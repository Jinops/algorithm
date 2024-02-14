import java.io.*;
import java.util.*;
 
class Node {
  char data;
  Node left;
  Node right;
  Node parent;
  
  Node(){}
  Node(char data){
    this.data = data;
  }
}

public class Main {
  static String preOrder(Node n) {
    if(n==null) return "";
    
    StringBuilder sb = new StringBuilder();
    sb.append(n.data);
    sb.append(preOrder(n.left));
    sb.append(preOrder(n.right));
    return sb.toString();
  }
  static String inOrder(Node n) {
    if(n==null) return "";
    
    StringBuilder sb = new StringBuilder();
    sb.append(inOrder(n.left));
    sb.append(n.data);
    sb.append(inOrder(n.right));
    return sb.toString();
  }
  static String postOrder(Node n) {
    if(n==null) return "";
    
    StringBuilder sb = new StringBuilder();
    sb.append(postOrder(n.left));
    sb.append(postOrder(n.right));
    sb.append(n.data);
    return sb.toString();
  }
  
  static int idx(char c) {
    return c-'A';
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    Node[] nodes = new Node[N];
    
    for(int i=0; i<N; i++) {
      nodes[i] = new Node((char)('A'+i));
    }
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      char c = st.nextToken().charAt(0);
      char left = st.nextToken().charAt(0);
      char right = st.nextToken().charAt(0);
      
      if(left!='.') {
        nodes[idx(c)].left = nodes[idx(left)]; 
      }
      if(right!='.') {
        nodes[idx(c)].right = nodes[idx(right)]; 
      }
    }
    System.out.println(preOrder(nodes[idx('A')]));
    System.out.println(inOrder(nodes[idx('A')]));
    System.out.println(postOrder(nodes[idx('A')]));
  }
}
