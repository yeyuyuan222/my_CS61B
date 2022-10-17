public class ArrayDeque<T>{
    private T []items;
    private int size;
    private int front,rear;
    /*invariant: front points to the position before the first item;
    rear points to the last item;
    if rear==front, the deque is empty.
    if (rear+1) % length == front, the deque is full. */
    public ArrayDeque(){
        items= (T[])new Object[8];
        size=0;
        front=rear=0;
    }
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        if(front<=rear) {
            System.arraycopy(items, front + 1, temp, 0, size);
            front=capacity-1;
            rear=size-1;
        }else{
            System.arraycopy(items,0,temp,0,rear+1);
            System.arraycopy(items,front+1,temp,capacity-items.length+front+1,size-rear-1);
            front=capacity-items.length+front;
        }
        items = temp;
    }
    private int incFront(){
        return (front+1)%items.length;
    }
    private int decFront(){
        return (front-1+items.length)%items.length;
    }
    private int incRear(){
        return (rear+1)%items.length;
    }
    private int decRear(){
        return (rear-1+items.length)%items.length;
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
        if(size==0){
            return null;
        }
        if(size/items.length<0.25){//usage<0.25
            resize(2*size);
        }
        size--;
        front=incFront();
        return items[front];
    }
    public T removeLast(){
        if(size==0){
            return null;
        }
        if(size/items.length<0.25){//usage<0.25
            resize(2*size);
        }
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
        return items[index+front+1];
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void printDeque(){
        int cur=front+1;
        if(front==rear){
            return;
        }
        while(cur!=rear){

            System.out.print(items[cur].data);
            System.out.print(' ');
            cur=(cur+1)%items.length;
        }
    }
    public static void main(String args[]){
        
        ArrayDeque L=new ArrayDeque();
        L.addLast(0);
        System.out.print(L.size());
        L.addLast(2);
        L.addFirst(3);
        L.addLast(4);
        L.addLast(5);
        System.out.print(L.size());
        L.addFirst(7);
    }


}