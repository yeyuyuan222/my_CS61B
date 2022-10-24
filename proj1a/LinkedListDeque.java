public class LinkedListDeque<T>{
    private class Node{
        public T data;
        public Node next;
        public  Node prev;
        public Node(T item, Node pre, Node nxt){
            data=item;
            next=nxt;
            prev=pre;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        size=0;
        sentinel =new Node(null,null,null);
        sentinel.prev= sentinel;
        sentinel.next= sentinel;
    }

    /*Invariant: new item is added after sentinel:first*/
    public void addFirst(T item){
        size++;
        Node _first=sentinel.next;
        sentinel.next=new Node(item, sentinel, _first);
        _first.prev=sentinel.next;
    }
    public  void addLast(T item){
        size++;
        Node _last= sentinel.prev;
        _last.next=new Node(item,_last, sentinel);
        sentinel.prev=_last.next;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node cur= sentinel.next;
        while(cur!= sentinel){
            System.out.print(cur.data);
            System.out.print(' ');
            cur=cur.next;
        }
    }
    /*please remember to rebuild the links of next and *prev* after removal. */
    public T removeFirst(){
        if(size>0){
            size--;
            Node temp= sentinel.next;
            sentinel.next=temp.next;
            temp.next.prev=sentinel;
            T val=temp.data;
            temp=null;
            return val;
        }
        return null;
    }
    public T removeLast(){
        if(size>0){
            size--;
            Node temp= sentinel.prev;
            sentinel.prev=temp.prev;
            temp.prev.next=sentinel;
            T val=temp.data;
            temp=null;
            return val;
        }
        return null;
    }
    public T get(int index){
        if(index>=size){
            return null;
        }

        Node cur= sentinel.next;
        for(int i=0;i<index;++i){
            cur=cur.next;
        }
        return cur.data;
    }
    private T getRecursiveHelper(int index, Node cur){
        if(index==0){
            return cur.data;
        }
        else{
            return getRecursiveHelper(index-1,cur.next);
        }
    }
    public T getRecursive(int index){
        if(index>=size){
            return null;
        }
        else{
            return getRecursiveHelper(index,sentinel.next);
        }
    }
    
}