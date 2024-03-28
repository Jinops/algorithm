// 크루스칼 풀이
import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
	Island from;
	Island to;
	double cost;
	
	Edge(Island from, Island to, double E){
		this.from = from;
		this.to = to;
		this.cost = E * (Math.pow(from.x-to.x, 2) + Math.pow(from.y-to.y, 2));
	}
	
	@Override
	public int compareTo(Edge o) {
		double diff = this.cost - o.cost;
		if(diff==0) return 0;
		return (diff>0)?1:-1;
	}

	@Override
	public String toString() {
		return "Edge [" + from + "," + to + ", cost=" + cost + "]";
	}
}

class Island {
	int x;
	int y;
	int n;
	
	Island(int x, int y, int n){
		this.x = x;
		this.y = y;
		this.n = n;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}

public class Solution {
	static Island[] islands;
	static int[] ps;
	
	static int findSet(int x) {
		if(islands[ps[x]]!=islands[x]) {
			ps[x] = findSet(ps[x]);
		}
		return ps[x];
	}
	
	static void union(int x, int y) {
		ps[y] = ps[x];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			islands = new Island[N];
			ps = new int[N];
			
			String[] xs = br.readLine().split(" ");
			String[] ys = br.readLine().split(" ");
			
			for(int i=0; i<N; i++) {
				int X = Integer.parseInt(xs[i]);
				int Y = Integer.parseInt(ys[i]);
				islands[i] = new Island(X, Y, i);
				ps[i] = i;
			}
			
			double E = Double.parseDouble(br.readLine());
			
			int edgeCnt = N*(N-1)/2;
			Edge[] edges = new Edge[edgeCnt];
			int edgeIdx = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					Island from = islands[i];
					Island to = islands[j];
					edges[edgeIdx++] = new Edge(from, to, E);
				}
			}
			
			Arrays.sort(edges);
			
			int idx = 0;
			int curEdgeCnt = 0;
			double result = 0;
			while(curEdgeCnt<N-1) {
				Island from = edges[idx].from;
				Island to = edges[idx].to;
				
				int fromP = findSet(from.n);
				int toP = findSet(to.n);
				if(fromP!=toP) {
					union(fromP, toP);
					result += edges[idx].cost;
					curEdgeCnt++;
				}
				idx++;
			}
			System.out.printf("#%d %d\n", t, Math.round(result));
			
		}
	}
}
