package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashTableOpenAddressingPerformanceTest {

    private URL wordListSrc;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;

    private String inputLine;

    private String[] words;

    private final int wordListSize = 100000;
    private final int numOfMeasurements = 30;
    private final int initHashSize = 512;

    private final int firstNotRejectedVal = 9;
    private final int lastNotRejectedVal = 19;

    private HashTable<String> hashTable;

    private long start;
    private long end;
    private long elapsedTime;

    private long[] putTimeResults;
    private long[] getTimeResults;

    private double putTimeArmean;
    private double getTimeArmean;

    private int counter = 1;

    @Before
    public void setUp() {
        words = new String[wordListSize];
        getTimeResults = new long[numOfMeasurements];
        putTimeResults = new long[numOfMeasurements];

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
    public void performanceTest() {
        HashTable<String> hashLinearProbing = null;
        HashTable<String> hashQuadraticProbing = null;
        HashTable<String> hashDoubleHashing = null;

        System.out.println("Adresowanie liniowe:");
        printTimeForLinearProbing(hashLinearProbing);
        counter = 1;

        System.out.println("Adresowanie kwadratowe:");
        printTimeForQuadraticProbing(hashQuadraticProbing);
        counter = 1;

        System.out.println("Adresowanie podwójne:");
        printTimeForDoubleHashing(hashDoubleHashing);

    }

    private void printTimeForLinearProbing(HashTable<String> hash) {
        printHeader();

        for (int i = 1; i <= initHashSize; i *= 2) {
            hash = new HashLinearProbing<>(i * initHashSize);
            System.out.print(fixedLengthString(counter++ + ". ", 4) + fixedLengthString(i * initHashSize + "", 26));
            measureTime(hash, i * initHashSize);
        }

    }

    private void printTimeForQuadraticProbing(HashTable<String> hash) {
        System.out.println("Lp  " + "Poczatkowy rozmiar hasza  " + "Sredni czas wstawiania 100000 elementow "
                + "Sredni czas wyszukiwania 100000 elementow");

        for (int i = 1; i <= initHashSize; i *= 2) {
            hash = new HashQuadraticProbing<>(i * initHashSize, 1, 10);
            System.out.print(fixedLengthString(counter++ + ". ", 4) + fixedLengthString(i * initHashSize + "", 26));
            measureTime(hash, i * initHashSize);
        }

    }

    private void printTimeForDoubleHashing(HashTable<String> hash) {
        printHeader();

        for (int i = 1; i <= initHashSize; i *= 2) {
            hash = new HashDoubleHashing<>(i * initHashSize);
            System.out.print(fixedLengthString(counter++ + ". ", 4) + fixedLengthString(i * initHashSize + "", 26));
            measureTime(hash, i * initHashSize);
        }

    }

    private void measureTime(HashTable<String> hash, int hashSize) {

        for (int j = 0; j < numOfMeasurements; j++) {
            try {
                hash = hash.getClass().newInstance();
            } catch (Exception e) {
                System.out.println("doing nothing");
            }

            start = System.nanoTime();
            for (int i = 0; i < wordListSize; i++) {
                hash.put(words[i]);
            }
            end = System.nanoTime();

            elapsedTime = (end - start) / 1000;
            putTimeResults[j] = elapsedTime;

            start = System.nanoTime();
            for (int i = 0; i < wordListSize; i++) {
                hash.get(words[i]);
            }
            end = System.nanoTime();

            /*
             * for(int i = 0; i < wordListSize; i++) {
             * hash.delete(words[i]);
             * }
             */

            elapsedTime = (end - start) / 1000;
            getTimeResults[j] = elapsedTime;
        }

        Arrays.sort(putTimeResults);
        Arrays.sort(getTimeResults);

        long getTimeSum = 0, putTimeSum = 0;

        for (int i = firstNotRejectedVal; i < lastNotRejectedVal; i++) {
            putTimeSum += putTimeResults[i];
            getTimeSum += getTimeResults[i];
        }

        putTimeArmean = putTimeSum / (lastNotRejectedVal - firstNotRejectedVal);
        getTimeArmean = getTimeSum / (lastNotRejectedVal - firstNotRejectedVal);

        System.out.print(fixedLengthString(putTimeArmean + "", 40) + fixedLengthString(getTimeArmean + "", 40));
        System.out.println();
    }

    private String fixedLengthString(String string, int length) {
        return String.format("%1$-" + length + "s", string);
    }

    private void printHeader() {
        System.out.println("Lp  " + "Poczatkowy rozmiar hasza  " + "Sredni czas wstawiania 100000 elementow "
                + "Sredni czas wyszukiwania 100000 elementow");
    }
}
