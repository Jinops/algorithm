class Solution {
  public int[] solution(int n, int s) {
      int[] answer = {};
      if(n>s) {
          answer = new int[]{-1};
          return answer;
      }
      
      answer = new int[n];
      for(int i=0; i<n; i++){
          answer[i] = s/n;
      }
      
      int remain = s%n;
      int idx = n-1;
      while(remain-->0){
          answer[idx] += 1;
          idx = (idx==0)? n-1 : idx-1;
      }
      
      return answer;
  }
}
