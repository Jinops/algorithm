import java.util.*;
import java.io.*;

class Solution {
    static boolean isValid(String begin, String word){
        boolean diffFlag = false;
        for(int i=0; i<begin.length(); i++){
            if(begin.charAt(i) != word.charAt(i)){
                if(diffFlag){
                    return false;
                }
                diffFlag = true;
            }
        }
        return true;
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> cntQueue = new LinkedList<>();
        queue.add(begin);
        cntQueue.add(0);
        
        while(!queue.isEmpty()){
            String cur = queue.poll();
            int cnt = cntQueue.poll();
            if(cur.equals(target)){
                answer = cnt;
                break;
            }
            
            for(int i=0; i<words.length; i++){
                if(!visited[i] && isValid(cur, words[i])){
                    queue.add(words[i]);
                    cntQueue.add(cnt+1);
                    visited[i] = true;
                }
            }
        }
        
        return answer;
    }
}
