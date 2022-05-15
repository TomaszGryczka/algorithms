package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Skipped because it takes too much time.")
public class RbtInvocCounter {
    private URL wordListSrc;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;

    private String inputLine;

    private String[] words;

    private final int wordListSize = 1000000;

    private RedBlackTree<String, String> rbt;

    @Before
    public void setUp() {
        words = new String[wordListSize];

        rbt = new RedBlackTree<>();

        try {
            wordListSrc = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
            inputStreamReader = new InputStreamReader(wordListSrc.openStream());

            bufferedReader = new BufferedReader(inputStreamReader);

            int i = 0;

            while ((inputLine = bufferedReader.readLine()) != null) {
                for (int j = 0; j < 10; j++) {
                    words[i + j] = inputLine + j;
                }
                i += 10;
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
    public void countNumOfInvocations() {
        try {
            FileWriter fileWriter = new FileWriter("output.txt");

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < wordListSize; i++) {
                rbt.put(words[i], words[i]);
                bufferedWriter.write(i + ", " + rbt.getCounter() + '\n');
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
