package data_structure.linear_ds.stack;

class SNode<T> {

    T data;
    SNode<T> next;

    SNode(T data){
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public SNode<T> getNext() {
        return next;
    }

    public void setNext(SNode<T> next) {
        this.next = next;
    }
}
