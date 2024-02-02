import java.util.*;

public class Main {
	static int N;
	static int result = 0;
	
	static boolean isValid(boolean[][] m, int i, int j) {
		if(m[i][j]) {
			return false;
		}
		if(i>0 && m[i-1][j] || i<N-1 && m[i+1][j]) { // 위아래에 true 있으면
			return false;
		}
		if(j>0 && m[i][j-1] || j<N-1 && m[i][j+1]) { // 좌우에 true 있으면
			return false;
		}
		return true;
	}
	
	static void set(boolean[][] m, int lastI, int lastJ, int cnt) {
		if(cnt == N) {
			result += 1;

			for(boolean[] b:m) {
				System.out.println(Arrays.toString(b));
			}
			System.out.println();
			return;
		}
		// TODO: last i & j
		int startI = lastI+1;
		int startJ = lastJ+1;
		if(startJ==N) {
			startI += 1;
			startJ = 0;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// TODO
				if(isValid(m, i, j)) {
					m[i][j] = true;
					set(m, i, j, cnt+1); // TODO: y,x
					m[i][j] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		boolean[][] m = new boolean[N][N]; // true일 때 배치불가

		set(m, 0, -1, 0);
		System.out.println(result);
	}
}
