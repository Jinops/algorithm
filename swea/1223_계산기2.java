import java.io.*;
import java.util.Stack;
 
public class Solution {
  static int calculate(int a, int b, char c) {
    if(c=='+') return a+b;
    if(c=='-') return a-b;
    if(c=='*') return a*b;
    if(c=='/') return a/b;
    return Integer.MAX_VALUE;
  }
   
  static int calculatePostFix(char[] cs) {
    Stack<Integer> stack = new Stack<>();
     
    for(char c:cs) {
      if(Character.isDigit(c)) { // 1. 피연산자는 스택에 push
        stack.push(Character.getNumericValue(c));
      } else { // 2-1.연산자 만나면 필요한 만큼 피연산자를 스택에서 pop
        int n1 = stack.pop();
        int n2 = stack.pop();
        stack.push(calculate(n1, n2, c)); // 2-2. 결과를 다시 push
      }
    }
    return stack.pop(); // stack pop
  }
   
  static boolean hasPriority(char c, char compare) {
    if((c=='*' || c=='/') && (compare=='+' || compare=='-')) {
      return true;
    }
    return false;
  }
   
  static char[] toPostfix(int N, String str) { // 후위로 변경
    char[] cs = new char[N];
    int idx = 0;
    Stack<Character> stack = new Stack<>();
     
    for(char c:str.toCharArray()) { // 1. 토큰 읽고
      if(Character.isDigit(c)) { // 2. 피연산자면 출력
        cs[idx++] = c;
      } else { // 연산자면, 토큰이 스택의 top보다 우선순위가 높아질 때 까지 pop(출력)한 후 stack에 추가
        while(!stack.isEmpty() && !hasPriority(c, stack.peek())) {
          cs[idx++] = stack.pop();
        }
        stack.push(c);
      }
    }
    while(!stack.isEmpty()) {
      cs[idx++] = stack.pop(); // 종료하면 남은 것 모두 pop
    }
    return cs;
  }
   
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = 10;
     
    for(int t=1; t<=T; t++) {
      int N = Integer.parseInt(br.readLine());
      String str = br.readLine();
 
      char[] cs = toPostfix(N, str);
      int result = calculatePostFix(cs);
       
      System.out.printf("#%d %d\n", t, result);
    }
  }
}