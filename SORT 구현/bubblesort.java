public class bubblesort{
    
    public static int []a = {5,6,78,3,4,78,34,5,34,6};

    public static void swap(int []a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void bubble_sort(int []a){

        int size = a.length;

        for(int i=1; i<size; i++){
            for(int j=0; j<size-i; j++){
                if(a[j] > a[j+1]){
                    swap(a,j,j+1);
                }
            }
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
        bubble_sort(a);
        System.out.print("배열 후 원소: ");
        printarray(a);
    }
}
