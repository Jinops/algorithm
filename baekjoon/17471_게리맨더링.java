// TODO https://www.acmicpc.net/problem/17471
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] getAdjNodes(int node, int adjCounts, boolean[][] matrix) {
		int[] adjNodes = new int[adjCounts];
		int count = 0;
		for(int i=0; i<matrix[node].length; i++) {
			if(matrix[node][i]==true) {
				adjNodes[count++] = i;
			}
		}
		return adjNodes;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] costs = new int[n];
		int[] adjCounts = new int[n];
		boolean[][] matrix = new boolean[n][n];
		
		// BFS로 서치하면서
		// 우선순위를 "h가 개선되는 것"을 우선으로
		// h는 전체 cost - 현재 구역의 cost 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			adjCounts[i] = Integer.parseInt(st.nextToken());
			
			for(int start=0; start<adjCounts[i]; start++) {
				int j = Integer.parseInt(st.nextToken())-1;
				matrix[i][j] = true;
				matrix[j][i] = true;
			}
		}
		
//		for(boolean[] m:matrix) {
//			System.out.println(Arrays.toString(m));
//		}
		
		for(int node=0; node<n; node++) {
			// TODO
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			queue.add(node);
			
			while(queue.size() < n) {
				// 1. 현재 queue의 인접 node를 찾는다
				// 2. 인접 node를 붙여도, 조건에 위배되지 않는지 확인한다.
				// 3. 인접 node를 붙였을 때, 결과 h가 개선되는지 확인한다.
				// 4. 
			}
			
		}
		
	}
}
