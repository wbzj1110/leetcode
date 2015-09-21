package org.wangbin.leetcode;

import java.util.LinkedList;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up
 * all the values along the path equals the given sum.
 * 
 * For example: Given the below binary tree and sum = 22, 5 / \ 4 8 / / \ 11 13 4 / \ \ 7 2 1 return
 * true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * 
 * @author wb
 * @date 2015-9-21 上午8:12:28
 */
public class _112PathSum {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(4);
        node.right = new TreeNode(8);
        node.right.left = new TreeNode(13);
        node.right.right = new TreeNode(4);
        node.right.right = new TreeNode(1);
        node.left.left = new TreeNode(11);
        node.left.left.left=new TreeNode(7);
        node.left.left.right=new TreeNode(2);
        System.out.println(new _112PathSum().hasPathSum2(node, 22));
        System.out.println(new _112PathSum().hasPathSum(node, 22));
    }
    /**
     * 递归。。其实每次用递归都有种。卧槽，我TM没想出其他好点的法子啊
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        sum-=root.val;
        if(root.left==null &&root.right==null){
            return sum==0;
        }else{
            return hasPathSum(root.right, sum)||hasPathSum(root.left, sum);
        }
    }
    /**
     * 迭代
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        LinkedList<Integer> values = new LinkedList<Integer>();
        values.add(root.val);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            int sumValue = values.poll();
            if(node.left==null && node.right==null &&sum == sumValue){
                return true;
            }
            if(node.left!=null){
                queue.add(node.left);
                values.add(sumValue+node.left.val);
            }
            if(node.right!=null){
                queue.add(node.right);
                values.add(sumValue+node.right.val);
            }
        }
        
        return false;
    }
}
