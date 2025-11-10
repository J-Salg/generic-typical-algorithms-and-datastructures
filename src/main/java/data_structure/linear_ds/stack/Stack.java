package data_structure.linear_ds.stack;

public class Stack<T> {

    private SNode<T> top;
    private int size;

    public Stack(){
        this.top = null;
        this.size = 0;
    }

    public void push(T element){
        SNode<T> newNode = new SNode<>(element);
        newNode.setNext(this.top);
        this.top = newNode;

        this.size++;
    }

    public T pop(){

        if(this.size == 0){
            throw new IllegalStateException("Cannot pop from an empty stack.");
        }

        T element = this.top.getData();

        this.top = this.top.getNext();

        this.size--;

        return element;
    }

    public T peek() {
        if (this.size == 0) {
            throw new IllegalStateException("Cannot peek from an empty stack.");
        }
        return this.top.getData();
    }

    public int getSize() {
        return this.size;
    }

    public void printStack() {
        SNode<T> current = this.top;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

}
