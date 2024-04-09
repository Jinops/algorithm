import java.util.*;
import java.io.*;

// 1부터 100까지 자연수와, 그 등장횟수를 갖고 있는 class를 만든다
// 등장 횟수의 오름차순, 수의 오름차순으로 정렬한다
// 각 값을 꺼내서 새 배열을 만든다

class Num implements Comparable<Num>{
  int n;
  int cnt;
  
  public Num(int n, int cnt) {
    this.n = n;
    this.cnt = cnt;
  }

  @Override
  public int compareTo(Num o) {
    if(this.cnt!=o.cnt) return this.cnt-o.cnt;
    return this.n - o.n;
  }
}

class Main {
  static List<Integer> cal(int[] A) {
    Num[] nums = new Num[101];
    for(int i=0; i<=100; i++) {
      nums[i] = new Num(i, 0);
    }
    
    for(int i:A) {
      if(i==0) continue;
      nums[i].cnt++;
    }
    
    List<Integer> list = new ArrayList<>();
    
    Arrays.sort(nums);
    for(Num num:nums) {
      if(num.cnt==0) continue;
      list.add(num.n);
      list.add(num.cnt);
    }
    
    return list;
  }
  
  static int r;
  static int c;
  static int k;
  static int[][] A;
  
  static boolean isFinish() {
    if(!(r<A.length && c<A[0].length)) return false;
    return A[r][c] == k;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    r = Integer.parseInt(st.nextToken())-1;
    c = Integer.parseInt(st.nextToken())-1;
    k = Integer.parseInt(st.nextToken());
    
    A = new int[3][3];
    
    for(int i=0; i<3; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<3; j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    int time=0;
    
    while(!isFinish() && time++<100) {
      int w = A[0].length;
      int h = A.length;
      
      if(A.length >= A[0].length) {
        // R연산 (가로)
        List<Integer>[] list = new List[h];
        
        for(int i=0; i<h; i++) {
          list[i] = cal(A[i]);
          w = Math.max(w, list[i].size());
        }
        
        A = new int[h][w];
        for(int i=0; i<h; i++) {
          for(int j=0; j<list[i].size(); j++) {
            A[i][j] = list[i].get(j);
          }
        }
      } else {
        // C연산 (세로)
        List<Integer>[] list = new List[w];
        int newH = h;
        
        for(int j=0; j<w; j++) {
          int[] a = new int[h];
          for(int i=0; i<h; i++) {
            a[i] = A[i][j];
          }
          list[j] = cal(a);
          newH = Math.max(newH, list[j].size());
        }
        
        h = newH;
        
        A = new int[h][w];
        for(int j=0; j<w; j++) {
          for(int i=0; i<list[j].size(); i++) {
            A[i][j] = list[j].get(i);
          }
        }
      }
    }
    
    if(time==101) time = -1;
    System.out.println(time);
  }
}
