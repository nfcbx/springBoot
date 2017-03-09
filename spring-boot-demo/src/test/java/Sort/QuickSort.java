package Sort;

import java.util.Random;

public class QuickSort {

	public static void main(String[] args) {
		Random random = new Random();

		int[] arr = new int[10];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(50);
		}
		System.out.print("排序前：");
		System.out.println(printArray(arr));
		System.out.println();
		
		quickSort(arr, 0, arr.length - 1);
		
		System.out.print("排序后：");
		System.out.println(printArray(arr));
		System.out.println();

	}
	
	/**
	 * 基本思想：
	 * 选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，
	 * 将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,
	 * 然后再用同样的方法递归地排序划分的两部分。
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	private static int partition(int[] arr, int low, int high) {

		int key = arr[low];// key值
		while (low < high) {

			while (low < high && arr[high] >= key) {// 从右向左小循环
				high--;
			}
			
			arr[low] = arr[high]; // 比key值小的移到低端

			while (low < high && arr[low] <= key) {// 从左向右小循环
				low++;
			}

			arr[high] = arr[low]; // 比key值大的 移到高端

			System.out.println(printArray(arr));
			System.out.println("low: " + low + "    high: " + high);
		}

		arr[low] = key;// 把轴元素放在轴所在地位置

		return low;// 返回轴所在的位置
	}

	private static void quickSort(int data[], int low, int high) {
		System.out.println("调用： " + low);
		// 递归
		int q;
		if (low < high) {
			q = partition(data, low, high);
			quickSort(data, q + 1, high);// 对q左边进行分类
			quickSort(data, low, q - 1);// 对q右边进行分类
		}
	}
	
	
	public static String printArray(int[] arr) {
		StringBuffer str = new StringBuffer();
		for (int i : arr) {
			str.append(i + " , ");
		}
		return str.toString();
	}

}
