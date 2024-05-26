import java.util.Scanner;

public class Driver {
    private static Scanner sc = new Scanner(System.in);
    private static Heap<Integer, String> heap;

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       print("Enter size of table:  ");
       int size = Integer.parseInt(sc.nextLine());
       Heap<Integer, String> heap = new Heap<Integer, String>(size);
       int option = 0;

        while(true) {
            menu();
            print("\nEnter option: ");
            option = Integer.parseInt(sc.nextLine());

            if(option ==0){
                break;
            }
            else if(option == 1){
                print("Size: ");
                size = Integer.parseInt(sc.nextLine()); //
                heap = new Heap(size);
            }
            else if(option == 2){
                print("Enter key(int): ");
                int key = Integer.parseInt(sc.nextLine());
                print("Enter value(String): ");
                String val = sc.nextLine();
                heap.insert(key, val);
            }
            else if(option == 3){
                heap.deleteRoot();
            }
            else if(option == 4){
                heap.print();
            }
            else if(option == 5){
                heap.heapSort();
            }
            else if(option == 6){
                HeapItem[] arr = new HeapItem[3];
                HeapItem h1 = new HeapItem(1, "t1");
                HeapItem h2 = new HeapItem(1, "t2");
                HeapItem h3 = new HeapItem(1, "t3");
                arr[0] = h1;
                arr[1] = h2;
                arr[2] = h3;
                heap.heapify(arr);
            }
        }
    }
    public static void menu(){
        print("\n\n0: Exit Program\n1: Create heap\n2: Insert\n3: Delete root\n4: print heap\n5: heapSort\n6: heapify");
    }
    public static void print(String s){
        System.out.println(s);
    }
    
}
