import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine());
    char[] originSounds = br.readLine().toCharArray();
    int minCount = 2000;
    
    for(int cycle=1; cycle<N; cycle++) {
      char[] sounds = originSounds.clone();
      int cnt = 0;
      
      for(int i=0; i<N; i++){
        if(sounds[i]=='#') {
          int idx = i;
          while(idx<sounds.length && sounds[idx]=='#') {
            sounds[idx] = '.';
            idx+=cycle;
          }
          if(cnt>=minCount) {
            break;
          } else {
            cnt++;
          }
        }
      }

      minCount = cnt;
      if(minCount == 1) {
        break;
      }
    }
    System.out.println(minCount);
  }
}
