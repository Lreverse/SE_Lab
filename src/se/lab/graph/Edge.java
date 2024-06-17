package se.lab.graph;

/**
 * 边.
 */
public class Edge {
    /**
     * 边的权重.
     */
    private int weight;
    /**
     * 尾部结点标识.
     */
    private Vertex tail;

    /**
     * 初始化边.
     * @param tail 尾部顶点
     */
    public Edge(final Vertex tail) {
        this.tail = tail;
        this.weight = 1;
    }

    /**
     * 获取尾部顶点.
     * @return 尾部顶点
     */
    public Vertex getTail() {
        return tail;
    }

    /**
     * 更改尾部顶点.
     * @param tail 尾部顶点
     */
    public void setTail(final Vertex tail) {
        this.tail = tail;
    }

    /**
     * 获取边权重.
     * @return 边权重
     */
    public int getWeight() {
        return weight;
    }

    /**
     * 更改边权重.
     * @param weight 边权重
     */
    public void setWeight(final int weight) {
        this.weight = weight;
    }

    /**
     * 增加边权重.
     */
    public void weightAdd1() {
        weight++;
    }
}
