package org.wangbin.leetcode;

/**
 * 
 * @author wb
 * @date 2015-9-21 ����8:54:01
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
     * preOrder,������ת��Ϊ������Ϊ�գ���������¼������� ���ԣ����˱���һ�����linkedlist���� ����ÿ�μ�¼����������Ȼ��ݹ�������������
     * ÿ���ȼ�¼����������Ȼ���ʼ�����ڵ㣬֮�����¹����Ϊ������Ϊnull��������Ϊ�������
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
        // ��¼��������
        TreeNode left = root.left;
        TreeNode right = root.right;
        // ��ʼ�����ڵ�
        root.left = null;
        root.right = null;
        TreeNode tail = root;
        // ������������
        if (left != null) {
            tail.right = left;
            tail = helper(left);//��������ǰ�������
        }
        if (right != null) {
            tail.right = right;
            tail = helper(right);
        }
        return tail;
    }

    /**
     * ��hint����������������ʹ�������������������ֵ��Ϊ�µ��Һ��Ӵ����������ӱ�Ϊ�ա� ע����ǣ���Ϊ�Һ��ӻ���£�����Ϊ�˵ݹ���������Ҫ�ڸ���֮ǰ��ǰ�����Һ��ӡ�
     * ����������Ҫά��һ��ȫ�ֱ��������浱ǰ�������Ľڵ㡣
     * root��ǰ�������ÿ�ζ�������������������Ȼ��lastvisited������¼��һ�������ڵ��λ�ã�Ȼ��ÿ�ζ����õ�ǰ��root��ע���������޸���һ��root����Ϊ
     * ������Ϊ�գ�������Ϊ��ǰroot��root�����ƿڣ���ȷʵ��������Ҳ��Ϊɶ�¸�������߻���realRight��������ˣ�root��right����Ϊlastvisited���޸�
     * 
     * @param root
     */
    private TreeNode lastvisited = null;

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode realRight = root.right;
        if (lastvisited != null) {// ����ط��Ĳ�����ʵ���Ƕ�root�����Ĳ���
            lastvisited.left = null;
            lastvisited.right = root;
        }
        lastvisited = root;
        flatten2(root.left);
        flatten2(realRight);
    }
}
