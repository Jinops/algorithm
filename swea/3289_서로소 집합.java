import java.util.*;
import java.io.*;

public class Solution {
	static int find(int[] ps, int x) {
		if (x!=ps[x]) {
			ps[x] = find(ps, ps[x]);
		}
		return ps[x];
	}
	
	static void union(int[] ps, int x, int y) {
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

				int pA = find(ps, A);
				int pB = find(ps, B);
				
				if(type==0) {
					if(pA!=pB) {
						union(ps, pA, pB);
					}
				} else if (type==1) {
					int result = ps[pA]==ps[pB] ? 1 : 0;
					sb.append(result);
				}
			}
			
			System.out.printf("#%d %s\n", t, sb.toString());
		}
		
	}
}
