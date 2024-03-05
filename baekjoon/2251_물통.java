import java.util.*;
import java.io.*;

class Amount {
  int a;
  int b;
  int c;
  
  public Amount(int a, int b, int c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
}

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int A = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());
    
    Set<Integer> set = new HashSet<>();
    
    Queue<Amount> queue = new LinkedList<>();
    boolean[][][] visited = new boolean[A+1][B+1][C+1];
    queue.add(new Amount(0, 0, C));
    
    while(!queue.isEmpty()) {
      Amount amt = queue.poll();
      if(visited[amt.a][amt.b][amt.c]) {
        continue;
      }
      visited[amt.a][amt.b][amt.c] = true;
      
      if(amt.a==0) {
        set.add(amt.c);
      }
      
      if(amt.a+amt.b<=B) { // A->B
        queue.add(new Amount(0, amt.a+amt.b, amt.c));
      } else {
        queue.add(new Amount(amt.a-(B-amt.b), B, amt.c));
      }
      
      if(amt.a+amt.c<=C) { // A->C
        queue.add(new Amount(0, amt.b, amt.c+amt.a));
      } else {
        queue.add(new Amount(amt.a-(C-amt.c), amt.b, C));
      }
      
      if(amt.b+amt.a<=A) { // B->A
        queue.add(new Amount(amt.a+amt.b, 0, amt.c));
      } else {
        queue.add(new Amount(A, amt.b-(A-amt.a), amt.c));
      }

      if(amt.b+amt.c<=C) { // B->C
        queue.add(new Amount(amt.a, 0, amt.c+amt.b));
      } else {
        queue.add(new Amount(amt.a, amt.b-(A-amt.a), C));
      }
      
      if(amt.c+amt.a<=A) { // C->A
        queue.add(new Amount(amt.a+amt.c, amt.b, 0));
      } else {
        queue.add(new Amount(A, amt.b, amt.c-(A-amt.a)));
      }
      
      if(amt.c+amt.b<=B) { // C->B
        queue.add(new Amount(amt.a, amt.b+amt.c, 0));
      } else {
        queue.add(new Amount(amt.a, B, amt.c-(B-amt.b)));
      }
    }
    
    SortedSet<Integer> sortedSet = new TreeSet<>(set);
    for(int n:sortedSet) {
      System.out.print(n+" ");
    }
  }
}
