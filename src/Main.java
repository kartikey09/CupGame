import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//AUTHOR - KARTIKEY RANA 

public class Main {
	
	static Integer[][][] dp;

	static class Pair {
		int[] arr;
		List<int[]> steps;
		
		Pair(int[] arr, List<int[]> steps){
			this.arr = arr;
			this.steps = steps;
		}
	}
	
	public static void main(String[] args) throws IOException {
		int[][] testCases = {{8,3,5,4}, {212,109,155,57}, {5,2,3,4}, {110,2,3,109}, {110,2,3,106}, {10,5,7,9}, {7,3,5,6}, {10,2,4,9}}; 
		for(int i = 0; i < testCases.length; i++) {
			int[] testCase = testCases[i];
			int x =testCase[0] , y = testCase[1], z = testCase[2];
			dp = new Integer[x + 1][y + 1][z + 1];
			System.out.println("\nTestCase " + i);
			minMovesToGetAns(x, 0, 0, x, y, z, testCase[3]);
		}
	}

	public static int minMovesToGetAns(int cup0Start, int cup1Start, int cup2Start, int lim0, int lim1, int lim2,
			int desiredAns) {

		Queue<Pair> q = new LinkedList<>();
		dp[cup0Start][cup1Start][cup2Start] = 0;
		List<int[]> ll_temp = new LinkedList<>();
		ll_temp.add(new int[] {cup0Start, cup1Start, cup2Start });
		q.add(new Pair(new int[] {cup0Start, cup1Start, cup2Start}, ll_temp));
		int time = 1;
		int size = 1;

		while (size-- > 0) {
			Pair p = q.remove();
			int[] curr = p.arr;
			
			int cup0 = curr[0], cup1 = curr[1], cup2 = curr[2];

			if (cup0 == desiredAns || cup1 == desiredAns || cup2 == desiredAns) {
				for(int[] a : p.steps) {
					System.out.println("cup1 : " + a[0] + " cup2 : " + a[1] + " cup3 : " + a[2]);
				}
				return time;
			}

			int tempCup0 = 0, tempCup1 = 0, tempCup2 = 0;
			if (cup0 > 0) {
				tempCup1 = Math.min(cup1 + cup0, lim1);
				tempCup0 = cup0 - (tempCup1 - cup1);

				if (dp[tempCup0][tempCup1][cup2] == null) {
					dp[tempCup0][tempCup1][cup2] = time;
					List<int[]> ll = new LinkedList<>(p.steps);
					ll.add(new int[] { tempCup0, tempCup1, cup2 });
					q.add(new Pair(new int[] { tempCup0, tempCup1, cup2 }, ll));
				}

				tempCup2 = Math.min(cup2 + cup0, lim2);
				tempCup0 = cup0 - (tempCup2 - cup2);
				if (dp[tempCup0][cup1][tempCup2] == null) {
					dp[tempCup0][cup1][tempCup2] = time;
					List<int[]> ll = new LinkedList<>(p.steps);
					ll.add(new int[] { tempCup0, cup1, tempCup2 });
					q.add(new Pair(new int[] { tempCup0, cup1, tempCup2 }, ll));
				}
			}

			tempCup0 = 0;
			tempCup1 = 0;
			tempCup2 = 0;

			if (cup1 > 0) {

				tempCup0 = Math.min(cup0 + cup1, lim0);
				tempCup1 = cup1 - (tempCup0 - cup0);

				if (dp[tempCup0][tempCup1][cup2] == null) {
					dp[tempCup0][tempCup1][cup2] = time;
					List<int[]> ll = new LinkedList<>(p.steps);
					ll.add(new int[] { tempCup0, tempCup1, cup2 });
					q.add(new Pair(new int[] { tempCup0, tempCup1, cup2 }, ll));
				}

				tempCup2 = Math.min(cup2 + cup1, lim2);
				tempCup1 = cup1 - (tempCup2 - cup2);

				if (dp[cup0][tempCup1][tempCup2] == null) {
					dp[cup0][tempCup1][tempCup2] = time;
					List<int[]> ll = new LinkedList<>(p.steps);
					ll.add(new int[] { cup0, tempCup1, tempCup2 });
					q.add(new Pair(new int[] { cup0, tempCup1, tempCup2 }, ll));
				}
			}

			tempCup0 = 0;
			tempCup1 = 0;
			tempCup2 = 0;

			if (cup2 > 0) {
				tempCup0 = Math.min(cup0 + cup2, lim0);
				tempCup2 = cup2 - (tempCup0 - cup0);

				if (dp[tempCup0][cup1][tempCup2] == null) {
					dp[tempCup0][cup1][tempCup2] = time;
					List<int[]> ll = new LinkedList<>(p.steps);
					ll.add(new int[] { tempCup0, cup1, tempCup2 });
					q.add(new Pair(new int[] { tempCup0, cup1, tempCup2 }, ll));
				}

				tempCup1 = Math.min(cup2 + cup1, lim1);
				tempCup2 = cup2 - (tempCup1 - cup1);
				if (dp[cup0][tempCup1][tempCup2] == null) {
					dp[cup0][tempCup1][tempCup2] = time;
					List<int[]> ll = new LinkedList<>(p.steps);
					ll.add(new int[] { cup0, tempCup1, tempCup2 });
					q.add(new Pair(new int[] { cup0, tempCup1, tempCup2 }, ll));
				}
			}
			if (size == 0) {
				size = q.size();
				time++;
			}

		}
		System.out.println("not possible");
		return -1;
	}

}


