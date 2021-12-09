package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Huffman {

    private Node root;

    private List<Node> listOfNodes;
    private List<Code> listOfCodes;

    private String pathToRootDir;

    private final String plainTextFileName = "data.txt";
    private final String encodedTextFileName = "encoded.txt";
    private final String treeConfigFileName = "treeConfigFile.txt";
    private final String decodedTextFileName = "decoded.txt";

    private String text;

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
        this.pathToRootDir = pathToRootDir;

        if (compress == true) {
            readPlainTextFile();

            writeTreeConfigFile();

            buildTree();

            getCodes();

            encode();
        } else {
            listOfNodes.clear();

            readTreeConfig();

            buildTree();

            decode();
        }

        return 0;
    }

    private void encode() {
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

        writeToFile(pathToRootDir + encodedTextFileName, encodedText);
    }

    private void readPlainTextFile() {
        validateInputFileName(plainTextFileName);

        try (FileReader fileReader = new FileReader(pathToRootDir + plainTextFileName);
                BufferedReader reader = new BufferedReader(fileReader);) {

            int character;

            while ((character = reader.read()) != -1) {
                text += (char) character;

                addToNodes((char) character, 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTreeConfigFile() {
        createNewFile(treeConfigFileName);

        String result = "";

        for (int i = 0; i < listOfNodes.size(); i++) {
            result += listOfNodes.get(i).toString() + " ";
        }

        writeToFile(treeConfigFileName, result);
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
        validateInputFileName(treeConfigFileName);

        try (FileReader fileReader = new FileReader(pathToRootDir + treeConfigFileName);
                BufferedReader reader = new BufferedReader(fileReader);) {

            int character;
            int freq;

            List<Character> freqChars = new ArrayList<>();

            while ((character = reader.read()) != -1) {
                while ((freq = reader.read()) != -1) {
                    if ((char) freq >= '0' && (char) freq <= '9') {
                        freqChars.add((char) freq);
                    } else {
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
    }

    private void decode() {
        validateInputFileName(encodedTextFileName);

        try (FileReader fileReader = new FileReader(pathToRootDir + encodedTextFileName);
                BufferedReader reader = new BufferedReader(fileReader);) {

            int character;

            text = "";

            Node it = root;

            while ((character = reader.read()) != -1) {
                it = nextNode(it, character);
            }

            nextNode(it, character);

            writeToFile(decodedTextFileName, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Node nextNode(Node node, int character) {
        if (node.getLeft() != null && character == '0') {
            return node.getLeft();
        } else if (node.getRight() != null && character == '1') {
            return node.getRight();
        } else {
            text += node.getCharacter();
            node = root;

            return character != -1 ? nextNode(node, character) : node;
        }
    }

    private void addToNodes(char character, int numOfChars) {
        if (listOfNodes.contains(new Node(null, null, 1, character))) {
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
        createNewFile(pathToRootDir + fileName);

        try (FileWriter fileWriter = new FileWriter(pathToRootDir + fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {

            bufferedWriter.write(textToWrite);

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewFile(String fileName) {
        File file = new File(pathToRootDir + fileName);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void validateInputFileName(String fileName) {
        File file = new File(pathToRootDir + fileName);
        
        if(!file.exists()) {
            throw new IllegalArgumentException("File data.txt does not exist!");
        }
    }

    private void printListOfNodes() {
        for (int i = 0; i < listOfNodes.size(); i++) {
            if (listOfNodes.get(i).getCharacter() == '\n') {
                System.out.println("\\n" + ":" + listOfNodes.get(i).getFreq());
            } else if (listOfNodes.get(i).getCharacter() == '\r') {
                System.out.println("\\r" + ":" + listOfNodes.get(i).getFreq());
            } else {
                System.out.println(listOfNodes.get(i));
            }
        }
    }

    private void getPreOrder(String subWord, Node node) {

        if (node.getCharacter() != null) {
            System.out.println(node.toString(subWord));
        }

        if (node.getLeft() != null) {
            String leftSubWord = subWord + "0";

            getPreOrder(leftSubWord, node.getLeft());
        }

        if (node.getRight() != null) {
            String rightSubWord = subWord + "1";

            getPreOrder(rightSubWord, node.getRight());
        }
    }

    public static void main(String[] args) {

        Huffman huf = new Huffman();

        huf.huffman("./", true);

        huf.huffman("./", false);
    }

}
