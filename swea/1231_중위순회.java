import java.io.*;
import java.util.*;

class Node {
  int number;
  char data;
  Node left;
  Node right;
  
  Node(){}
  Node(int number){
    this.number = number;
  }
  Node(int number, char data){
    this.number = number;
    this.data = data;
  }
}

public class Solution {
  
  static void inOrder(Node node) { // LVR
    if(node==null) {
      return;
    }
    inOrder(node.left);
    System.out.print(node.data);
    inOrder(node.right);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    for(int t=1; t<=10; t++) {
      
      int N = Integer.parseInt(br.readLine());
      Node[] nodes = new Node[N+1];
      
      for(int i=1; i<=N; i++) {
        nodes[i] = new Node(i);
      }
      
      for(int i=1; i<=N; i++) {
        st = new StringTokenizer(br.readLine());
        Node node = nodes[Integer.parseInt(st.nextToken())];
        node.data = st.nextToken().charAt(0);
        if(st.hasMoreTokens()) {
          node.left = nodes[Integer.parseInt(st.nextToken())];
        }
        if(st.hasMoreTokens()) {
          node.right = nodes[Integer.parseInt(st.nextToken())];
        }
      }
      
      System.out.printf("#%d ",t);
      inOrder(nodes[1]);
      System.out.println();
    }
  }
}
