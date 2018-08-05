package com.DataStructure_Algorithm.tree;

public class MiniSpanTree_KruskalTest {

	public static void main(String[] args) throws Exception{
		MiniSpanTree_Kruskal mst = new MiniSpanTree_Kruskal();
		mst.init();
		mst.kruskal();
	}

}

/**
6 10
0 2 1
3 5 2
1 4 3
2 5 4
1 2 5
2 3 5
0 3 5
0 1 6
2 4 6
4 5 6
****************************
0 --> 2 : 1
3 --> 5 : 2
1 --> 4 : 3
2 --> 5 : 4
1 --> 2 : 5
最小生成树长度为：15.0

**/