import javax.lang.model.util.ElementScanner6;

public class BinarySearch
{
    public static int binarySearch(int []arr, int lo, int hi, int target)
    {
        if(hi<=lo) return -1;
        int mid = lo + (hi-lo)/2;
        if(arr[mid] == target)
        {
            return mid;
        }
        else if(arr[mid] > target)
        {
            return binarySearch(arr, lo, mid, target);
        }
        else
        {
            return binarySearch(arr, mid+1, hi, target);
        }
    }

    public static int search(int[]arr, int target)
    {
        return binarySearch(arr, 0, arr.length, target);
    }

    public static void main(String[]args)
    {
        int[] searchfor = {1, 4, 8, 2, 9, 10, 44, 0};
        System.out.println(search(searchfor, 10));
    }
}