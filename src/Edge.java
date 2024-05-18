
public class Edge {
    private int weight;  // 边的权重
    private String tail;  // 尾部结点标识

    public Edge(String name) {
        this.tail = name;
        this.weight = 1;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String name) {
        this.tail = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void weightAdd1() {
        weight++;
    }
}
