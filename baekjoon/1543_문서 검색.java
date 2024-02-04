// TODO: 60% 실패
import java.io.*;
// 보이어 무어
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    String pattern = br.readLine();
    int result = 0;
    
    int idx = pattern.length()-1;
    int pLastIdx = pattern.length()-1;
    while(idx<str.length()) {
      if(str.charAt(idx)==pattern.charAt(pLastIdx)) {
        int backIdx = 0;
        boolean isCorrect = true;
        while(isCorrect && backIdx++<pLastIdx) {
          if(str.charAt(idx-backIdx)!=pattern.charAt(pLastIdx-backIdx)){
            isCorrect = false;
          }
        }
        if(isCorrect) {
          result += 1;
        }
        idx += pattern.length();
      } else if(pattern.indexOf(str.charAt(idx))>=0){
        idx += pLastIdx-pattern.lastIndexOf(str.charAt(idx)); // IndexOf 반례: baababbac | bbac
      } else {
        idx += pattern.length();
      }
    }
    
    System.out.print(result);
  }
}
