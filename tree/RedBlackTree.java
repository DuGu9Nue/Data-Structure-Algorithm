package com.Data_Structure_Algorithm.tree;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	
	private class Node{
		private Key key;    //键
		private Value val;    //值
		private Node left, right;    //左右指针域
		private boolean color;    //和父节点连接的颜色
		private int size;    //对应子树的节点数
		
		public Node(Key key, Value val, boolean color, int size) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.size = size;
		}
	}
	
	public RedBlackTree() {	}
	
	private boolean isRed(Node x) {
		if(null == x) return false;
		return RED == x.color;
	}
	
	private int size(Node x) {
		if(null == x) return 0;
		return x.size;
	}
	
	public int size() {
		return size(root);
	}
	
	public boolean isEmpty() {
		return null == root;
	}
	
	public Value get(Key key) {
		if(null == key) throw new IllegalArgumentException("argument to get() is null !");
		return get(root, key);
	}
	
	private Value get(Node p, Key key) {
		while(null != p) {    //null == p 查找失败
			if(key.compareTo(p.key) == 0) return p.val;
			else if(key.compareTo(p.key) < 0) {
				p = p.left;
			} else { p = p.right; }
		}
		return null;
	}
	
	/**
	 * 左旋
	 * 坐旋和右旋一样的操作步骤（下面针对左旋步骤）：
	 * 1、让 x 节点的左孩子节点成为 h 节点的右孩子节点，这样才符合BST的规则
	 * 2、让 h 节点成为 x 节点的左孩子节点
	 * 3、把 h 节点的链的颜色赋值给 x 节点的链的颜色
	 * 4、h 节点的链的颜色置为红色
	 * 5、把 h 节点的 size 属性赋值给 x 节点的 size 属性
	 * 6、更新 h 节点的 size 属性: 左孩子节点数 + 右孩子节点数 + 自身1
 	 * @param h
	 * @return
	 */
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		
		x.color = x.left.color;
		x.left.color = RED;
		//等价于下面这两句话吗？猜测应该是等价的
//		x.color = h.color;
//		h.color = RED;
		
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	//右旋
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.color = x.right.color;
		x.right.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	//变色
	private void flipColors(Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}
	
	//节点的插入 1
	public void put(Key key, Value val) {
		if(null == key || null == val) { throw new IllegalArgumentException("argument to put() is null !"); }
		
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	//节点的插入 2
	private Node put(Node h, Key key, Value val) {
		if(null == h) { return new Node(key, val, RED, 1); } 
		
		int cmp = key.compareTo(h.key);
		//这里的 h.left = 赋值是不可少的，因为插入后可能会进行左旋右旋不能保证此时的 h 节点还是原来的 h 节点
		if(cmp < 0) h.left = put(h.left, key, val);
		else if(cmp > 0) h.right = put(h.right, key, val);
		else h.val = val;
		
		//左旋条件：右孩子节点链为红，左孩子节点链为黑
		if(isRed(h.right) && !isRed(h.left)) { h = rotateLeft(h); }
		//右旋条件：左孩子节点链为红，左 孙子 节点链为红
		if(isRed(h.left) && isRed(h.left.left)) { h = rotateRight(h); }
		//变色条件：左孩子节点链和右孩子节点链都是红
		if(isRed(h.left) && isRed(h.right)) { flipColors(h); }
		
		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	//求红黑树的高度 给外部的方法
	public int getHeight() {
		return getHeight(root);
	}
	
	//求红黑树的高度 内部的方法
	private int getHeight(Node p) {
		if (p == null) { return 0; }
		else {
			int lh = getHeight(p.left);
			int rh = getHeight(p.right);
			return lh > rh? lh + 1: rh + 1;
		}
	}
	
	//中序遍历
	public void showInOrder(Node root) {
		if(null == root) { return; }
		
		showInOrder(root.left);
		System.out.println(root.key + " ---> " + root.val);
		showInOrder(root.right);
	}
	
	public Node getRoot() {
		return this.root;
	}
	
}
