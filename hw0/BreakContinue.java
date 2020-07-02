public class BreakContinue {
	public static void windowPosSum(int[] a, int n) {
		for(int i = 0; i < a.length; i ++) {
			if(a[i] >= 0) {
				int sum = 0;
				for(int j = i, k = 0; j < a.length && k <= n; j ++, k ++ ){
					sum += a[j];
				}
				a[i] = sum;
			}
			else continue;
		}
	}

	public static void main(String[] args) {
		int[] a = {1, 2, -3, 4, 5, 4};
		int n = 3;
		System.out.println(java.util.Arrays.toString(a));

		windowPosSum(a, n);

		System.out.println(java.util.Arrays.toString(a));

		int[] b = {1, -1, -1, 10, 5, -1};
		n = 2;

		System.out.println(java.util.Arrays.toString(b));

		windowPosSum(b, n);

		System.out.println(java.util.Arrays.toString(b));
	}
}
