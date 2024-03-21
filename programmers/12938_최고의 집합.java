// 시간초과
import java.util.*;
import java.io.*;

class Solution {
    static int S;
    static int maxMul = 0;
    static List<Integer> maxList = new ArrayList<>();
    
    public int mul(List<Integer> list){
        int result = 1;
        for(int n:list){
            result *= n;
        }
        return result;
    }
    
    public void nCr(int start, int n, int r, List<Integer> list, int sum){
        if(sum>S){
            return;
        }
        if(r==0){
            System.out.println(list.toString());
            int mul = mul(list);
            if(sum==S && mul>maxMul){
                maxList = new LinkedList<>(list);
            }
            return;
        }
        for(int i=start; i<=n; i++){
            list.add(i);
            nCr(i, n, r-1, list, sum+i);
            list.remove(list.size()-1);
        }
    }
    
    public int[] solution(int n, int s) {
        S = s;
        nCr(1, 9, n, new ArrayList<>(), 0);
        if(maxList.size()==0){
            int[] answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        int[] answer = new int[maxList.size()];
        for(int i=0; i<maxList.size(); i++) {
            answer[i] = maxList.get(i);
        }
        return answer;
    }
}