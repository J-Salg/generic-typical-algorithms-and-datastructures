package data_structure.linear_ds.linked_list;

abstract class Node<T> {

    private T data;

    Node(T data){
        this.data = data;
    }

    T getData(){
        return data;
    }

    void setData(T data){
        this.data = data;
    }

}
