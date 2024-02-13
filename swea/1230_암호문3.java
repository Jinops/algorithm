// LinkedList 직접구현하기
import java.io.*;
import java.util.*;

class Node {
  int data;
  Node next;
  
  Node(){}
  Node(int data){
    this.data = data;
  }
}

class LinkedList {
  Node head;
  int size;
  
  LinkedList(){
    this.head = new Node();
  }
  
  private Node getNode(int idx) {
    if(!(0<=idx&&idx<=size)) {
      throw new IndexOutOfBoundsException();
    }
    Node node = head;
    for(int i=0; i<idx; i++) {
      node = node.next;
    }
    return node;
  }
  
  void insert(int idx, int data) {
    Node cur = getNode(idx);
    Node node = new Node(data);
    node.next = cur.next;
    cur.next = node;
    size++;
  }
  
  void delete(int idx) {
    Node cur = getNode(idx);
    cur.next = cur.next.next;
    size--;
  }
  
  void add(int data) {
    insert(size, data);
  }

  @Override
  public String toString() {
    return toStringLimit(Integer.MAX_VALUE);
  }
  
  public String toStringLimit(int limit) {
    String str = "";
    Node cur = head.next;
    while(cur!=null && limit-->0) {
      str += cur.data + " ";
      cur = cur.next;
    }
    return str;
  }
}

public class Solution {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    for(int t=1; t<=10; t++) {

      LinkedList pw = new LinkedList();
      int N = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      
      for(int i=0; i<N; i++) {
        pw.add(Integer.parseInt(st.nextToken()));
      }
      
      int cmdCnt = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      
      for(int i=0; i<cmdCnt; i++) {
        char cmd = st.nextToken().charAt(0);
        
        if(cmd=='A') {
          int y = Integer.parseInt(st.nextToken());
          for(int j=0; j<y; j++) {
            pw.add(Integer.parseInt(st.nextToken()));
          }
        } else {
          int x = Integer.parseInt(st.nextToken());
          int y = Integer.parseInt(st.nextToken());
          
          for(int j=0; j<y; j++) {
            switch(cmd) {
            case 'I': pw.insert(x++, Integer.parseInt(st.nextToken())); break;
            case 'D': pw.delete(x); break;
            }
          }
        }
      }
      
      System.out.printf("#%d %s\n", t, pw.toStringLimit(10));
    }
    
  }
}
