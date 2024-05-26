import java.security.Key;

public interface HeapInterface<Key, Value> {

	public  void     insert(Key k, Value v);
	public  HeapItem deleteRoot(); // this is "dequeue-ing" the item off the Heap
	public  void     fixDown(int idx, int upTo); // aka bubble down or sift down
	public  void     fixUp(int idx); // aka bubble up or sift up
	public  boolean  isFull();
	public  void     heapSort();
	public  void     heapify(HeapItem[] arr);
	public void		 print();
}