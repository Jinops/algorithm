import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		Map<Integer, ArrayList<Integer>> edges = new HashMap<>();
		List<Integer> visited = new ArrayList<>();
		int[] costs = new int[n+1];
		for(int i=0; i<costs.length; i++) {
			costs[i] =Integer.MAX_VALUE;
		}
		costs[x] = 0;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(edges.get(start)==null) {
				edges.put(start, new ArrayList<Integer>());
			}
			edges.get(start).add(end);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(x);
		
		while(queue.size()>0) { // BFS
			int startNode = queue.poll();
			if(costs[startNode]>k) {
				break;
			}
			ArrayList<Integer> adjNodes = edges.get(startNode);
			if(adjNodes!=null) {
				for(int node:adjNodes) {
					if(!visited.contains(node)) {
						visited.add(node);
						queue.add(node);
						costs[node] = Math.min(costs[node], costs[startNode]+1);
					}
				}
			}
		}
		
		String result = "";
		for(int i=1; i<costs.length; i++) {
			if(costs[i]==k) {
				result += i+"\n";
			}
		}
		System.out.print((result.equals("")?-1:result));
	}
}
