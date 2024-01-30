import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] arr;
  static int bestResult;
  static LinkedList<Integer> bestStack;
  
  static int calculate(Stack<Integer> stack) {
    int result = stack.lastElement() * stack.firstElement();
    for(int i=0; i<stack.size()-1; i++) {
      result += stack.get(i) * stack.get(i+1);
    }
    return result;
  }
  
  static void run(int multiplied, LinkedList<Integer> list) {
    if(list.size() == N && multiplied > bestResult) {
//      System.out.println(list.toString());
      bestResult = multiplied;
      bestStack = list;
    }
    for(int n:arr) {
      if(!list.contains(n)) {
        list.add(n);
        run(multiplied*n, list);
        list.pop();
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
    long beforeTime = System.currentTimeMillis();
    run(1, new LinkedList<Integer>());
    System.out.println(bestResult);
    System.out.println(bestStack.toString().replace("[", "").replace("]", "").replaceAll(",", ""));

    System.out.println("시간차이(m) : "+( System.currentTimeMillis() - beforeTime));
  }
}
