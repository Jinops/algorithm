import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tcCount = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=tcCount; tc++) {
			String str = br.readLine();
			int level = 0;
			int result = 0;
			for(int i=0; i<str.length(); i++) {
				char c = str.charAt(i);
				if(c=='(') {
					level += 1;
				} else if(str.charAt(i-1)=='(') { // ()
					level -= 1;
					result += level;
				} else if(str.charAt(i-1)==')') { // ))
					level -= 1;
					result += 1;
				}
				
			}
			result+= level;
			System.out.printf("#%d %d\n", tc, result);
		}
	}
}
