package se.lab.graph;

import java.util.List;
import java.util.ArrayList;

/**
 * 有向图类.
 */
public class MyGraph {
    /**
     * 顶点数.
     */
    private int n;

    /**
     * 边数.
     */
    private int e;

    /**
     * 邻接表.
     */
    private final List<Vertex> adjlist;

    /**
     * 构造函数.
     */
    public MyGraph() {
        n = 0;
        e = 0;
        adjlist = new ArrayList<>();
    }

    /**
     * 获取顶点数.
     *
     * @return 顶点数
     */
    public int getN() {
        return n;
    }

    /**
     * 获取边数.
     *
     * @return 边数.
     */
    public int getE() {
        return e;
    }

    /**
     * 获取邻接表.
     * @return 邻接表
     */
    public List<Vertex> getAdjlist() {
        return adjlist;
    }

    /**
     * 添加节点.
     *
     * @param name 节点名字
     */
    public void addVertex(final String name) {
        Vertex flag = findVertex(name);
        if (flag == null) {
            adjlist.add(new Vertex(name, n));
            n++;
        }
/*        else {
            System.out.printf("se.lab.graph.Vertex(%s) already exists\n", name);
        }*/
    }

    /**
     * 添加边.
     *
     * @param sour 源点
     * @param dest 终点
     */
    public void addEdge(final String sour, final String dest) {
        Vertex node = findVertex(sour);
        if (node != null) {
            List<Edge> edgeList = node.getEdgeList();
            List<Edge> result;
            result = edgeList.stream().filter(node_tail -> node_tail.getTail().getName().equals(dest)).toList();

            if (result.isEmpty()) {
                // 如果有向边不存在，则将边加入到边表中
                Vertex nodeDest = findVertex(dest);
                edgeList.add(new Edge(nodeDest));
            } else {
                // 如果有向边存在，则权重加1（只会有一个元素，所以直接取出）
                result.getFirst().weightAdd1();
            }
            e++;
        } else {
            System.out.printf("se.lab.graph.Vertex(%s) is not generated yet\n", sour);
        }
    }

    /**
     * 寻找节点.
     *
     * @param name 待寻找的节点名字
     * @return 查找到的节点
     */
    public Vertex findVertex(final String name) {
        List<Vertex> result = adjlist.stream().filter(node -> node.getName().equals(name)).toList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.getFirst();
        }
    }

    /**
     * 打印有向图.
     */
    public void printGraph() {
        System.out.println("顶点数：" + n);
        System.out.println("边数：" + e + "\n");
        for (Vertex v : adjlist) {
            System.out.println("顶点" + v.getName());
            for (Edge edge : v.getEdgeList()) {
                System.out.printf("    尾部：%-15s权重：%-2d\n", edge.getTail().getName(), edge.getWeight());
            }
        }
    }
}
