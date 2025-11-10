package data_structure.tree.bst;

public class BinarySearchTree<T extends Comparable<T>> {

    private BSTNode<T> root;

    public void insert(T value){
        this.root = insertRec(this.root, value);
    }

    private BSTNode<T> insertRec(BSTNode<T> node, T value) {
        if(node == null){
            return new BSTNode<>(value);
        }

        int cmp = value.compareTo(node.getData());
        if(cmp < 0){
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (cmp > 0) {
            node.setRight(insertRec(node.getRight(), value));
        } else{
            System.out.println("Duplicate value ignored: " + value);
        }

        return node;
    }

    public void delete(T value){
        this.root = deleteRec(this.root, value);
    }

    private BSTNode<T> deleteRec(BSTNode<T> node, T value) {
        if(node == null){
            return null;
        }

        int cmp = value.compareTo(node.getData());
        if(cmp < 0){
            node.setLeft(deleteRec(node.getLeft(), value));
        } else if (cmp > 0) {
            node.setRight(deleteRec(node.getRight(), value));
        }else{
            if(node.getLeft() == null){
                return node.getRight();
            }else if(node.getRight() == null){
                return node.getLeft();
            }

            BSTNode<T> minNode = findMin(node.getRight());
            node.setData(minNode.getData());
            node.setRight(deleteRec(node.getRight(), minNode.getData()));
        }

        return node;
    }

    private BSTNode<T> findMin(BSTNode<T> node) {
        while(node.getLeft() != null){
            node = node.getLeft();
        }

        return node;
    }

    public boolean contains(T value){
        return containsRec(this.root, value);
    }

    private boolean containsRec(BSTNode<T> node, T value) {
        if(node == null){
            return false;
        }

        int cmp = value.compareTo(node.getData());
        if(cmp < 0){
            return containsRec(node.getLeft(), value);
        }else if(cmp > 0){
            return containsRec(node.getRight(), value);
        }

        return true;
    }

    public void inorder(){
        inorderRec(this.root);
        System.out.println();
    }

    private void inorderRec(BSTNode<T> node) {
        if(node != null){
            inorderRec(node.getLeft());
            System.out.print(node.getData() + " ");
            inorderRec(node.getRight());
        }
    }

    public void preorder(){
        preorderRec(this.root);
        System.out.println();
    }

    private void preorderRec(BSTNode<T> node) {
        if(node != null){
            System.out.print(node.getData() + " ");
            preorderRec(node.getLeft());
            preorderRec(node.getRight());
        }
    }

    public void postorder(){
        postorderRec(this.root);
        System.out.println();
    }

    private void postorderRec(BSTNode<T> node) {
        if(node != null){
            postorderRec(node.getLeft());
            postorderRec(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public boolean search(T value) {
        return searchRec(this.root, value);
    }

    private boolean searchRec(BSTNode<T> node, T value) {
        if (node == null) {
            return false;
        }

        int cmp = value.compareTo(node.getData());
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return searchRec(node.getLeft(), value);
        } else {
            return searchRec(node.getRight(), value);
        }
    }

}
