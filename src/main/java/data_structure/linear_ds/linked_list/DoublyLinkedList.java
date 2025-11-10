package data_structure.linear_ds.linked_list;

public class DoublyLinkedList<T> extends LinkedList<T> {

    private DoublyLinkedNode<T> head;
    private DoublyLinkedNode<T> tail;

    private DoublyLinkedNode<T> cursor;
    private DoublyLinkedNode<T> reverseCursor;

    public DoublyLinkedList() {
        super();
        this.head = null;
        this.tail = null;
    }

    @Override
    public void add(T element) {
        add(element, getSize() + 1);
    }

    @Override
    public void add(T element, int position) {
        validatePositionAdd(position);

        DoublyLinkedNode<T> newNode = new DoublyLinkedNode<>(element);

        if(position == 1){
            newNode.setNext(this.head);
            if(this.head != null){
                this.head.setPrev(newNode);
            }
            this.head = newNode;
            if(this.tail == null){
                this.tail = newNode;
            }
        } else if (position == getSize() + 1) {
            newNode.setPrev(this.tail);
            if(this.tail != null){
                this.tail.setNext(newNode);
            }
            this.tail = newNode;
        }else{
            DoublyLinkedNode<T> nextNode = getNode(position);
            DoublyLinkedNode<T> prevNode = nextNode.getPrev();
            prevNode.setNext(newNode);
            newNode.setPrev(prevNode);
            newNode.setNext(nextNode);
            nextNode.setPrev(newNode);
        }

        increaseSize();
    }

    public void remove(){
        remove(getSize());
    }

    @Override
    public void remove(int position) {
        validatePosition(position);

        DoublyLinkedNode<T> nodeToRemove = getNode(position);

        if(nodeToRemove.getPrev() != null){
            nodeToRemove.getPrev().setNext(nodeToRemove.getNext());
        }else{
            this.head = nodeToRemove.getNext();
        }

        if(nodeToRemove.getNext() != null){
            nodeToRemove.getNext().setPrev(nodeToRemove.getPrev());
        }else{
            this.tail = nodeToRemove.getPrev();
        }

        decreasedSize();
    }

    @Override
    public void showList() {
        DoublyLinkedNode<T> current = this.head;
        while (current != null){
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();

    }

    public void startCursor() {
        this.cursor = null;
    }

    public boolean advanceCursor() {
        if (this.cursor == null) {
            this.cursor = this.head;
        } else {
            this.cursor = this.cursor.getNext();
        }
        return this.cursor != null;
    }

    public T getCursor() {
        return (this.cursor != null) ? this.cursor.getData() : null;
    }

    public void startReverseCursor() {
        this.reverseCursor = null;
    }

    public boolean advanceReverseCursor() {
        if (this.reverseCursor == null) {
            this.reverseCursor = this.tail;   
        } else {
            this.reverseCursor = this.reverseCursor.getPrev(); 
        }
        return this.reverseCursor != null;
    }

    public T getReverseCursor() {
        return (this.reverseCursor != null) ? this.reverseCursor.getData() : null;
    }

    private DoublyLinkedNode<T> getNode(int position) {
        DoublyLinkedNode<T> current;
        if (position <= getSize() / 2) {
            current = this.head;
            for (int i = 1; i < position; i++)
                current = current.getNext();
        } else {
            current = this.tail;
            for (int i = getSize(); i > position; i--)
                current = current.getPrev();
        }
        return current;
    }

    private void validatePosition(int position) {
        if (position < 1 || position > getSize())
            throw new IndexOutOfBoundsException("Position must be between 1 and " + getSize());
    }

    private void validatePositionAdd(int position) {
        if (position < 1 || position > getSize() + 1)
            throw new IndexOutOfBoundsException("Position must be between 1 and " + (getSize() + 1));
    }
}
