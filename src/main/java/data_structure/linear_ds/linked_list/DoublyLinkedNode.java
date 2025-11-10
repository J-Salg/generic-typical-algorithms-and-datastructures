package data_structure.linear_ds.linked_list;

class DoublyLinkedNode<T> extends Node<T>{

    private DoublyLinkedNode<T> next;
    private DoublyLinkedNode<T> prev;

    DoublyLinkedNode(T data){
        super(data);

        this.next = null;
        this.prev = null;
    }

    DoublyLinkedNode<T> getNext() {
        return next;
    }

    void setNext(DoublyLinkedNode<T> next) {
        this.next = next;
    }

    DoublyLinkedNode<T> getPrev() {
        return prev;
    }

    void setPrev(DoublyLinkedNode<T> prev) {
        this.prev = prev;
    }
}
