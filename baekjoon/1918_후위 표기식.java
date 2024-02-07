import java.io.*;
import java.util.*;
 
public class Main {
 
  static boolean hasPriority(char c, char compare) {
    if(c=='(') return true; // stack 밖에서는 '(' 우선순위 최상
    if(c!='(' && compare=='(') return true; // stack 안에서는 '(' 우선순위 최하
    if((c=='*' || c=='/') && (compare=='+' || compare=='-')) return true;
    return false;
  }
 
  static String toPostfix(String str) { // 후위로 변경
    StringBuilder sb = new StringBuilder();
    Stack<Character> stack = new Stack<>();
 
    for(char c:str.toCharArray()) { // 1. 토큰 읽고
      if(Character.isAlphabetic(c)) { // 2. 피연산자면 출력
        sb.append(c);
      } else if(c==')' && !stack.isEmpty()){ // 오른쪽괄호이면, 스택 top이 왼쪽 괄호가 될 떄 까지 pop 및 출력
          while(stack.peek()!='(') {
            sb.append(stack.pop());
          }
            stack.pop();
        } else { // 왼쪽 괄호or연산자면, 토큰이 스택의 top보다 우선순위가 높아질 때 까지 pop(출력)한 후 stack에 추가
        while(!stack.isEmpty() && !hasPriority(c, stack.peek())) {
          sb.append(stack.pop());
        }
        stack.push(c);
      }
    }
    while(!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    return sb.toString();
  }
 
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    System.out.println(toPostfix(str));
  }
}
