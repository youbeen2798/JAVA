public class selectionsort{

    public static int []a = {5,3,2,9,10};

    public static void swap(int []a , int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void selection_sort(int []a){
        int size = a.length;

        for(int i=0; i<size-1; i++){
            int min_idx = i;
            for(int j = i; j<size; j++){
                if(a[j] < a[min_idx]){
                    min_idx = j;
                }
            }
            swap(a,min_idx, i);
        }
    }

    public static void printarray(int []a){

        for(int i=0; i<a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        System.out.print("배열 전 원소: ");
        printarray(a);
        selection_sort(a);
        System.out.print("배열 후 원소: ");
        printarray(a);
    }
}
