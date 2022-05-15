package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashListChainingTimeTest {
    private URL wordListSrc;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;

    private String inputLine;

    private String[] words;

    private final int wordListSize = 100000;
    private final int numOfMeasurements = 30;
    private final int initHashSize = 4096;

    private final int firstNotRejectedVal = 9;
    private final int lastNotRejectedVal = 19;

    private HashTable<String> hashTable;

    private long start;
    private long end;
    private long elapsedTime;

    private long[] results;

    private double armean;

    @Before
    public void setUp() {
        words = new String[wordListSize];
        results = new long[numOfMeasurements];

        try {
            wordListSrc = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
            inputStreamReader = new InputStreamReader(wordListSrc.openStream());

            bufferedReader = new BufferedReader(inputStreamReader);

            int i = 0;

            while ((inputLine = bufferedReader.readLine()) != null) {
                words[i++] = inputLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void printTimeForDifferentHashTableSizes() {
        System.out.println("LP  " + "Rozmiar hasza  " + "Sredni czas wyszukiwania 100000 elementow");
        int counter = 1;
        for (int i = 1; i <= 64; i *= 2) {
            System.out.println(fixedLengthString(counter++ + ". ", 4) + fixedLengthString(i * initHashSize + "", 15)
                    + fixedLengthString(measureTime(i * initHashSize) + "", 42));
        }
    }

    private double measureTime(int hashTableSize) {
        hashTable = new HashListChaining<>(hashTableSize);

        for (int i = 0; i < wordListSize; i++) {
            hashTable.add(words[i]);
        }

        for (int j = 0; j < numOfMeasurements; j++) {
            start = System.nanoTime();
            for (int i = 0; i < wordListSize; i++) {
                hashTable.get(words[i]);
            }
            end = System.nanoTime();

            elapsedTime = (end - start) / 1000;
            results[j] = elapsedTime;
        }

        Arrays.sort(results);

        long sum = 0;

        for (int i = firstNotRejectedVal; i < lastNotRejectedVal; i++) {
            sum += results[i];
        }

        armean = sum / (lastNotRejectedVal - firstNotRejectedVal);

        return armean;

    }

    private String fixedLengthString(String string, int length) {
        return String.format("%1$-" + length + "s", string);
    }
}
