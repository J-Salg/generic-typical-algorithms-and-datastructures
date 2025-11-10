package data_structure.tree.b;

public class BTree<T extends Comparable<T>> {

    private BNode<T> root;
    private int minDegree;

    public BTree(int minDegree){
        this.minDegree = minDegree;
        this.root = null;
    }

    public void insert(T key){
        if(this.root == null){
            this.root = new BNode<>(this.minDegree, true);
            this.root.getKeys()[0] = key;
            this.root.setNumKeys(1);
        }else{
            if(this.root.isFull()){
                BNode<T> newRoot = new BNode<>(minDegree, false);
                newRoot.getChildren()[0] = this.root;
                splitChild(newRoot, 0, this.root);
                this.root = newRoot;
            }
            insertNonFull(this.root, key);

        }
    }

    private void insertNonFull(BNode<T> node, T key) {
        int i = node.getNumKeys() - 1;

        if (node.isLeaf()) {
            while (i >= 0 && key.compareTo(node.getKeys()[i]) < 0) {
                node.getKeys()[i + 1] = node.getKeys()[i];
                i--;
            }
            node.getKeys()[i + 1] = key;
            node.setNumKeys(node.getNumKeys() + 1);
        } else {
            while (i >= 0 && key.compareTo(node.getKeys()[i]) < 0) {
                i--;
            }
            i++;

            if (node.getChildren()[i].isFull()) {
                splitChild(node, i, node.getChildren()[i]);
                if (key.compareTo(node.getKeys()[i]) > 0) {
                    i++;
                }
            }

            insertNonFull(node.getChildren()[i], key);
        }
    }

    private void splitChild(BNode<T> parent, int index, BNode<T> fullChild){
        BNode<T> newNode = new BNode<>(this.minDegree, fullChild.isLeaf());
        newNode.setNumKeys(this.minDegree - 1);

        for(int i = 0; i < this.minDegree - 1; i++){
            newNode.getKeys()[i] = fullChild.getKeys()[i + this.minDegree];
        }

        if(!fullChild.isLeaf()){
            for(int i = 0; i < this.minDegree; i++){
                newNode.getChildren()[i] = fullChild.getChildren()[i + this.minDegree];
            }
        }

        fullChild.setNumKeys(this.minDegree - 1);

        for(int i = parent.getNumKeys(); i >= index + 1; i--){
            parent.getChildren()[i + 1] = parent.getChildren()[i];
        }

        parent.getChildren()[index + 1] = newNode;

        for(int i = parent.getNumKeys() - 1; i >= index; i--){
            parent.getKeys()[i + 1] = parent.getKeys()[i];
        }

        parent.getKeys()[index] = fullChild.getKeys()[this.minDegree - 1];
        parent.setNumKeys(parent.getNumKeys() + 1);

    }

    public boolean search(T key) {
        return searchRecursive(this.root, key);
    }

    private boolean searchRecursive(BNode<T> node, T key) {
        if (node == null){
            return false;
        }

        int i = 0;
        while (i < node.getNumKeys() && key.compareTo(node.getKeys()[i]) > 0) {
            i++;
        }

        if (i < node.getNumKeys() && key.compareTo(node.getKeys()[i]) == 0) {
            return true;
        }

        if (node.isLeaf()) {
            return false;
        }

        return searchRecursive(node.getChildren()[i], key);
    }

    public void print() {
        printRecursive(this.root, 0);
    }

    private void printRecursive(BNode<T> node, int level) {
        if (node != null) {
            System.out.print("Level " + level + ": ");
            for (int i = 0; i < node.getNumKeys(); i++) {
                System.out.print(node.getKeys()[i] + " ");
            }
            System.out.println();
            for (int i = 0; i <= node.getNumKeys(); i++) {
                printRecursive(node.getChildren()[i], level + 1);
            }
        }
    }

    public void delete(T key) {
        if (this.root == null) {
            return;
        }

        deleteRecursive(this.root, key);

        if (this.root.getNumKeys() == 0) {
            if (!this.root.isLeaf()) {
                this.root = this.root.getChildren()[0];
            } else {
                this.root = null;
            }
        }
    }

    private void deleteRecursive(BNode<T> node, T key) {
        int idx = 0;
        while (idx < node.getNumKeys() && key.compareTo(node.getKeys()[idx]) > 0) {
            idx++;
        }

        if (idx < node.getNumKeys() && key.compareTo(node.getKeys()[idx]) == 0) {
            if (node.isLeaf()) {
                removeFromLeaf(node, idx);
            } else {
                removeFromNonLeaf(node, idx);
            }
        } else {
            if (node.isLeaf()) {
                return;
            }

            boolean flag = (idx == node.getNumKeys());

            if (node.getChildren()[idx].getNumKeys() < this.minDegree) {
                fill(node, idx);
            }

            if (flag && idx > node.getNumKeys()) {
                deleteRecursive(node.getChildren()[idx - 1], key);
            } else {
                deleteRecursive(node.getChildren()[idx], key);
            }
        }
    }

    private void removeFromLeaf(BNode<T> node, int idx) {
        for (int i = idx + 1; i < node.getNumKeys(); i++) {
            node.getKeys()[i - 1] = node.getKeys()[i];
        }
        node.getKeys()[node.getNumKeys() - 1] = null;
        node.setNumKeys(node.getNumKeys() - 1);
    }

    private void removeFromNonLeaf(BNode<T> node, int idx) {
        T key = node.getKeys()[idx];

        BNode<T> leftChild = node.getChildren()[idx];
        BNode<T> rightChild = node.getChildren()[idx + 1];

        if (leftChild.getNumKeys() >= this.minDegree) {
            T pred = getPredecessor(node, idx);
            node.getKeys()[idx] = pred;
            deleteRecursive(leftChild, pred);
        } else if (rightChild.getNumKeys() >= this.minDegree) {
            T succ = getSuccessor(node, idx);
            node.getKeys()[idx] = succ;
            deleteRecursive(rightChild, succ);
        } else {
            merge(node, idx);
            deleteRecursive(leftChild, key);
        }
    }

    private T getPredecessor(BNode<T> node, int idx) {
        BNode<T> cur = node.getChildren()[idx];
        while (!cur.isLeaf()) {
            cur = cur.getChildren()[cur.getNumKeys()];
        }
        return cur.getKeys()[cur.getNumKeys() - 1];
    }

    private T getSuccessor(BNode<T> node, int idx) {
        BNode<T> cur = node.getChildren()[idx + 1];
        while (!cur.isLeaf()) {
            cur = cur.getChildren()[0];
        }
        return cur.getKeys()[0];
    }

    private void fill(BNode<T> node, int idx) {
        if (idx != 0 && node.getChildren()[idx - 1].getNumKeys() >= this.minDegree) {
            borrowFromPrev(node, idx);
        } else if (idx != node.getNumKeys() && node.getChildren()[idx + 1].getNumKeys() >= this.minDegree) {
            borrowFromNext(node, idx);
        } else {
            if (idx != node.getNumKeys()) {
                merge(node, idx);
            } else {
                merge(node, idx - 1);
            }
        }
    }

    private void borrowFromPrev(BNode<T> node, int idx) {
        BNode<T> child = node.getChildren()[idx];
        BNode<T> sibling = node.getChildren()[idx - 1];

        for (int i = child.getNumKeys() - 1; i >= 0; i--) {
            child.getKeys()[i + 1] = child.getKeys()[i];
        }
        if (!child.isLeaf()) {
            for (int i = child.getNumKeys(); i >= 0; i--) {
                child.getChildren()[i + 1] = child.getChildren()[i];
            }
        }

        child.getKeys()[0] = node.getKeys()[idx - 1];

        if (!child.isLeaf()) {
            child.getChildren()[0] = sibling.getChildren()[sibling.getNumKeys()];
        }

        node.getKeys()[idx - 1] = sibling.getKeys()[sibling.getNumKeys() - 1];

        sibling.getKeys()[sibling.getNumKeys() - 1] = null;
        if (!sibling.isLeaf()) {
            sibling.getChildren()[sibling.getNumKeys()] = null;
        }

        child.setNumKeys(child.getNumKeys() + 1);
        sibling.setNumKeys(sibling.getNumKeys() - 1);
    }

    private void borrowFromNext(BNode<T> node, int idx) {
        BNode<T> child = node.getChildren()[idx];
        BNode<T> sibling = node.getChildren()[idx + 1];

        child.getKeys()[child.getNumKeys()] = node.getKeys()[idx];

        if (!child.isLeaf()) {
            child.getChildren()[child.getNumKeys() + 1] = sibling.getChildren()[0];
        }

        node.getKeys()[idx] = sibling.getKeys()[0];

        for (int i = 1; i < sibling.getNumKeys(); i++) {
            sibling.getKeys()[i - 1] = sibling.getKeys()[i];
        }
        if (!sibling.isLeaf()) {
            for (int i = 1; i <= sibling.getNumKeys(); i++) {
                sibling.getChildren()[i - 1] = sibling.getChildren()[i];
            }
        }

        sibling.getKeys()[sibling.getNumKeys() - 1] = null;
        if (!sibling.isLeaf()) {
            sibling.getChildren()[sibling.getNumKeys()] = null;
        }

        child.setNumKeys(child.getNumKeys() + 1);
        sibling.setNumKeys(sibling.getNumKeys() - 1);
    }

    private void merge(BNode<T> node, int idx) {
        BNode<T> child = node.getChildren()[idx];
        BNode<T> sibling = node.getChildren()[idx + 1];

        child.getKeys()[this.minDegree - 1] = node.getKeys()[idx];

        for (int i = 0; i < sibling.getNumKeys(); i++) {
            child.getKeys()[i + this.minDegree] = sibling.getKeys()[i];
        }

        if (!child.isLeaf()) {
            for (int i = 0; i <= sibling.getNumKeys(); i++) {
                child.getChildren()[i + this.minDegree] = sibling.getChildren()[i];
            }
        }

        for (int i = idx + 1; i < node.getNumKeys(); i++) {
            node.getKeys()[i - 1] = node.getKeys()[i];
        }
        for (int i = idx + 2; i <= node.getNumKeys(); i++) {
            node.getChildren()[i - 1] = node.getChildren()[i];
        }

        node.getKeys()[node.getNumKeys() - 1] = null;
        node.getChildren()[node.getNumKeys()] = null;

        child.setNumKeys(child.getNumKeys() + sibling.getNumKeys() + 1);
        node.setNumKeys(node.getNumKeys() - 1);
    }

    public BNode<T> getRoot() {
        return this.root;
    }

    public int getMinDegree() {
        return this.minDegree;
    }

}
