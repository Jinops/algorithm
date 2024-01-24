import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
		
		while(queue.size() < n) {
			int node = getNearestNode(queue, edges, n); // 주변 연결안된 노드들을 뽑아서
			costs[node] = Math.min(costs[node], 2 + getMinCost(queue, costs, x));
			queue.add(node); // 연결을 시킨다
			System.out.println(node);
//			for(int adjNode:edges.get(node)) { // 연결된 노드 주면 노드에 대해
//				System.out.printf("ADJ %d\n", adjNode);
//				if(!queue.contains(adjNode) && ) { // queue에 없는 노드이고, 
//					costs[adjNode] = Math.min(costs[adjNode], 2); // cost를 계산해주다
//				}
//				System.out.printf("costs[%d] %d\n", adjNode, costs[adjNode]);
//			}
		}
		
		System.out.println(edges);
		
		for(int c:costs) {
			System.out.printf("%d ",c);
		}
	}
	
	static int getNearestNode(Queue<Integer> queue, Map<Integer, ArrayList<Integer>> edges, int n) {
		for(int start:edges.keySet()) {
			for(int end:edges.get(start)) {
				if(queue.contains(start) && !queue.contains(end)) {
					return end;
				}
			}
		}
		return -1;
	}
	static int getMinCost(Queue<Integer> queue, int[] costs, int start) {
		int min = Integer.MAX_VALUE;
		if(queue.size()==1) {
			return 0;
		}
		for(int node:queue) {
			if(node==start) {
				continue;
			}
			if(costs[node] < min) {
				min = costs[node];
			}
		}
		return min;
	}
}
