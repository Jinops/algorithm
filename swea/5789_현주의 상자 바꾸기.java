import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			
			for(int i=0; i<q; i++) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				for(int j=l-1; j<r; j++) {
					arr[j] = i+1;
				}
			}
			
			String result = "";
			for(int num:arr) {
				result+=" "+num;
			}
			System.out.printf("#%d%s\n", t, result);
		}
	}
}
