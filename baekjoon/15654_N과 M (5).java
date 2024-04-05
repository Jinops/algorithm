import java.util.*;
import java.io.*;

class Main {
  static int N;
  static int M;
  static int nums[];
  static boolean check[];

  static void pick(Stack<Integer> stk){
    if(stk.size()==M){
      for(int n:stk){
        System.out.print(n+" ");
      }
      System.out.println();
      return;
    }

    for(int i=0; i<N; i++){
      if(check[i]) continue;
      stk.add(nums[i]);
      check[i] = true;
      pick(stk);
      check[i] = false;
      stk.pop();
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    nums = new int[N];
    check = new boolean[N];

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++){
      nums[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(nums);
    pick(new Stack<Integer>());
  }
}
