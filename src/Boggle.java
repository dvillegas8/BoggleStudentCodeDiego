import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {
        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.
        ArrayList<String> goodWords = new ArrayList<String>();
        Stack<Character> letters = new Stack<Character>();
        // TST to store the dictionary words for fast lookups
        TST tst = new TST();
        for(int i = 0; i < dictionary.length;i++){
            tst.insert(dictionary[i], i);
        }
        // Depth First Search
        String word = "";
        // for every letter on the board
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[j].length;j++){
                // add current letter to stack
                word += board[i][j];
                letters.add(board[i][j]);
                // Check if in dictionary
            }
        }

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }
}
