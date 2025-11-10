package data_structure.tree.heap;

public class HeapNode<T> {
    private T value;

    public HeapNode(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
