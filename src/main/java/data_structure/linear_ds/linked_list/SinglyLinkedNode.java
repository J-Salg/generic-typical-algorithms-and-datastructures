package data_structure.linear_ds.linked_list;

class SinglyLinkedNode<T> extends Node<T> {

    private SinglyLinkedNode<T> next;

    SinglyLinkedNode(T data){
        super(data);

        this.next = null;
    }

    SinglyLinkedNode<T> getNext(){
        return next;
    }

    void setNext(SinglyLinkedNode<T> next){
        this.next = next;
    }

}
