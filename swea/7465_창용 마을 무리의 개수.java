import java.util.*;
import java.io.*;

public class Solution {
	static int[] ps;
	static void makeSet(int N) {
		ps = new int[N+1];
		for(int i=1; i<=N; i++) {
			ps[i] = i;
		}
	}
	static int findSet(int x) {
		if(ps[x]!=x) {
			ps[x] = findSet(ps[x]);
		}
		return ps[x];
	}
	static void union(int x, int y) {
		ps[y] = x;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			makeSet(N);
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				int pa = findSet(a);
				int pb = findSet(b);

				if(pa!=pb) {
					union(pa, pb);
				}
			}
			
			Set<Integer> set = new HashSet<>();
			for(int i=1; i<=N; i++) {
				set.add(findSet(ps[i]));
			}
			
			System.out.printf("#%d %s\n", t, set.size());
		}
		
	}
}
