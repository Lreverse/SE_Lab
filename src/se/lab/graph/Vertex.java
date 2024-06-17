package se.lab.graph;

import java.util.LinkedList;

/**
 * 这是一个节点类.
 */
public class Vertex {
    /**
     * 每个节点的名字.
     */
    private final String name;

    /**
     * 边表.
     */
    private final LinkedList<Edge> edgeList;

    /**
     * 节点编号.
     */
    private final int index;

    /**
     * 构造函数.
     *
     * @param name 节点名字
     * @param index 节点编号
     */
    public Vertex(final String name, final int index) {
        this.name = name;
        this.index = index;
        edgeList = new LinkedList<>();
    }

    /**
     * 获取名字.
     *
     * @return 节点的名字
     */
    public String getName() {
        return this.name;
    }

    /**
     * 获取节点编号.
     *
     * @return 节点编号
     */
    public int getIndex() {
        return index;
    }

    /**
     * 获取边表.
     *
     * @return 边表
     */
    public LinkedList<Edge> getEdgeList() {
        return edgeList;
    }
}
