import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
  static void push(int[] heap, AtomicInteger size, int data) {
    size.getAndAdd(1);
    heap[size.get()] = data;

    int ch = size.get();
    int p = ch/2;
    
    while(p>0 && heap[p]<heap[ch]) {
      swap(heap, p, ch);
      ch = p;
      p = ch/2;
    }
  }
  
  static int pop(int[] heap, AtomicInteger size) {
    if(size.get()==0) {
      return -1;
    }
    
    int root = heap[1];
    heap[1] = heap[size.get()];
    heap[size.get()] = 0;
    size.getAndAdd(-1);
    
    int p = 1;
    int ch = p*2;
    if(ch+1 <= size.get() && heap[ch] < heap[ch+1]) {
      ch++; // right
    }
    
    while(ch <= size.get() && heap[p] < heap[ch]) {
      swap(heap, p, ch);
      p = ch;
      ch = p*2;

      if(ch+1 <= size.get() && heap[ch] < heap[ch+1]) {
        ch++; // right
      }
    }
    
    return root;
  }
  
  static void swap(int[] heap, int p, int ch) {
    int tmp = heap[p];
    heap[p] = heap[ch];
    heap[ch] = tmp;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      sb.append("#").append(t);
      
      int N = Integer.parseInt(br.readLine());
      int[] heap = new int[N+1];
      AtomicInteger size = new AtomicInteger(0);
      
      for(int n=0; n<N; n++) {
        
        st = new StringTokenizer(br.readLine());
        int cmd = Integer.parseInt(st.nextToken());
        if(cmd==1) {
          int x = Integer.parseInt(st.nextToken());
          push(heap, size, x);
        } else if(cmd==2) {
          sb.append(" ").append(pop(heap, size));
        }
//        System.out.println(size.get() + "/" + Arrays.toString(heap));
      }
      sb.append('\n');
    }
    System.out.print(sb.toString());
  }
}
