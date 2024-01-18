import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int result = 0;
			String input = br.readLine();
			int clapCount = 0;
			for(int i=0; i<input.length(); i++) {
				int num = Character.getNumericValue(input.charAt(i));
				if(num == 0) {
					continue;
				}
				if(clapCount >= i) {
					clapCount += num;
				} else {
					int needCount = i-clapCount;
					result += needCount;
					clapCount += needCount+num;
				}
			}
			System.out.printf("#%d %d\n", tc, result);
		}
	}
}
