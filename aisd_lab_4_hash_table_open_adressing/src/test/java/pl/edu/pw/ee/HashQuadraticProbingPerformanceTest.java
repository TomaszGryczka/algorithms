package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

@Ignore("Skipped because this test take too much time to print output. Delete this annotation to run performance test.")
public class HashQuadraticProbingPerformanceTest {
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

    private HashTable<String> hash;

    private long start;
    private long end;
    private long elapsedTime;

    private long[] putTimeResults;
    private long[] getTimeResults;

    private double putTimeArmean;
    private double getTimeArmean;

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
        System.out.println("Adresowanie kwadratowe:");
        printTimeForQuadraticProbing();
    }

    private void printTimeForQuadraticProbing() {                
        int counter = 1;
        int a = 0;
        int b = 11;

        for(int j = 1; j < 11; j++) {
            printHeader();
            System.out.println("a = " + (a + j) + ", " + "b = " + (b - j));

            for (int i = 1; i <= initHashSize; i *= 2) {
                System.out.print(fixedLengthString(counter + ". ", 4) + fixedLengthString(i * initHashSize + ",", 26));
                measureTime(i * initHashSize, a + j, b - j);
                counter++;
            }

            counter = 1;

            System.out.println();
        }
        

    }

    private void measureTime(int hashSize, int a, int b) {

        for (int j = 0; j < numOfMeasurements; j++) {
            hash = new HashQuadraticProbing<>(hashSize, a, b);

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

            elapsedTime = (end - start) / 1000;
            getTimeResults[j] = elapsedTime;
        }

        Arrays.sort(putTimeResults);
        Arrays.sort(getTimeResults);

        long getTimeSum = 0;
        long putTimeSum = 0;

        for (int i = firstNotRejectedVal; i < lastNotRejectedVal; i++) {
            putTimeSum += putTimeResults[i];
            getTimeSum += getTimeResults[i];
        }

        putTimeArmean = putTimeSum / (lastNotRejectedVal - firstNotRejectedVal);
        getTimeArmean = getTimeSum / (lastNotRejectedVal - firstNotRejectedVal);

        System.out.print(fixedLengthString(putTimeArmean + ",", 40) + fixedLengthString(getTimeArmean + ",", 40));
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
