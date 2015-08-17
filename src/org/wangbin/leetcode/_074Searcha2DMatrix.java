package org.wangbin.leetcode;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right. The first integer of each
 * row is greater than the last integer of the previous row. For example,
 * 
 * Consider the following matrix:
 * 
 * [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] Given target = 3, return
 * true.
 * 
 * @author wb
 * @date 2015-8-15 上午10:24:03
 */
public class _074Searcha2DMatrix {
	public static void main(String[] args) {
		int[][] ints = new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 },
				{ 23, 30, 34, 50 } };
		System.out.println(new _074Searcha2DMatrix().searchMatrix(ints, 3));
	}

	/**
	 * 行2分搜索，然后列2分搜索。。没了。。
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int low = 0;
		int high = matrix.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (matrix[mid][0] == target) {
				return true;
			} else if (matrix[mid][0] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		if (high < 0) {
			return false;
		}
		int row = high; // 当从while中跳出时，low指向的值肯定比target大，而high指向的值肯定比target小
		low = 0;
		high = matrix[0].length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (matrix[row][mid] == target) {
				return true;
			} else if (matrix[row][mid] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return false;
	}
	/**
	 * ......还有个方法，2维数组变1维，对整个进行2分，但是有个问题就是坐标确认。详情如下：
	 * 虽然本题看似是矩阵问题，但是本着搜索题目关键字为第一步的原则，可以找到：each row are
	 * sorted，每一行按照顺序也是sorted。同时也是数组保存。
	 * 
	 * 但是本题的难点就是如何将2D矩阵转换成1D，然后利用二分查找法来解决问题。转换的重点就在于每个点的位置，在矩阵表示中，我们习惯用（i，j）
	 * 来表示一个点，所以这就有碍于我们使用low high mid来指向需要的位置。为了解决问题，第一步就是需要将这个矩阵按照顺序拉成一条线。
	 * 
	 * 像题中的例子我可以将其转化为：
	 * 
	 * position: 0 1 2 3 4 5 6 7 8 9 10 11
	 * 
	 * values: 1 3 5 7 10 11 16 20 23 30 34 50
	 * 
	 * row: 0 0 0 0 1 1 1 1 2 2 2 2
	 * 
	 * column: 0 1 2 3 0 1 2 3 0 1 2 3
	 * 
	 * 
	 * 
	 * 其中：行数rows=3，列数columns=4
	 * 
	 * 
	 * 
	 * 如上，这个就是将2D矩阵转化成1行数组的对应表。所以对于二分查找法的初始值为：low=0，high=rows*columns-1（总共数值的个数，
	 * 因为从0开始所以减1）。而为了能够方便在given 2D
	 * matrix找到需要比对的值，我们还是需要确定行数和列数，通过上表可以看出，行数是position
	 * /columns，而列数是position%columns,
	 * 这样一来，就能很容易的在原矩阵中定位到所需要的值。剩下其他的解题思路，就与二分查找法一模一样了。
	 * 
	 * 时间复杂度O(log(rows*columns))
	 * 对数函数性质=O(log(rows)+log(colums))
	 */
	public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0||matrix==null)
            return false;
            
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int low = 0;
        int high = rows*cols-1;
        
        while(low<=high){
            int mid = (low+high)/2;
            int midValue = matrix[mid/cols][mid%cols];
            if(midValue == target)
                return true;
            else if(midValue < target)
                low = mid+1;
            else
                high = mid-1;
        }
        return false;
    }
}
