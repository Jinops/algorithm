import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[] times = new int[num];
		
		for(int i=0; i<num; i++) {
			times[i] = sc.nextInt();
		}
		Arrays.sort(times);
		
		int result = 0;
		for (int i=0; i<num; i++) {
			for (int j=0; j<=i; j++) {
				result += times[j];
			}
		}
		
		System.out.println(result);
	}
}
