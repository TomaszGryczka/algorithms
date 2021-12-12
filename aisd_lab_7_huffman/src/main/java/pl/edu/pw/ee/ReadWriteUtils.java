package pl.edu.pw.ee;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class ReadWriteUtils {

    public static void writeBinaryFile(String pathToFile, String textToWrite) {
        createNewFile(pathToFile);

        try (FileOutputStream fileOutputStream = new FileOutputStream(pathToFile);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);) {

            while (textToWrite.length() % 8 != 0) {
                textToWrite += "0";
            }

            for (int i = 0; i < textToWrite.length(); i += 8) {
                String byteString = textToWrite.substring(i, i + 8);
                int parsedByte = 0xFF & Integer.parseInt(byteString, 2);

                bufferedOutputStream.write(parsedByte);
            }

            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readBinaryFile(String pathToFile) {
        validateInputFile(pathToFile);

        String result = "";

        try (FileInputStream fileInputStream = new FileInputStream(pathToFile);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);) {

            int parsedByte; 

            while ((parsedByte = bufferedInputStream.read()) != -1) {
                
                result += String.format("%08d", Integer.parseInt(Integer.toString(parsedByte, 2), 10));
            }

            bufferedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static void createNewFile(String pathToFile) {
        File file = new File(pathToFile);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void validateInputFile(String pathToFile) {
        File file = new File(pathToFile);

        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("File " + pathToFile + " does not exist!");
        }
    }

    public static void validateRootDirectory(String pathToRootDir) {
        File file = new File(pathToRootDir);

        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("Incorrect root directory!");
        }
    }
}
