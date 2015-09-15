package org.wangbin.leetcode;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * 
 * For example, Given n = 3, there are a total of 5 unique BST's.
 * 
 * 1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3
 * 
 * @author wb
 * @date 2015-9-15 上午9:43:59
 */
public class _096UniqueBinarySearchTrees {
    public static void main(String[] args) {

    }

    /**
     * 不会。。。。。参考的这个 <a>http://www.cnblogs.com/springfor/p/3884009.html</a>
     * 根据卡特兰数，C0Cn+1，因为leetcode输入的参数是n，所以为了避免混淆，这里递推式写成Ct+1,初始值为C0 = 1。
     * 
     * 原始的递推式是： Ct+1 += Ci*Ct-i (0<= i <=t) 现在令变量num=t+1，那么t=num-1
     * 
     * 所以原始递推式做变量替换得：Cnum += Ci*Cnum-1-i (0<= i <=num-1) 而num的取值范围是[1, n]因为C0已知。
     * 
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n == 0 || n == 1){
            return 1;
        }
        int[] C = new int[n + 1];
        C[0] = 1;
        // 递推式是Ct+1 += Ci*Ct-i(0<= i <= t)
        // 令num = t+1
        // 则 t = num-1;
        // 因此递推式化为：
        // Cnum += Ci*Cnum-1-i(0<=i<=num-1, 1<=num<=n)
        // C0 = 1

        for (int num = 1; num <= n; num++) {
            for (int i = 0; i <= num - 1; i++) {
                C[num] += C[i] * C[(num - 1) - i];
            }
        }
        return C[n];
    }

}
