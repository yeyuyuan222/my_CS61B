import java.util.zip.CheckedInputStream;

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
            System.arraycopy(items,front+1,temp,capacity-size+rear+1,size-rear-1);
            //make sure the index arguments above are correct.
            front=capacity-size+rear;
        }
        items = temp;
    }
    /*the inc/dec functions don't change the values of front and rear, it returns a new value. 
    In case we just want to know the values after inc/dec, but not changing it for real. */
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
    /*pay attention to which comes first, inc/decreasing front/rear or assigning item */
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
    private void checkUsage(){
        double usage=((double)size)/(double)items.length;
         if(items.length>8 && usage<0.25){//usage<0.25
            resize(2*size);
        }
    }
    /*should not remove when size==0 */
    public T removeFirst(){
        if(size==0){
            return null;
        }
        size--;
        front=incFront();
        T val=items[front];
        checkUsage();
        return val;
    }
    public T removeLast(){
        if(size==0){
            return null;
        }
        size--;
        T val=items[rear];
        rear=decRear();
      checkUsage(); 
        return val;
    }
    public int size(){
        return size;
    }
    /*the argument 'index' refers to the  the order of the element in the abstrat list, 
     * not necessarily equals to the real index of array     */
    public T get(int index){
        if(index>=size){
            return null;
        }
        return items[(index+front+1)%items.length];
        //please remember to mod length, please. Otherwise it might cause 'out of bounds' error
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

            System.out.print(items[cur]);
            System.out.print(' ');
            cur=(cur+1)%items.length;
        }
    }
/*     public static void main(String args[]){
        
        ArrayDeque L=new ArrayDeque();

        L.addFirst(6);
        L.addFirst(5);
        L.addFirst(4);
        L.addFirst(3);
        L.addFirst(2);
         L.addFirst(1);
        System.out.print(L.size());
        System.out.print(L.size());
        L.addLast(7); 
        L.addLast(8);
        L.removeFirst();
       L.removeFirst();
L.removeFirst();
L.removeFirst();
L.removeFirst();
L.removeFirst();
L.removeFirst();


    }  */

}