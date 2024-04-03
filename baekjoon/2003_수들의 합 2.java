import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    int[] A = new int[N];
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
    
    int start = 0;
    int end = 0;
    
    int result = 0;
    int tmp = 0;
    
    while(start<N) {
      // start 포인터가 마지막 위치를 넘어가기 전까지 반복
      while(tmp<M && end <N) {
        // 구간합이 M보다 작다면, end를 증가시킨다
        tmp += A[end];
        end += 1;
      }
      if(tmp == M) {
        result += 1;
      }
      
      tmp -= A[start];
      start += 1;
      // 현재 시작점 기준으로 end를 최대한 늘린 뒤 확인했으므로, start도 증가
    }
    
    System.out.println(result);
  }
}
