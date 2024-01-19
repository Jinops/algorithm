import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tcCount = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=tcCount; tc++) {
			int size = Integer.parseInt(br.readLine());
			String[] map = new String[size];
			
			for(int i=0; i<size; i++) {
				map[i] = br.readLine();
			}
			
			int center = size/2;
			String str = "";
			for(int i=0; i<size/2+1; i++) {
				str+=map[i].substring(center-i, center+i+1);
			}
			for(int i=0; i<size/2; i++) {
				str+=map[center+i+1].substring(i+1, size-i-1);
			}
			
			int result = 0;
			for(int i=0; i<str.length(); i++) {
				result += Character.getNumericValue(str.charAt(i));
			}
			
			System.out.printf("#%d %d\n", tc, result);
		}
	}
}
