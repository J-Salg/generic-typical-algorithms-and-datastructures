package data_structure.linear_ds.queue;

class QNode<T> {

    T data;
    QNode<T> next = null;

    QNode(T data){
        this.data = data;
        this.next = null;
    }

    T getData() {
        return data;
    }

    QNode<T> getNext() {
        return next;
    }

    void setNext(QNode<T> next) {
        this.next = next;
    }
}
