// WIP
import java.util.*;
import java.io.*;

public class Solution {
	static int find(int[] ps, int x) {
		if(ps[x]==x) return ps[x];
		return find(ps, ps[x]);
	}
	static void union(int[] ps, int x, int y) {
		ps[find(ps, ps[y])] = find(ps, ps[x]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			
			int[] ps = new int[N+1];
			for(int i=1; i<=N; i++) {
				ps[i] = i;
			}
			
			StringBuilder sb = new StringBuilder();
			
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				if(type==0) {
					union(ps, A, B);
				} else if (type==1) {
					int result = ps[A]==ps[B] ? 1 : 0;
					sb.append(result);
				}
			}
			
			System.out.printf("#%d %s", t, sb.toString());
		}
		
	}
}
