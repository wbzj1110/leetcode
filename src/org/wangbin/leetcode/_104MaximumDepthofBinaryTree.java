package org.wangbin.leetcode;

import java.util.LinkedList;

/**
 * Given a binary tree, find its maximum depth.
 * 
 * The maximum depth is the number of nodes along the longest path from the root node down to the
 * farthest leaf node.
 * 
 * @author wb
 * @date 2015-9-17 上午8:19:16
 */
public class _104MaximumDepthofBinaryTree {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        System.out.println(new _104MaximumDepthofBinaryTree().maxDepth(node));
        System.out.println(new _104MaximumDepthofBinaryTree().maxDepth2(node));
    }
    /**
     * 广度优先遍历,非递归
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int curLevel = 1;
        int nextLevel = 0;
        int maxLen = 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            curLevel--;
            if(temp.left!=null){
                queue.add(temp.left);
                nextLevel++;
            }
            if(temp.right!=null){
                queue.add(temp.right);
                nextLevel++;
            }
            if(curLevel==0){
                curLevel = nextLevel;
                nextLevel = 0;
                maxLen++;
            }
        }
        return maxLen;
    }
    /**
     * 递归做法
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if(root==null){
            return 0;
        }
        int maxLeft = maxDepth2(root.left);
        int maxRight = maxDepth2(root.right);
        return Math.max(maxLeft, maxRight)+1;//加上根节点
        
    }
    
}
