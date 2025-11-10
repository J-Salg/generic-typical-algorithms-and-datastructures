package data_structure.linear_ds.queue;

public class Queue<T> {

    private QNode<T> front;
    private QNode<T> rear;

    private int size;

    public Queue(){
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T element){
        QNode<T> newNode = new QNode<>(element);
        if(this.size != 0){
            this.rear.setNext(newNode);
        }else{
            this.front = newNode;
        }
        this.rear = newNode;

        this.size++;
    }

    public T dequeue(){
        if(this.size == 0){
            throw new IllegalStateException("Cannot dequeue from an empty queue.");
        }

        T value = this.front.getData();
        this.front = this.front.getNext();

        this.size--;

        if(this.size == 0){
            this.rear = null;
        }

        return value;
    }

    public T peek(){
        if(this.size == 0){
            throw new IllegalStateException("Cannot peek from an empty queue.");
        }

        return this.front.getData();
    }

    public void printQueue(){
        QNode<T> current = this.front;
        while (current != null){
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

}
