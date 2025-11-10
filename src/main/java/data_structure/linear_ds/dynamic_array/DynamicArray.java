package data_structure.linear_ds.dynamic_array;

import java.util.Arrays;

public class DynamicArray<T> {

    private T[] data;

    private int size;
    private int capacity;

    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public DynamicArray(){
        this.capacity = DEFAULT_CAPACITY;
        this.data = (T[]) new Object[this.capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public DynamicArray(int initCapacity){
        if(initCapacity <= 0){
            throw new IllegalArgumentException("Initial capacity must be greater than 0.");
        }
        this.capacity = initCapacity;
        this.data = (T[]) new Object[this.capacity];
        this.size = 0;
    }

    public void add(T element){
        validateCapacity(this.size + 1);
        data[this.size++] = element;
    }
    
    public void add(int index, T element){
        validateIndexAdd(index);
        validateCapacity(this.size + 1);

        System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        this.data[index] = element;

        this.size++;
    }
    
    public void set(int index, T element){
        validateIndex(index);
        this.data[index] = element;
    }

    public T get(int index){
        validateIndex(index);
        return this.data[index];
    }

    public void remove(){
        if(this.size == 0){
            throw new IllegalStateException("Cannot remove from an empty array.");
        }
        this.data[--this.size] = null;
    }

    public void remove(int index){
        validateIndex(index);

        int moveValues = this.size - index - 1;
        if(moveValues > 0){
            System.arraycopy(this.data, index + 1, this.data, index, moveValues);
        }

        this.data[--this.size] = null;
    }

    private void validateIndex(int index) {
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (this.size - 1));
        }
    }

    private void validateIndexAdd(int index) {
        if(index < 0 || index > this.size){
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (this.size));
        }
    }

    private void validateCapacity(int valCapacity) {
        if (valCapacity > this.capacity) {
            resize(this.capacity * 2);
        }
    }

    private void resize(int newCapacity) {
        this.data = Arrays.copyOf(this.data, newCapacity);

        this.capacity = newCapacity;
    }

    public void showArray(){
        for(int i = 0; i < this.size; i++){
            System.out.print(this.data[i] + " ");
        }
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
}
