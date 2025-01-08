import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {
        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.
        ArrayList<String> goodWords = new ArrayList<String>();
        boolean[][] visited = new boolean[board.length][board[0].length];
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
                dfs(tst, goodWords, board, visited, i, j, "");
            }
        }

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }
    public static void dfs(TST tst, ArrayList<String> goodWords, char[][] board, boolean[][] visited, int row, int col, String word){
        // Check if out of bounds
        if(row < 0 || row >= visited.length || col < 0 || col >= visited[0].length){
            return;
        }
        // Check if visited
        if(visited[row][col]){
            return;
        }
        String wordTwo = word;
        wordTwo += board[row][col];
        // Check if prefix is in dictionary
        if(tst.lookup(word) >= 0){
            // Check for duplicates
            if(!goodWords.contains(word)){
                goodWords.add(word);
            }
        }
        visited[row][col] = true;
        // Recurse top
        dfs(tst, goodWords, board, visited, row + 1, col, wordTwo);
        // Recurse Bottom
        dfs(tst, goodWords, board, visited, row - 1, col, wordTwo);
        // Recurse Left
        dfs(tst, goodWords, board, visited, row, col - 1, wordTwo);
        // Recurse Right
        dfs(tst, goodWords, board, visited, row, col + 1, wordTwo);
        // Mark as unvisited
        visited[row][col] = false;
    }
}
