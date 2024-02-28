// TODO:
import java.io.*;
import java.util.*;

public class Solution {
  static int[] getOrderedIdxArray(Integer[] nums) {
    ArrayList<Integer> originList = new ArrayList<>(Arrays.asList(nums));
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(nums));
    list.sort(Collections.reverseOrder());
    int[] idxArr = new int[list.size()];
    System.out.println(originList.toString());
    System.out.println(list.toString());
    for(int i=0; i<idxArr.length; i++) {
      System.out.println(list.get(i));
      idxArr[i] = originList.indexOf(list.get(i));
      list.set(i, -1);
    }
    return idxArr;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {

      st = new StringTokenizer(br.readLine());
      String str = st.nextToken();
      Integer[] nums = new Integer[str.length()];
      int cnt = Integer.parseInt(st.nextToken());
      
      for(int i=0; i<nums.length; i++) {
        nums[i] = Character.getNumericValue(str.charAt(i));
      }
      int[] idxArr = getOrderedIdxArray(nums);
      System.out.println(Arrays.toString(idxArr));
//      
//      System.out.printf("#%d %s\n", t, isPal?"YES":"NO");
    }
  }
}
