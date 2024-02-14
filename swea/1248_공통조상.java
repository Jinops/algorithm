import java.io.*;
import java.util.*;
 
class Node {
  int data;
  Node parent;
  Node left;
  Node right;
   
  Node(){}
  Node(int number){
    this.data = number;
  }
}
 
public class Solution {
  static int count(Node n) {
    if(n==null) {
      return 0;
    }
    int cnt = 1;
    cnt += count(n.left);
    cnt += count(n.right);
    
    return cnt;
  }
   
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());
     
    for(int t=1; t<=T; t++) {
       
      st = new StringTokenizer(br.readLine());
      int V = Integer.parseInt(st.nextToken());
      int E = Integer.parseInt(st.nextToken());
       
      Node[] nodes = new Node[V+1];
      for(int i=1; i<=V; i++) {
        nodes[i] = new Node(i);
      }
       
      Node n1 = nodes[Integer.parseInt(st.nextToken())];
      Node n2 = nodes[Integer.parseInt(st.nextToken())];
 
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<E; i++) {
        int p = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
         
        if(nodes[p].left==null) {
          nodes[p].left = nodes[n];
        } else {
          nodes[p].right = nodes[n];
        }
        nodes[n].parent = nodes[p];
      }
       
      ArrayList<Node> n1Parents = new ArrayList<>();
      while(n1!=null) {
        n1Parents.add(n1.parent);
        n1 = n1.parent;
      }
      while(!n1Parents.contains(n2)) {
        n2 = n2.parent;
      }
       
      System.out.printf("#%d %d %d\n", t, n2.data, count(n2));
    }
  }
}
