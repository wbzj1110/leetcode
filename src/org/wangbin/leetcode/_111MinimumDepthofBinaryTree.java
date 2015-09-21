package org.wangbin.leetcode;

import java.util.LinkedList;

/**
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the root node down to the
 * nearest leaf node.
 * 
 * @author wb
 * @date 2015-9-21 上午7:53:28
 */
public class _111MinimumDepthofBinaryTree {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        node.right.right = new TreeNode(8);
        System.out.println(new _111MinimumDepthofBinaryTree().minDepth(node));
        System.out.println(new _111MinimumDepthofBinaryTree().minDepth2(node));
    }

    /**
     * 递归解法呗，注意一个地方当左右子树有一个为0的时候，需要查看另一个子树
     * 
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int minLeft = minDepth(root.left);
        int minRight = minDepth(root.right);
        if (minLeft == 0 || minRight == 0) {
            return minLeft >= minRight ? minLeft + 1 : minRight + 1;
        }
        return Math.min(minLeft, minRight) + 1;
    }

    /**
     * 迭代
     * 
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int curLevel = 1;
        int nextLevel = 0;
        int dept = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLevel--;
            //Input:[0]         Output:2   Expected:1
            if(node.left==null&&node.right==null){
                return dept;
            }
            if (node.left != null) {
                nextLevel++;
                queue.add(node.left);
            }
            if (node.right != null) {
                nextLevel++;
                queue.add(node.right);
            }
            if (curLevel == 0) {
                curLevel = nextLevel;
                nextLevel = 0;
                dept++;
            }
        }
        return dept;
    }
}
