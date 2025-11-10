package data_structure.tree.b;

class BNode<T> {

    private T[] keys;
    private int minDegree;
    private BNode<T>[] children;
    private int numKeys;
    private boolean isLeaf;

    @SuppressWarnings("unchecked")
    BNode(int minDegree, boolean isLeaf){
        this.minDegree = minDegree;
        this.isLeaf = isLeaf;
        this.keys = (T[]) new Comparable[2 * minDegree - 1];
        this.children = new BNode[2 * minDegree];
        this.numKeys = 0;
    }

    public T[] getKeys() {
        return keys;
    }

    public void setKeys(T[] keys) {
        this.keys = keys;
    }

    public int getMinDegree() {
        return minDegree;
    }

    public void setMinDegree(int minDegree) {
        this.minDegree = minDegree;
    }

    public BNode<T>[] getChildren() {
        return children;
    }

    public void setChildren(BNode<T>[] children) {
        this.children = children;
    }

    public int getNumKeys() {
        return numKeys;
    }

    public void setNumKeys(int numKeys) {
        this.numKeys = numKeys;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isFull(){
        return numKeys == (2 * this.minDegree - 1);
    }
}
