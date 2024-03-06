import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
  public static void main(String args[]) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String str = br.readLine();
      int level = 0;
      int result = 0;
      for(int i=0; i<str.length(); i++) {
        char c = str.charAt(i);
        if(c=='(') {
          level += 1;
        } else if(str.charAt(i-1)=='(') { // ()
          level -= 1;
          result += level;
        } else if(str.charAt(i-1)==')') { // ))
          level -= 1;
          result += 1;
        }
          
      }
      result+= level;
      System.out.print(result);
  }
}
