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
 
public class Main {
   
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());
     
    for(int t=1; t<=T; t++) {
       
      int N = Integer.parseInt(br.readLine());
       
      Node[] nodes = new Node[N+1];
      for(int i=1; i<=N; i++) {
        nodes[i] = new Node(i);
      }
       
 
      for(int i=0; i<N-1; i++) {
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
         
        if(nodes[p].left==null) {
          nodes[p].left = nodes[n];
        } else {
          nodes[p].right = nodes[n];
        }
        nodes[n].parent = nodes[p];
      }

      st = new StringTokenizer(br.readLine());
      Node n1 = nodes[Integer.parseInt(st.nextToken())];
      Node n2 = nodes[Integer.parseInt(st.nextToken())];
      
      ArrayList<Node> n1Parents = new ArrayList<>();
      while(n1!=null) {
        n1Parents.add(n1);
        n1 = n1.parent;
      }
      while(!n1Parents.contains(n2)) {
        n2 = n2.parent;
      }
       
      System.out.println(n2.data);
    }
  }
}
