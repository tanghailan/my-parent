package com.kanavi.test.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class demo {
    public static void main(String[] args) {
        int[][] arr =  new int [][]{{1,4},{2,5},{8,9}};
        arr = jiaoji(arr);
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static int[][] jiaoji(int[][] arr) {
        List<int[]> tmp_arr = new ArrayList<>();
        if (arr.length == 0 || arr == null) return tmp_arr.toArray(new int[0][]);
        // 对起点终点进行排序
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < arr.length) {
            int left = arr[i][0];
            int right = arr[i][1];
            // 如果有重叠，循环判断哪个起点满足条件
            while (i < arr.length - 1 && arr[i + 1][0] <= right) {
                i++;
                right = Math.max(right, arr[i][1]);
            }
            // 将现在的区间放进res里面
            tmp_arr.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }
        return tmp_arr.toArray(new int[0][]);
    }
}
