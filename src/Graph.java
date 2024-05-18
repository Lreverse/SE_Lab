import java.util.*;

public class Graph {
    private int n;  // 顶点数
    private int e;  // 边数
    private final List<Vertex> Adjlist;

    public Graph() {
        Adjlist = new ArrayList<>();
    }

    public int getN() {
        return n;
    }

    public int getE() {
        return e;
    }

    public void addVertex(String name) {
        Vertex flag = findVertex(name);
        if (flag == null) {
            Adjlist.add(new Vertex(name));
            n++;
        }
//        else {
//            System.out.printf("Vertex(%s) already exists\n", name);
//        }
    }

    public void addEdge(String sour, String dest) {
        Vertex node = findVertex(sour);
        if (node != null) {
            List<Edge> EdgeList = node.getEdgeList();
            List<Edge> result = EdgeList.stream().filter(node_tail -> node_tail.getTail().equals(dest)).toList();

            if (result.isEmpty()) {
                // 如果有向边不存在，则将边加入到边表中
                EdgeList.add(new Edge(dest));
            } else {
                // 如果有向边存在，则权重加1（只会有一个元素，所以直接取出）
                result.getFirst().weightAdd1();
            }
            e++;
        } else {
            System.out.printf("Vertex(%s) is not generated yet\n", sour);
        }
    }

    private Vertex findVertex(String name) {
        List<Vertex> result = Adjlist.stream().filter(node->node.getName().equals(name)).toList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.getFirst();
        }
    }

    public void printGraph() {
        System.out.println("顶点数：" + n);
        System.out.println("边数：" + e + "\n");
        for (Vertex v : Adjlist) {
            System.out.println("顶点" + v.getName());
            for (Edge edge : v.getEdgeList()) {
                System.out.printf("    尾部：%-10s权重：%-2d\n", edge.getTail(), edge.getWeight());
            }
        }
    }
}
