package com.DataStructure_Algorithm.tree;
import com.DataStructure_Algorithm.tree.RedBlackTree;

public class RedBlackTreeTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		RedBlackTree rbt = new RedBlackTree<Integer, String>();
		
		for(int i = 0; i < 20; ++i) {
			rbt.put(i, String.valueOf(20 - i));
		}
		
		rbt.showInOrder(rbt.getRoot());
	}

}
