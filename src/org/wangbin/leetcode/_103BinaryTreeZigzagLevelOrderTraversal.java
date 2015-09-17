package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left
 * to right, then right to left for the next level and alternate between).
 * 
 * For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7 return its zigzag level
 * order traversal as: [ [3], [20,9], [15,7] ] confused what "{1,#,2,3}" means? > read more on how
 * binary tree is serialized on OJ.
 * 
 * @author wb
 * @date 2015-9-17 上午7:50:02
 */
public class _103BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        List<List<Integer>> result = new _103BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(node);
        Util.showListInListInteger(result);

    }

    /**
     * 广度优先遍历，然后记录每层，放入最终答案的时候，来回逆序即可
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        List<Integer> tempLs = new LinkedList<Integer>();
        int curLevel = 1;
        int nextLevel = 0;
        boolean isReverse = false;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            curLevel--;
            tempLs.add(temp.val);
            if (temp.left != null) {
                queue.add(temp.left);
                nextLevel++;
            }
            if (temp.right != null) {
                queue.add(temp.right);
                nextLevel++;
            }
            if (curLevel == 0) {
                curLevel = nextLevel;
                nextLevel = 0;
                if (tempLs.size() != 0) {
                    if (isReverse) {
                        Collections.reverse(tempLs);
                        result.add(new ArrayList<Integer>(tempLs));
                        isReverse =false;
                    } else {
                        result.add(new ArrayList<Integer>(tempLs));
                        isReverse = true;
                    }
                }
                tempLs = new ArrayList<Integer>();
            }
        }
        return result;
    }
}
