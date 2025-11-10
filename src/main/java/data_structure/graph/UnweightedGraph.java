package data_structure.graph;

public class UnweightedGraph<T> extends AGraph<T> {

    public UnweightedGraph(boolean directed){
        super(directed);
    }

    @Override
    public void addEdge(T src, T dest) {
        adjMap.get(src).put(dest, DEFAULT_WEIGHT);
        if(!directed){
            adjMap.get(dest).put(src, DEFAULT_WEIGHT);
        }
    }

    @Override
    public String formatEdge(T dest, int weight) {
        return dest.toString();
    }

}
