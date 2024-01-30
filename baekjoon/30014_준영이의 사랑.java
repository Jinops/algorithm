import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] arr;
  static int bestResult;
  static Stack<Integer> bestStack;
  
  static int calculate(Stack<Integer> stack) {
    int result = stack.lastElement() * stack.firstElement();
    for(int i=0; i<stack.size()-1; i++) {
      result += stack.get(i) * stack.get(i+1);
    }
    return result;
  }
  
  static void run(Stack<Integer> stack) {
    if(stack.size() == N) {
      int result = calculate(stack);
      if(result > bestResult) {
        bestResult = result;
        bestStack = (Stack<Integer>) stack.clone();
      }
    }
    for(int n:arr) {
      if(stack.search(n)==-1) {
        stack.add(n);
        run(stack);
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
    
    Stack<Integer> stack = new Stack<>();
    run(stack);
    
    System.out.println(bestResult);
    System.out.println(bestStack.toString().replace("[", "").replace("]", "").replaceAll(",", ""));
  }
}
