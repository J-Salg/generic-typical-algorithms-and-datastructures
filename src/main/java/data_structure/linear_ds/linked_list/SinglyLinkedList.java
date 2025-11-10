package data_structure.linear_ds.linked_list;

public class SinglyLinkedList<T> extends LinkedList<T> {

    private SinglyLinkedNode<T> head;
    private SinglyLinkedNode<T> cursor;

    public SinglyLinkedList() {
        super();

        this.head = null;
        this.cursor = null;
    }

    @Override
    public void add(T element) {
        add(element, getSize() + 1);
    }

    @Override
    public void add(T element, int position) {
        validatePositionAdd(position);

        SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(element);

        if(position == 1){
            newNode.setNext(this.head);
            this.head = newNode;
        }else{
            SinglyLinkedNode<T> prev = getNode(position - 1);
            newNode.setNext(prev.getNext());
            prev.setNext(newNode);
        }

        increaseSize();

    }

    public void remove(){
        remove(getSize());
    }

    @Override
    public void remove(int position) {
        validatePosition(position);

        if(position == 1){
            this.head = this.head.getNext();
        }else{
            SinglyLinkedNode<T> prev = getNode(position - 1);
            prev.setNext(prev.getNext().getNext());
        }

        decreasedSize();
    }

    @Override
    public void showList() {

        SinglyLinkedNode<T> current = this.head;
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

    private SinglyLinkedNode<T> getNode(int position){
        SinglyLinkedNode<T> current = this.head;
        for(int i = 1; i < position; i++){
            current = current.getNext();
        }

        return current;
    }

    private void validatePosition(int position) {
        if (position < 1 || position > getSize()) {
            throw new IndexOutOfBoundsException("Position must be between 1 and " + getSize());

        }
    }

    private void validatePositionAdd(int position) {
        if (position < 1 || position > getSize() + 1) {
            throw new IndexOutOfBoundsException("Position must be between 1 and " + getSize());
        }
    }

}
