package data_structure.tree.avl;

public class AVLTree<T extends Comparable<T>> {

    private AVLNode<T> root;

    public AVLTree(){
        this.root = null;
    }

    public void insert(T value){
        this.root = insertAVL(this.root, value);
    }

    private AVLNode<T> insertAVL(AVLNode<T> node, T value) {
        if(node == null){
            return new AVLNode<>(value);
        }

        if(value.compareTo(node.getValue()) < 0){
            node.setLeft(insertAVL(node.getLeft(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRight(insertAVL(node.getRight(), value));
        } else{
            return node;
        }

        updateHeight(node);

        int balance = obtainBalance(node);

        if(balance > 1 && value.compareTo(node.getLeft().getValue()) < 0){
            return rightRotation(node);
        }
        if(balance < -1 && value.compareTo(node.getRight().getValue()) > 0){
            return leftRotation(node);
        }
        if(balance > 1 && value.compareTo(node.getLeft().getValue()) > 0){
            node.setLeft(leftRotation(node.getLeft()));
            return rightRotation(node);
        }
        if(balance < -1 && value.compareTo(node.getRight().getValue()) < 0){
            node.setRight(rightRotation(node.getRight()));
            return leftRotation(node);
        }

        return node;
    }

    private AVLNode<T> leftRotation(AVLNode<T> x) {
        AVLNode<T> y = x.getRight();
        AVLNode<T> T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private AVLNode<T> rightRotation(AVLNode<T> y) {
        AVLNode<T> x = y.getLeft();
        AVLNode<T> T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private int obtainBalance(AVLNode<T> node) {
        return (node == null) ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    private void updateHeight(AVLNode<T> node) {
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }

    private int height(AVLNode<T> node){
        return (node == null) ? 0 : node.getHeight();
    }

    public void delete(T value) {
        this.root = deleteRec(this.root, value);
    }

    private AVLNode<T> deleteRec(AVLNode<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeft(deleteRec(node.getLeft(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRight(deleteRec(node.getRight(), value));
        } else {

            if (node.getLeft() == null || node.getRight() == null) {
                AVLNode<T> temp = (node.getLeft() != null) ? node.getLeft() : node.getRight();

                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            }
            else {
                AVLNode<T> minNode = findMin(node.getRight());
                node.setValue(minNode.getValue());

                node.setRight(deleteRec(node.getRight(), minNode.getValue()));
            }
        }

        if (node == null) {
            return null;
        }

        updateHeight(node);

        int balance = obtainBalance(node);

        if (balance > 1 && obtainBalance(node.getLeft()) >= 0) {
            return rightRotation(node);
        }

        if (balance > 1 && obtainBalance(node.getLeft()) < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            return rightRotation(node);
        }

        if (balance < -1 && obtainBalance(node.getRight()) <= 0) {
            return leftRotation(node);
        }

        if (balance < -1 && obtainBalance(node.getRight()) > 0) {
            node.setRight(rightRotation(node.getRight()));
            return leftRotation(node);
        }

        return node;
    }


    private AVLNode<T> findMin(AVLNode<T> node) {
        AVLNode<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public void inOrder(){
        inOrderRec(this.root);
        System.out.println();
    }

    private void inOrderRec(AVLNode<T> node) {
        if(node != null){
            inOrderRec(node.getLeft());
            System.out.print(node.getValue() + " ");
            inOrderRec(node.getRight());
        }
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public int getHeight(){
        return this.root.getHeight();
    }

    private void setRoot(AVLNode<T> root) {
        this.root = root;
    }

    public boolean search(T value) {
        return searchRec(this.root, value);
    }

    private boolean searchRec(AVLNode<T> node, T value) {
        if (node == null) {
            return false;
        }

        int cmp = value.compareTo(node.getValue());
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return searchRec(node.getLeft(), value);
        } else {
            return searchRec(node.getRight(), value);
        }
    }
}
