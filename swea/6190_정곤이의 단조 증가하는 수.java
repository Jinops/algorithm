import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static boolean isValid(int n) {
		String str = Integer.toString(n);
		int beforeNum = str.charAt(0);
		int currentNum;
		for(int i=1; i<str.length(); i++) {
			currentNum = str.charAt(i);
			if(beforeNum > currentNum) {
				return false;
			}
			beforeNum = currentNum;
		}
		return true;
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			
			int result = -1;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				
				for(int j=0; j<i; j++) {
					int multiplied = arr[i]*arr[j];
					if(isValid(multiplied) && multiplied>result) {
						result = multiplied;
					}
				}
			}
			
			System.out.printf("#%d %d\n", t, result);
		}
	}
}
