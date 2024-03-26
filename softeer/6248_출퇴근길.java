import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;

    static void DFS(List<Integer>[] edges, Set<Integer> set, int start, int end, int denied, boolean[] visited){
        if(visited[start] && start == end){
            for(int i=1; i<=n; i++){
                if(visited[i]){
                    set.add(i);
                }
            }
            return;
        }
        
        for(int next:edges[start]){
            if(!visited[next] && next!= denied){
                visited[next] = true;
                DFS(edges, set, next, end, denied, visited);
                visited[next] = false;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 개수

        List<Integer>[] edges = new LinkedList[n+1];
        List<Integer>[] edgesR = new LinkedList[n+1];
        for(int i=1; i<=n; i++){
            edges[i] = new LinkedList<Integer>();
            edgesR[i] = new LinkedList<Integer>();
        }
        
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges[from].add(to);
            edgesR[to].add(from);
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        // 1. from -> to를 직접 가는 경로
        // 2. from -> somewhere -> from -> to 가는 경로
        // = somewhere에서 from 가는 경로
        // = from에서 from을 가는 역경로를 계산한다.
        // 3. 반대(to -> from)도 마찬가지
    
        Set<Integer> fromSet = new HashSet<>(); // 출근길
        Set<Integer> toSet = new HashSet<>(); // 퇴근길

        DFS(edges, fromSet, from, to, 0, new boolean[n+1]);
        DFS(edges, toSet, to, from, 0, new boolean[n+1]);

        // System.out.println(fromSet.toString());
        // System.out.println(toSet.toString());
        
        DFS(edgesR, fromSet, from, from, to, new boolean[n+1]);
        DFS(edgesR, toSet, to, to, from, new boolean[n+1]);
        
        // System.out.println(fromSet.toString());
        // System.out.println(toSet.toString());

        fromSet.retainAll(toSet); // 교집합
        fromSet.remove(from);
        fromSet.remove(to);
        System.out.println(fromSet.size());
    }
}
