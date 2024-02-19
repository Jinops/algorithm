import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int A;
	static int B;
	static int C;
	
	static long calculate(int exp) {
		if(exp==1) {
			return A%C;
		}
		long half = calculate(exp/2)%C;
		if(exp%2==1) {
			return half * half %C * A%C;
		}
		return half * half %C;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(calculate(B));
	}
}
