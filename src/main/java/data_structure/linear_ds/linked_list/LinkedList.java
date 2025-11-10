package data_structure.linear_ds.linked_list;

public abstract class LinkedList<T> {

    private int size;

    public LinkedList(){
        this.size = 0;
    }

    public abstract void add(T element);
    public abstract void add(T element, int position);
    public abstract void remove(int position);
    public abstract void showList();

    public int getSize(){
        return size;
    }

    public void increaseSize(){
        this.size++;
    }

    public void decreasedSize(){
        this.size--;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
