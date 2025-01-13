import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {
        ArrayList<String> goodWords = new ArrayList<String>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        // Trie to store the dictionary words for fast lookups
        Trie trie = new Trie();
        for(int i = 0; i < dictionary.length;i++){
            trie.insert(dictionary[i]);
        }
        Node node = trie.getRoot();
        // Depth First Search
        String word = "";
        // for every letter on the board
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length;j++){
                dfs(node, goodWords, board, visited, i, j, "");
            }
        }
        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }
    public static void dfs(Node node, ArrayList<String> goodWords, char[][] board, boolean[][] visited, int row, int col, String word){
        // Check if out of bounds
        if(row < 0 || row >= visited.length || col < 0 || col >= visited[0].length){
            return;
        }
        // Check if visited
        else if(visited[row][col]){
            return;
        }
        String wordTwo = word;
        // Check if next letter is in dictionary
        if(node.getNext()[board[row][col]] != null){
            wordTwo += board[row][col];
            node = node.getNext()[board[row][col]];
        }
        else{
            return;
        }
        // Check if prefix is in dictionary
        if(node.isWord()){
            // Check for duplicates
            if(!goodWords.contains(wordTwo)) {
                goodWords.add(wordTwo);
            }
        }
        // Mark as visited
        visited[row][col] = true;
        // Recurse top
        dfs(node, goodWords, board, visited, row - 1, col, wordTwo);
        // Recurse right
        dfs(node, goodWords, board, visited, row, col + 1, wordTwo);
        // Recurse bottom
        dfs(node, goodWords, board, visited, row + 1, col, wordTwo);
        // Recurse left
        dfs(node, goodWords, board, visited, row, col - 1, wordTwo);
        // Mark as unvisited
        visited[row][col] = false;
    }
}
