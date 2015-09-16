package org.wangbin.leetcode;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * 
 * Two binary trees are considered equal if they are structurally identical and the nodes have the
 * same value.
 * 
 * @author wb
 * @date 2015-9-16 ����9:06:22
 */
public class _100SameTree {
    public static void main(String[] args) {

    }
    /**
     * �ж�2��bst�Ƿ���ȣ��Ƚ�ÿ���ڵ��Ƿ���ȼ���
     * �������ڵ���ȣ���������Ҳ���
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null){
            return false;
        }
        return p.val==q.val && isSameTree(p.left, q.left)
                &&isSameTree(p.right, q.right);
    }


}
