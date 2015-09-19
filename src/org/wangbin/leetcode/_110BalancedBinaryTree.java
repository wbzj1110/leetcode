package org.wangbin.leetcode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
 * the two subtrees of every node never differ by more than 1.
 * 
 * @author wb
 * @date 2015-9-19 ����9:31:17
 */
public class _110BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        System.out.println(new _110BalancedBinaryTree().isBalanced2(node));
    }

    /**
     * �ݹ�����ж��Ƿ���ƽ�������
     * �ȿ����ڵ��������������ƽ���������Ȼ������������
     * ���Ե�Ч�ʺõͣ���Ҫ�����еĽڵ㶼����һ�飬ʱ�临�Ӷ���
     * O��NlogN��
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        int height = getHeight(root.left) - getHeight(root.right);
        if(Math.abs(height)>1){
            return false;
        }else{
            return isBalanced(root.left)&&isBalanced(root.right);
        }
    }

    private int getHeight(TreeNode root) {
        // TODO Auto-generated method stub
        if(root == null){
            return 0;
        }
        return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }
    public boolean isBalanced2(TreeNode root) {
        if(checkBalance(root) ==-1){
            return false;
        }else{
            return true;
        }
    }

    private int checkBalance(TreeNode root) {
        // TODO Auto-generated method stub
        if(root==null){
            return 0;
        }
        int leftHeight = checkBalance(root.left);
        if(leftHeight==-1){
            return -1;
        }
        int rightHieght = checkBalance(root.right);
        if(rightHieght==-1){
            return -1;
        }
        if(Math.abs(rightHieght-leftHeight) >1){
            return -1;
        }else{
            return Math.max(rightHieght, leftHeight)+1;
        }
    }

}
