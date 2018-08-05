package com.DataStructure_Algorithm.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 边类
class Edge implements Comparable<Edge> {
	public Integer begin, end, weight; 
	public Edge(Integer begin, Integer end, Integer weight) {
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge arg0) {
		if (this.weight > arg0.weight) return 1;
		else if (this.weight < arg0.weight) return -1;
		return 0;
	}
}

public class MiniSpanTree_Kruskal {
	// 图中的顶点数 
	private Integer numVertexs;
	// 图中的边数目 
	private Integer numEdges;
	// 最小生成树长度 
	private Double cnt;
	// 边集 
	private Edge[] edges;
	// parent 数组，并查集 
	private Integer[] parent;
	
	public MiniSpanTree_Kruskal() {}
	
	public void init() throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入顶点个数和边个数：");
		String[] strArr = in.readLine().split(" ");
		numVertexs = Integer.valueOf(strArr[0]);
		numEdges = Integer.valueOf(strArr[1]);
		cnt = 0D;
		parent = new Integer[numVertexs];
		edges = new Edge[numEdges];
		
		System.out.println("请输入 " + numEdges + " 条边: ");
		for (int i = 0; i < numEdges; ++i) {
			strArr = in.readLine().split(" ");
			Integer begin = Math.min(Integer.valueOf(strArr[0]), Integer.valueOf(strArr[1]));
			Integer end = Math.max(Integer.valueOf(strArr[0]), Integer.valueOf(strArr[1]));
			edges[i] = new Edge(begin, end, Integer.valueOf(strArr[2]));
		}
		in.close();
		System.out.println("****************************");
		Arrays.sort(edges);		
	}
	
	// 并查集 标记联通图 -- 很关键、核心
	public Integer find(Integer f) {
		while (parent[f] > 0) { f = parent[f]; }
		return f;
	}
	
	// Kruskal 生成最小生成树 
	public void kruskal() {
		Integer n, m;
		// 初始化 parent 数组 全是 0 
		Arrays.fill(parent, 0);
		
		for (int i = 0; i < numEdges; ++i) {
			n = find(edges[i].begin);
			m = find(edges[i].end);
			if(n != m) {    // 如果 n == m 表示为环路，则不加入
				parent[n] = m;
				System.out.println(edges[i].begin + " --> " + edges[i].end + " : " + edges[i].weight);
				cnt += edges[i].weight;
			}
		}
		System.out.println("最小生成树长度为：" + cnt);
	}
	
}
