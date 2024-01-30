import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] arr;
  static int result;
  static Stack<Integer> resultStack;
  
  static void run(Stack<Integer> stack, int acc) {
    if(stack.size() == N) {
      acc += stack.lastElement() * stack.firstElement();
      if(acc > result) {
        result = acc;
        resultStack = (Stack<Integer>) stack.clone();
      }
      return;
    }
    for(int n:arr) {
      if(!stack.contains(n)) {
        int newAcc = acc;
        if(stack.size()>0) {
          newAcc += stack.lastElement() * n;
        }
        stack.add(n);
        run(stack, newAcc);
        stack.pop();
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    N = Integer.parseInt(br.readLine());
    arr = new int[N];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    run(new Stack<Integer>(), 0);
    System.out.println(result);
    System.out.println(resultStack.toString().replace("[", "").replace("]", "").replaceAll(",", ""));
  }
}
