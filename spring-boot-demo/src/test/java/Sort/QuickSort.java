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
		for (int i : arr) {
			System.out.print(i + " , ");
		}
		System.out.println();
		
		quickSort(arr, 0, arr.length - 1);
		
		System.out.print("排序后：");
		for (int i : arr) {
			System.out.print(i + " , ");
		}
		System.out.println();

	}

	private static int partition(int[] arr, int i, int j) {

		int key = arr[i];// key值
		while (i < j) {

			while (i < j && arr[j] > key) {// 从右向左小循环
				j--;
				System.out.println(j);
			}
			if (arr[j] <= key) {// 判定填充
				arr[i] = arr[j];
			}

			while (i < j && arr[i] < key) {// 从左向右小循环
				i++;
				System.out.println(i);
			}

			if (arr[i] >= key) {// 判定填充
				arr[j] = arr[i];
			}

		}

		arr[i] = key;// 把轴元素放在轴所在地位置

		return i;// 返回轴所在的位置
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

}
