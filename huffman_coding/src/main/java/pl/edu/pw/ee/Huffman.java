package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Huffman {

    private int encodedTextLength;

    private Node root;

    private List<Node> listOfNodes;
    private List<Code> listOfCodes;

    private String pathToRootDir;
    private final String plainTextFileName = "data.txt";
    private final String encodedTextFileName = "encoded.txt";
    private final String treeConfigFileName = "treeConfigFile.txt";
    private final String decodedTextFileName = "decoded.txt";

    private String text;

    private String treeConfig;

    private class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.getFreq() < o2.getFreq()) {
                return -1;
            } else if (o1.getFreq() == o2.getFreq()) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public Huffman() {
        listOfNodes = new ArrayList<>();
        listOfCodes = new ArrayList<>();
    }

    public int huffman(String pathToRootDir, boolean compress) {
        ReadWriteUtils.validateRootDirectory(pathToRootDir);

        this.pathToRootDir = pathToRootDir;

        if (compress == true) {
            readPlainTextFile();

            createTreeConfig();

            buildTree();

            getCodes();

            int numOfBits = encode();

            writeToFile(pathToRootDir + treeConfigFileName, encodedTextLength + "\n" + treeConfig);

            return numOfBits;
        } else {
            listOfNodes.clear();

            readTreeConfig();

            return decode();
        }
    }

    private int encode() {
        String encodedText = "";

        char[] charsToEncode = text.toCharArray();

        for (int i = 0; i < charsToEncode.length; i++) {
            char charToEncode = charsToEncode[i];

            for (int j = 0; j < listOfCodes.size(); j++) {
                if (listOfCodes.get(j).getCharacter() == charToEncode) {
                    encodedText += listOfCodes.get(j).getBitCode();
                    break;
                }
            }
        }

        ReadWriteUtils.writeBinaryFile(pathToRootDir + encodedTextFileName, encodedText);

        this.encodedTextLength = encodedText.length();

        return encodedText.length();
    }

    private void readPlainTextFile() {
        ReadWriteUtils.validateInputFile(pathToRootDir + plainTextFileName);

        try (FileReader fileReader = new FileReader(pathToRootDir + plainTextFileName, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(fileReader);) {

            int character;
            text = "";

            while ((character = reader.read()) != -1) {
                text += (char) character;

                addToNodes((char) character, 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (listOfNodes.size() == 0) {
            throw new IllegalArgumentException("File is empty!");
        }
    }

    private void createTreeConfig() {

        treeConfig = "";

        for (int i = 0; i < listOfNodes.size(); i++) {
            treeConfig += listOfNodes.get(i).toString() + " ";
        }
    }

    private void buildTree() {
        NodeComparator nodeComparator = new NodeComparator();

        while (listOfNodes.size() > 1) {
            listOfNodes.sort(nodeComparator);

            Node left = listOfNodes.remove(0);
            Node right = listOfNodes.remove(0);

            listOfNodes.add(new Node(left, right, left.getFreq() + right.getFreq()));
        }

        root = listOfNodes.get(0);
    }

    private void getCodes() {
        getCodes("", root);

    }

    private void getCodes(String subWord, Node node) {

        if (node.getCharacter() != null) {
            listOfCodes.add(new Code(node.getCharacter(), subWord));
        }

        if (node.getLeft() != null) {
            String leftSubWord = subWord + "0";

            getCodes(leftSubWord, node.getLeft());
        }

        if (node.getRight() != null) {
            String rightSubWord = subWord + "1";

            getCodes(rightSubWord, node.getRight());
        }
    }

    private void readTreeConfig() {
        ReadWriteUtils.validateInputFile(pathToRootDir + treeConfigFileName);

        try (FileReader fileReader = new FileReader(pathToRootDir + treeConfigFileName, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(fileReader);) {

            int character;
            int lengthOfEncodedText;

            try {
                if ((lengthOfEncodedText = Integer.parseInt(reader.readLine())) != -1) {
                    encodedTextLength = lengthOfEncodedText;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(treeConfigFileName + " is broken!");
            }

            int freq;

            List<Character> freqChars = new ArrayList<>();

            while ((character = reader.read()) != -1) {
                if (reader.read() != ' ') {
                    throw new IllegalArgumentException("Tree config file is broken!");
                }

                while ((freq = reader.read()) != -1) {
                    if ((char) freq >= '0' && (char) freq <= '9') {
                        freqChars.add((char) freq);
                    } else {
                        if (freq != ' ') {
                            throw new IllegalArgumentException("Tree config file is broken!");
                        }
                        break;
                    }
                }

                if (freqChars.size() == 0) {
                    return;
                }

                StringBuilder builder = new StringBuilder(freqChars.size());
                for (Character ch : freqChars) {
                    builder.append(ch);
                }

                freq = Integer.parseInt(builder.toString());

                addToNodes((char) character, freq);

                freqChars.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        buildTree();
    }

    private int decode() {

        String binaryFileText = ReadWriteUtils.readBinaryFile(pathToRootDir + encodedTextFileName);

        if (encodedTextLength == 0 && root.getLeft() == null && root.getRight() == null) {
            writeToFile(decodedTextFileName, "" + root.getCharacter());

            return 1;
        }

        String encodedText = binaryFileText.substring(0, encodedTextLength);

        text = "";

        Node iter = root;

        for (int i = 0; i < encodedTextLength; i++) {
            iter = nextNode(iter, encodedText.charAt(i));
        }

        nextNode(iter, encodedText.charAt(encodedTextLength - 1));

        writeToFile(decodedTextFileName, text);

        return text.length();
    }

    private Node nextNode(Node node, int character) {
        if (node.getLeft() != null && character == '0') {
            return node.getLeft();
        } else if (node.getRight() != null && character == '1') {
            return node.getRight();
        } else {
            text += node.getCharacter();

            node = root;

            if (character != -1) {
                return nextNode(node, character);
            } else {
                return node;
            }
        }
    }

    private void addToNodes(char character, int numOfChars) {

        boolean contains = false;

        for(int i = 0; i < listOfNodes.size(); i++) {
            if(listOfNodes.get(i).equals(new Node(null, null, 1, character))) {
                contains = true;
                break;
            }
        }
        
        if (contains) {
            int id = 0;

            Node leafToFind = new Node(null, null, 1, character);

            for (int i = 0; i < listOfNodes.size(); i++) {
                if (leafToFind.equals(listOfNodes.get(i))) {
                    id = i;
                    break;
                }
            }

            listOfNodes.get(id).setFreq(listOfNodes.get(id).getFreq() + numOfChars);

        } else {
            listOfNodes.add(new Node(null, null, numOfChars, character));
        }
    }

    private void writeToFile(String fileName, String textToWrite) {
        ReadWriteUtils.createNewFile(pathToRootDir + fileName);

        try (FileWriter fileWriter = new FileWriter(pathToRootDir + fileName, Charset.forName("UTF-8"));
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {

            bufferedWriter.write(textToWrite);

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}