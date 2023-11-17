import java.util.Arrays;

public class main {

    private static final int MIN_MERGE = 32;

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Array before sorting: " + Arrays.toString(arr));

        timsort(arr, arr.length);

        System.out.println("Array after sorting: " + Arrays.toString(arr));
    }

    public static void timsort(int[] arr, int n) {
        if (n < 2) {
            return;
        }

        int minRun = minRunLength(MIN_MERGE);

        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, i, Math.min((i + minRun - 1), (n - 1)));
        }

        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
            int n1 = mid - left + 1;
            int n2 = right - mid;
    
            // Create temporary arrays
            int[] leftArr = new int[n1];
            int[] rightArr = new int[n2];
    
            // Copy data to temporary arrays leftArr[] and rightArr[]
            for (int i = 0; i < n1; i++) {
                leftArr[i] = arr[left + i];
            }
            for (int j = 0; j < n2; j++) {
                rightArr[j] = arr[mid + 1 + j];
            }
    
            // Merge the temporary arrays
    
            int i = 0, j = 0;
            int k = left;
    
            while (i < n1 && j < n2) {
                if (leftArr[i] <= rightArr[j]) {
                    arr[k] = leftArr[i];
                    i++;
                } else {
                    arr[k] = rightArr[j];
                    j++;
                }
                k++;
            }
    
            // Copy the remaining elements of leftArr[], if there are any
            while (i < n1) {
                arr[k] = leftArr[i];
                i++;
                k++;
            }
    
            // Copy the remaining elements of rightArr[], if there are any
            while (j < n2) {
                arr[k] = rightArr[j];
                j++;
                k++;
            }
        }
    

    public static int minRunLength(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }
}

