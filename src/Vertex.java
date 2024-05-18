import java.util.LinkedList;
import java.util.List;

public class Vertex {
    private final String name;   // 每个结点的名字
    private final LinkedList<Edge> EdgeList;

    public Vertex(String name) {
        this.name = name;
        EdgeList = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    public LinkedList<Edge> getEdgeList() {
        return EdgeList;
    }
}
