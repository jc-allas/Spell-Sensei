package for_Project4Blanco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spellchecker {
    private BinarySearchTree<String> words;

    public Spellchecker() {
        words = new BinarySearchTree<>();
        wordList();
    }

    private void wordList() {
        try {
            Scanner scanner = new Scanner(new File("wordList.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String word = removePunctAndDowncase(line);
                words.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.addSuppressed(e);
        }
    }
    
    public String removePunctAndDowncase(String input) {
        String punctuation = ".,?!-\";:()%$#@";
        String output = input.replaceAll("[" + punctuation + "]", "");
        output = output.replaceAll("’", "'");
        return output.toLowerCase();
    }

    public boolean isWordCorrect(String word) {
        String printedWord = removePunctAndDowncase(word);
        return words.search(printedWord) != null;
    }
}