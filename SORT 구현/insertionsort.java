public class insertionsort{


    public static int []a = {5,6,78,3,4,78,34,5,34,6};

    public static void swap(int []a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void insertion_sort(int []a){
        int size= a.length;

        for(int i=1; i<size; i++){
            int target = a[i];
            int j  = i-1;
            while(j>=0 && a[j] > target){
                swap(a,i,j);
                j--;
                target = a[--i];
            }
        }
    }

    public static void printarray(int []a){
        for(int i=0; i<a.length;i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        System.out.print("배열 전 원소: ");
        printarray(a);
        insertion_sort(a);
        System.out.print("배열 후 원소: ");
        printarray(a);
    }
}
