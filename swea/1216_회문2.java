import java.io.*;
import java.util.*;

class fromTo {
  int from;
  int to;
  
  fromTo(){}
  fromTo(int from, int to){
    this.from = from;
    this.to = to;
  }
  
  @Override
  public String toString() {
    return "["+this.from+"~"+this.to+"]";
  }
  
}

public class Solution {
  static int N = 100;
  
  static HashMap<Integer, Queue<fromTo>> getMap() {
    HashMap<Integer, Queue<fromTo>> map = new HashMap<>();
    for(int i=0; i<N; i++) {
      for(int j=i+1; j<N; j++) {
        int diff = j-i+1;
        if(!map.containsKey(diff)) {
          map.put(diff, new LinkedList<>());
        }
        map.get(diff).add(new fromTo(i, j));
      }
    }
    return map;
  }
  
  static char[] getVerticalArray(char[][] originArray, int colIdx) {
    char[] arr = new char[N];
    for(int i=0; i<N; i++) {
      arr[i] = originArray[i][colIdx];
    }
    return arr;
  }
  
  static boolean isPalindrome(char[] cs) {
    for(int i=0; i<cs.length/2; i++) {
      if(cs[i]!=cs[cs.length-i-1]) {
        return false;
      }
    }
    return true;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = 10;
    HashMap<Integer, Queue<fromTo>> map = getMap();
    ArrayList<Integer> keys = new ArrayList<>(map.keySet());
    Collections.sort(keys, Collections.reverseOrder());
    
    for (int t = 1; t <= T; t++) {

      int result = 0;
      int tNum = Integer.parseInt(br.readLine());
      char[][] matrix = new char[N][N];
      
      for(int i=0; i < N; i++) {
        matrix[i] = br.readLine().toCharArray();
      }
      
      for(int i=0; i<N; i++) {
        for(int key:keys) {
          if(result > key) {
            break;
          }
          for(fromTo p:map.get(key)) {
            char[] vMatrix = getVerticalArray(matrix, i);
            if(isPalindrome(Arrays.copyOfRange(matrix[i], p.from, p.to+1)) ||
                isPalindrome(Arrays.copyOfRange(vMatrix, p.from, p.to+1))) {
              result = Math.max(result, key);
              break;
            }
          }
        }
      }
      
      System.out.printf("#%d %d\n", tNum, result);
    }
  }
}
