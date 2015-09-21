package org.wangbin.leetcode;

/**
 * 
 * @author wb
 * @date 2015-9-21 上午8:54:01
 */
public class _114FlattenBinaryTreetoLinkedList {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(5);
        node.right.right = new TreeNode(6);
        node.left.right = new TreeNode(4);
        node.left.left = new TreeNode(3);
        // new _114FlattenBinaryTreetoLinkedList().flatten(node);
        new _114FlattenBinaryTreetoLinkedList().flatten2(node);
        System.out.println(node);
    }

    /**
     * preOrder,但是是转换为左子树为空，右子树记录的情况。 所以，除了遍历一遍加入linkedlist以外 可以每次记录左右子树，然后递归左右子树即可
     * 每次先记录左右子树，然后初始化根节点，之后重新构造成为左子树为null，右子树为所求的树
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root);
        System.out.println(root);
    }

    private TreeNode helper(TreeNode root) {
        // TODO Auto-generated method stub
        if (root == null) {
            return null;
        }
        // 记录左右子树
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 初始化根节点
        root.left = null;
        root.right = null;
        TreeNode tail = root;
        // 连接左右子树
        if (left != null) {
            tail.right = left;
            tail = helper(left);//这样就是前序遍历了
        }
        if (right != null) {
            tail.right = right;
            tail = helper(right);
        }
        return tail;
    }

    /**
     * 如hint所给出，这道题就是使用先序遍历，遍历到的值作为新的右孩子存起来，左孩子变为空。 注意的是，因为右孩子会更新，所以为了递归右子树，要在更新之前提前保存右孩子。
     * 整个程序需要维护一个全局变量，保存当前所遍历的节点。
     * root的前序遍历，每次都是先左子树右子树，然后lastvisited用来记录上一个遍历节点的位置，然后每次都是让当前的root【注意这样会修改上一个root】变为
     * 左子树为空，右子树为当前root的root。很绕口，但确实这样。这也是为啥下个程序里边会有realRight这个变量了，root的right会因为lastvisited而修改
     * 
     * @param root
     */
    private TreeNode lastvisited = null;

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode realRight = root.right;
        if (lastvisited != null) {// 这个地方的操作，实际是对root副本的操作
            lastvisited.left = null;
            lastvisited.right = root;
        }
        lastvisited = root;
        flatten2(root.left);
        flatten2(realRight);
    }
}
