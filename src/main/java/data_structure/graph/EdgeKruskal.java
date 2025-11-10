package data_structure.graph;

public class EdgeKruskal<T> implements Comparable<EdgeKruskal<T>>{

    private final T src, dest;
    private final int weight;

    public EdgeKruskal(T src, T dest, int weight){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public T getSrc(){
        return src;
    }

    public T getDest(){
        return dest;
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public int compareTo(EdgeKruskal<T> other) {
        return Integer.compare(this.weight, other.weight);
    }

    public String toString(){
        return src + " - " + dest + " : " + weight;
    }
}
