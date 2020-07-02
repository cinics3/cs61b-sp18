import java.util.Scanner;

public class DrawTriangle {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the n of Triangle:");
		int n = sc.nextInt();
		drawTriangle(n);
	}

	public static void drawTriangle(int n) {
		for(int i = 1; i <= n; i ++) {
			for(int j = 1; j <= i; j ++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
