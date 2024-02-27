// TODO
import java.io.*;

public class Main {
  static String toString(int[] ip) {
    StringBuilder sb = new StringBuilder();
    for(int n:ip) {
      sb.append(n).append('.');
    }
    return sb.toString().substring(0, sb.length()-1);
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] ips = new int[N][4];
    int[] network = new int[4];
    int[] subnet = new int[4];
    
    for(int i=0; i<N; i++) {
      String[] ipsStr = br.readLine().split("\\.");
      for(int j=0; j<4; j++) {
        ips[i][j] = Integer.parseInt(ipsStr[j]);
      }
    }
    for(int i=0; i<4; i++) {
      network[i] = 255; // (1<<8)-1
      subnet[i] = 255;
      for(int[] ip:ips) {
        network[i] = network[i] & ip[i];
      }
      for(int[] ip:ips) {
        subnet[i] = subnet[i] & (network[i] ^ ip[i] ^ 255);
      }
    }
    System.out.println(toString(network));
    System.out.println(toString(subnet));
  }
}
