package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to
 * right, level by level).
 * 
 * For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7 return its level order
 * traversal as: [ [3], [9,20], [15,7] ] confused what "{1,#,2,3}" means? > read more on how binary
 * tree is serialized on OJ.
 * 
 * @author wb
 * @date 2015-9-16 上午9:34:33
 */
public class _102BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        List<List<Integer>> result = new _102BinaryTreeLevelOrderTraversal().levelOrder(node);
        Util.showListInListInteger(result);
    }
    /**
     * 广度优先遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return Collections.EMPTY_LIST;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int curLevCnt = 1;  
        int nextLevCnt = 0;  
        List<Integer> temp = new ArrayList<Integer>();
        while(!queue.isEmpty()){
            TreeNode nodeTemp = queue.poll();
            curLevCnt--;
            temp.add(nodeTemp.val);
            if(nodeTemp.left!=null){
                nextLevCnt++;
                queue.add(nodeTemp.left);
            }
            if(nodeTemp.right!=null){
                nextLevCnt++;
                queue.add(nodeTemp.right);
            }
            if(curLevCnt==0){
                curLevCnt = nextLevCnt;
                nextLevCnt =0;
                result.add(new ArrayList<Integer>(temp));
                temp = new ArrayList<Integer>();
            }
            
        }
        return result;
    }

}
