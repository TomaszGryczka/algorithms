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
    private URL wordList;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;

    private String inputLine;

    private String[] arr;

    private final int wordListSize = 100000;
    private final int numOfMeasurements = 30;
    private final int firstNotRejectedValue = 9;
    private final int lastNotRejectedValue = 19;

    //should make of this variable
    private final int initHashSize = 4096;

    private HashTable<String> hashTable;

    private long start;
    private long end;
    private long result;

    private long[] measurements;

    private double armean;

    @Before
    public void setUp() {
        arr = new String[wordListSize];
        measurements = new long[numOfMeasurements];

        try {
            wordList = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
            inputStreamReader = new InputStreamReader(wordList.openStream());

            bufferedReader = new BufferedReader(inputStreamReader);

            int i = 0;

            while ((inputLine = bufferedReader.readLine()) != null) {
                arr[i++] = inputLine;
            }
        } catch (IOException e) {
            System.out.println("Error!");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Error!");
                }
            }
        }
    }

    @Test
    public void should() { //change name this test
        System.out.println("LP  " + "Rozmiar hasza  " + "Sredni czas wyszukiwania 100000 elementow");
        int counter = 1;
        for (int i = 1; i <= 64; i *= 2) {
            //some magic numbers
            System.out.println(fixedLengthString(counter++ + ". ", 4) + fixedLengthString(i * 4096 + "", 15)
                    + fixedLengthString(measureTime(i * 4096) + "", 42));
        }

    }

    private double measureTime(int hashTableSize) {
        hashTable = new HashListChaining<>(hashTableSize);

        for (int i = 0; i < wordListSize; i++) {
            hashTable.add(arr[i]);
        }

        for (int j = 0; j < numOfMeasurements; j++) {
            start = System.nanoTime();
            for (int i = 0; i < wordListSize; i++) {
                hashTable.get(arr[i]);
            }
            end = System.nanoTime();

            result = (end - start) / 1000;
            measurements[j] = result;
        }

        Arrays.sort(measurements);

        long sum = 0;

        for (int i = firstNotRejectedValue; i < lastNotRejectedValue; i++) {
            sum += measurements[i];
        }

        armean = sum / (lastNotRejectedValue - firstNotRejectedValue); // should do it better

        return armean;

    }

    private String fixedLengthString(String string, int length) {
        return String.format("%1$-" + length + "s", string);
    }
}
