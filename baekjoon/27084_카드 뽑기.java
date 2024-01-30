// TODO
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static double getP(int[] arr) {
    double p = 0;
    double pFirst = 1;
    double pSecond = 1; 
    
    for(int i=0; i<arr.length-1; i++) {
      if(i!=arr.length-2) {
        pFirst *= 0.5;
      }
      pSecond = 1;
      for(int j=i+1; j<arr.length; j++) {
        if(j!=arr.length-1) {
          pSecond *= 0.5;
        }
        System.out.println(arr[i] + "," + arr[j] + " : " + (pFirst * pSecond));
        if(arr[i]!=arr[j]) {
          p += pFirst * pSecond;
        }
      }
    }
    System.out.println(p);
    System.out.println("---");
    return p;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N];
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    double p = getP(arr);
    System.out.println((Math.pow(2, N)-1)*p % (Math.pow(10, 9)+7));
  }
}
