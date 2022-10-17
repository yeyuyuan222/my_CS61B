public class ArrayDeque<T>{
    T []items;
    int size;
    int front,rear;
    /*invariant: front points to the position before the first item;
    rear points to the last item;
    if rear==front, the deque is empty.
    if (rear+1) % length == front, the deque is full. */
    public ArrayDeque(){
        items= (T[])new Object[8];
        size=0;
        front=rear=0;
    }
    public void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        if(front<=rear) {
            System.arraycopy(items, front + 1, temp, 0, size);
        }else{
            System.arraycopy(items,0,temp,0,rear+1);
            System.arraycopy(items,front+1,temp,capacity-size+front,size-front-1);
            front=capacity-size+front-1;
        }
        items = temp;
    }
    private int incFront(){
        return (front+1)%items.length;
    }
    private int decFront(){
        return (front-1)%items.length;
    }
    private int incRear(){
        return (rear+1)%items.length;
    }
    private int decRear(){
        return (rear-1)%items.length;
    }
    private boolean isFull(){
        return incRear()==front;
    }
    public void addFirst(T item) {
        if(isFull()){
            resize(2*size);
        }
        items[front] = item;
        front=decFront();
        size++;
    }
    public void addLast(T item){
        if(isFull()){//full
            resize(2*size);
        }
        rear=incRear();
        items[rear]=item;
        size++;
    }
    public T removeFirst(){
        size--;
        front=incFront();
        return items[front];
    }
    public T removeLast(){
        size--;
        T val=items[rear];
        rear=decRear();
        return val;
    }
    public int size(){
        return size;
    }
    public T get(int index){
        if(index>=size){
            return null;
        }
        return items[index];
    }
}