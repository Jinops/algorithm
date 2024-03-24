import java.io.*;
import java.util.*;

class Gear {
  boolean[] isSs;
  int left;
  int right;

  Gear(boolean[] isSs){
    this.isSs = isSs;
    left = 6;
    right = 2;
  }
  void rotate(int dir){
    // 1:R / -1:L
    updateLR(dir*-1);
  }

  private void updateLR(int n){
    left += n;
    right += n;
    if(left>=8) left = 0;
    if(right>=8) right = 0;
    if(left<0) left = 7;
    if(right<0) right = 7;
  }

  boolean isLeftS(){
    return isSs[left];
  }
  boolean isRightS(){
    return isSs[right];
  }
  boolean is12S(){
    int c12 = left;
    for(int i=0; i<2; i++){
      c12 += 1;
      if(c12>7){
        c12 = 0;
      }
    }
    return isSs[c12];
  }
}

class Main {
  static Gear[] gears;
  
  static void cascadeRotate(int from, int fromDir, boolean fromLeft, int[] dirs){
    int next = from+(fromLeft?1:-1);
    if(!(0<=next&&next<4)) return;
    int nextDir = fromDir*-1;
    
    if(fromLeft && gears[from].isRightS() != gears[next].isLeftS()){
      dirs[next] = nextDir;
      cascadeRotate(next, nextDir, fromLeft, dirs);
    } else if(!fromLeft && gears[next].isRightS() != gears[from].isLeftS()){
      dirs[next] = nextDir;
      cascadeRotate(next, nextDir, fromLeft, dirs);
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    gears = new Gear[4];
    for(int i=0; i<4; i++){
      boolean[] isSs = new boolean[8];
      String str = br.readLine();
      for(int j=0; j<8; j++){
        isSs[j] = str.charAt(j) == '1';
      }
      gears[i] = new Gear(isSs);
    }

    int K = Integer.parseInt(br.readLine()); // 회전 수
    for(int k=0; k<K; k++){
      st = new StringTokenizer(br.readLine());
      int gearNum = Integer.parseInt(st.nextToken()) - 1;
      int dir = Integer.parseInt(st.nextToken());

      int[] rotateDirs = new int[4];
      rotateDirs[gearNum] = dir;
      cascadeRotate(gearNum, dir, true, rotateDirs);
      cascadeRotate(gearNum, dir, false, rotateDirs);

      for(int i=0; i<4; i++){
        if(rotateDirs[i]!=0){
          gears[i].rotate(rotateDirs[i]);
        }
      }
    }

    int result = 0;
    for(int i=0; i<4; i++){
      result += gears[i].is12S()? Math.pow(2, i) : 0;
    }
    System.out.println(result);
  }
}
