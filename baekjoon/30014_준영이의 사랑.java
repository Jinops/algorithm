import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);
    
    Deque<Integer> queue = new LinkedList<>();
    boolean addLast = true;
    for(int i=N-1; i>=0; i--) {
      if(addLast) {
        queue.addLast(arr[i]);
      } else {
        queue.addFirst(arr[i]);
      }
      addLast = !addLast;
    }
    
    List<Integer> list = new ArrayList<>(queue);
    int result = list.get(list.size()-1) * list.get(0);
    for(int i=0; i<list.size()-1; i++) {
      result += list.get(i) * list.get(i+1);
    }
    
    System.out.println(result);
    System.out.println(queue.toString().replace("[", "").replace("]", "").replaceAll(",", ""));
  }
}
