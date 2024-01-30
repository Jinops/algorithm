import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] arr;
  static int bestResult;
  static LinkedList<Integer> bestResutList;
  
  static int calculate(LinkedList<Integer> list) {
    int result = list.get(0) * list.get(N-1);
    for(int i=0; i<N-1; i++) {
      result += list.get(i) * list.get(i+1);
    }
    return result;
  }
  
  static void run(LinkedList<Integer> list) {
    if(list.size() == N) {
      int result = calculate(list);
      if(result > bestResult) {
        bestResult = result;
        bestResutList = (LinkedList<Integer>) list.clone();
      }
    }
    for(int n:arr) {
      if(!list.contains(n)) {
        list.add(n);
        run(list);
        list.pop();
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    N = Integer.parseInt(br.readLine());
    arr = new int[N];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    run(new LinkedList<Integer>());
    System.out.println(bestResult);System.out.println(bestResutList.toString().replace("[", "").replace("]", "").replaceAll(",", ""));
  }
}
