import java.io.*;
import java.util.*;

public class Main {
  static int size;
  static String[] nodes;
  static ArrayList<String>[] results;
  
  static void updateResult(int start, int end, int depth) {
    if(depth==-1) {
      return;
    }
    int mid = (start+end)/2;
    results[depth].add(nodes[mid]);
    updateResult(start, mid-1, depth-1);
    updateResult(mid+1, end, depth-1);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int K = Integer.parseInt(br.readLine());
    size = (int)Math.pow(2, K)-1;
    nodes = br.readLine().split(" "); // 중위순회
    results = new ArrayList[K];
    for(int i=0; i<K; i++) {
      results[i] = new ArrayList<>();
    }
    
    updateResult(0, size, K-1);
    
    for(int i=K-1; i>=0; i--) {
      for(Object node:results[i].toArray()) {
        System.out.print(node + " ");
      }
      System.out.println();
    }
  }
}
/*
4
8 4 9 2 10 5 11 1 12 6 13 3 14 7 15
*/
