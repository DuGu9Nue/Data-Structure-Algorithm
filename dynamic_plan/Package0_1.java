package com.DataStructure_Algorithm.dynamic_plan;

/**
 * 0-1 背包
 * https://blog.csdn.net/sinat_34022298/article/details/77653693
 * 
 * @author szspace
 */
public class Package0_1 {

	public static int[][] operation(int[] values, int[] weights, int maxWeight, int n) {
		int[][] ans = new int[n + 1][ maxWeight + 1];
		// 初始化，第一行，第一列 值设为 0（分别表示取0个物品，背包容量为0）
		for (int i = 1; i < maxWeight + 1; ++i) ans[0][i] = 0;
		for (int i = 1; i < n + 1; ++i) ans[i][0] = 0;
		
		// 动态规划-全局最优解包含局部最优解 
		for (int i = 1; i < n + 1; ++i) {  // 第 i 个物品 
			for (int j = 1; j < maxWeight + 1; ++j) { // 背包容量 j 
				if (j >= weights[i]) {
					// 不装当前 第 i 个物品的价值为 ans[i - 1][j];
					// 装当前第 i 个物品的价值 ans[i-1][j - weights[i]] + values[i];
					// 解读装：遍历每个可以装得下的容量 j，
					// 模拟：当前为容量为 j 的背包刚刚可以装得下 weights[i] ,即 weights[i] 为容量为 j 的背包装的最后一个物品
					// 所以得到：ans[i][j] = ans[i-1][ j - weights[i] ] + values[i];
					ans[i][j] = Math.max(ans[i - 1][j], ans[i - 1][j - weights[i]] + values[i]);
				}
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int[] values = {0,3,4,5,7};
		int[] weight = {0,2,3,4,5};
		int maxWeight = 9;
		int n = 4;
		int[][] ans = operation(values, weight, maxWeight, n);
		// 打印 ans 数组，每个背包容量对应的最大 values 就显而易见了 
		for(int i = 0; i < maxWeight + 1; ++i) { if(i == 0) {System.out.print("J\t");} else System.out.print(i + "\t");}
		System.out.println("\n--------------------------------------------------------------");
		for(int[] item : ans) {
			for (int it : item) System.out.print(it + "\t");
			System.out.println();
		}
	}

}
