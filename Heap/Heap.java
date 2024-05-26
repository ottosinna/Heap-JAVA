import java.security.Key;

class Heap<Key extends Comparable<Key>, Value> implements HeapInterface<Key, Value> {

	private HeapItem[] heap;
	private int currentPosition = -1;
	private int size;
	
	public Heap(int size) {
		heap = new HeapItem[size];
		this.size = size;
	}

	public void insert(Key k, Value v) {
		if(isFull()) {
			System.out.println("heap is full.");
			return;
		}
		
		if(k == null || v == null) {
			System.out.println("key or value is null, unable to insert.");
			return;
		}
		
		HeapItem item = new HeapItem(k,v);		
		heap[++currentPosition] = item;
		fixUp(currentPosition);
	}
	
	// this is "dequeue-ing" the item off the Heap (aka polling)
	public HeapItem deleteRoot() {
		// save root item in a tmp variable
		HeapItem root = heap[0];
		// replace root item with the last item that has been added to the heap and move currentPosition back by one
		heap[0] = heap[currentPosition--];
		// delete last item in the heap
		heap[currentPosition+1] = null;
		// fix down
		fixDown(0, -1);
		// return root item
		return root;
	} 
	
	public void fixDown(int index, int upTo) {
		
		if (upTo < 0) { 
			upTo = currentPosition;
		}
		while (index <= upTo) {
			
			int leftChild = 2 * index + 1;
			int rightChild = 2 * index + 2;
			
			if (leftChild <= upTo) {
				int childToSwap;
				if (rightChild > upTo) {
					childToSwap = leftChild;
				} else {
					HeapItem lc = heap[leftChild];
					HeapItem rc = heap[rightChild];
		
					Key lcKey = (Key) lc.key;
					Key rcKey = (Key) rc.key;					
					
					if(lcKey.compareTo(rcKey) > 0) {
						childToSwap = leftChild;
					}
					else {
						childToSwap = rightChild;
					}
					// or using ternary
					//childToSwap = (heap[leftChild] > heap[rightChild]) ? leftChild : rightChild;
				}
				
				HeapItem someItem = heap[index];
				HeapItem childToSwapItem = heap[childToSwap];
		
				Key someItemKey = (Key) someItem.key;
				Key childToSwapItemKey = (Key) childToSwapItem.key;	
					
				if (someItemKey.compareTo(childToSwapItemKey) < 0) {
					HeapItem tmp = heap[index];
					heap[index] = heap[childToSwap];
					heap[childToSwap] = tmp;
				} else {
					break;
				}
				index = childToSwap;
			} else {
				break;
			}
		}		
	}
	
	public void fixUp(int cp) {
		
		int parentIndex = (cp - 1) / 2;
		
		HeapItem t1 = heap[parentIndex];
		HeapItem t2 = heap[cp];
		
		Key k1 = (Key) t1.key;
		Key k2 = (Key) t2.key;		
		
		while (parentIndex >= 0 && k1.compareTo(k2) < 0) {
			
			HeapItem tmp = heap[parentIndex];
			
			//swap
			heap[parentIndex] = heap[cp];
			heap[cp] = tmp;
			
			// update indeces
			cp = parentIndex;
			parentIndex = (cp-1)/2;
			
			t1 = heap[parentIndex];
			t2 = heap[cp];
			k1 = (Key) t1.key;
			k2 = (Key) t2.key;
		}
	}
	
	public boolean isFull() {
		return currentPosition == heap.length - 1;
	}
	
	public void heapSort(){

        //Actually sorting by replacing root with last
        for (int i = 0; i < currentPosition; i++) {
            HeapItem tmp = heap[0]; //save root
            heap[0] = heap[currentPosition-i];
            heap[currentPosition - i] = tmp;
            fixDown(0, currentPosition-i-1); //fix down
        }
        //This is now no longer a heap...just a sorted array using heap
    }

    public void heapify(HeapItem[] arr){
        //Take random heap item array and make a heap
        for (int i = 0; i < arr.length; i++) {
            Key k = (Key) arr[i].key;
            Value v = (Value) arr[i].val;
            insert(k, v);
        }
    }

	public void print(){
		for(int i = 0; i < currentPosition; i++) {
			if(heap[i]!=null){
				HeapItem item = heap[i];
				System.out.println("(key: " + item.key + " : value: " + item.val + ")");
			}
		}
	}
}