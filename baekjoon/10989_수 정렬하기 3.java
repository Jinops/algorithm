import java.io.*;
// Counting Sort 구현
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] datas = new int[N];
    int[] results = new int[N];
    int max = 0;
    for (int i=0; i<datas.length; i++) {
      datas[i] = Integer.parseInt(br.readLine());
      max = Math.max(max, datas[i]);
    }

    // cnts배열: i:입력값, elem:등장횟수. 
    int[] cnts = new int[max+1];
    for (int i=0; i<datas.length; i++) {
      cnts[datas[i]] += 1;
    }
    // i 증가에 따른 누적합 (추후 중복값 처리를 위해)
    for (int i=1; i<cnts.length; i++) {
      cnts[i] += cnts[i-1];
    }
    
    // cnts의 값-1이 입력값의 순서
    for (int i=datas.length-1; i>=0; i--) {
      int data = datas[i];
      int idx = --cnts[data]; // 중복값을 처리해줌
      results[idx] = data;
    }
    
    for (int result:results) {
      bw.write(result+"\n");
    }
    bw.flush();
  }
}
