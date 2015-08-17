package org.wangbin.leetcode;

/**
 * i, j分别从头尾开始遍历，面积 area = min(height[j], height[i]) * (j-i)，当height[i] <
 * height[j]时，此时面积 area = height[i] * (j-i); 由于height[i]是短板，不管跟谁组合，它能达到的最大面积取决于
 * j-i，而此时j-i的距离是最大的，因此，此面积即为以i为左边界的最大面积，然后++i；同理得j的变化。因为对于i,
 * j，总有一个是短板，每次是短板的就发生变化，因此覆盖了所有情况。 O(n)
 * 
 * @author wb
 * @version 2015-7-28 上午9:10:15
 */
public class _011ContainerWithMostWater {
	public static void main(String[] args) {
		
		int []ints = new int []{1,2,3,4,5,6};
		System.out.println(new _011ContainerWithMostWater().maxArea2(ints));
	}
	public int maxArea(int[] height) {
		int  i ,j;
		int maxArea = 0;
		i =0;
		j = height.length-1;
		int area=0;
		while(i <j ){
			if(height[i]<height[j]){
				area = height[i]*(j-i);
				i++;
			}else{
				area = height[j]*(j-i);
				j--;
			}
			maxArea = Math.max(maxArea, area);
		}
		
		return maxArea;
	}
	public int maxArea2(int[] height) {
        if(height == null || height.length == 0)
            return 0;
        
        int low = 0, high = height.length -1 ;  
        int max = 0;  
        while (low < high) {
         int area = (high-low)*Math.min(height[low], height[high]);
         
         max = Math.max(max, area);  
         if (height[low] < height[high])  
             low++;  
         else  
             high--;  
        }  
            return max;  
    }
}
