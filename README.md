# SE_Lab_1

## 需求分析

- [x] 功能1：读入文本并生成有向图
- [x] 功能2：展示有向图
- [x] 功能3：查询桥接词
- [x] 功能4：根据bridge word生成新文本
- [x] 功能5：计算两个单词之间的最短路径
- [x] 功能6：随机游走


## 数据结构

使用邻接表来存储有向图
```java
public class MyGraph {
    private int n;  // 顶点数
    private int e;  // 边数
    private final List<Vertex> Adjlist;   // 邻接表
}
```
```java
public class Vertex {
    private final String name;   // 每个结点的名字
    private final LinkedList<Edge> EdgeList;   // 边表
    private final int index;    // 结点编号
}
```
```java
public class Edge {
    private int weight;  // 边的权重
    private Vertex tail;  // 尾部结点标识
}
```

## 功能1

读入文本后通过正则处理，再通过遍历进行有向图的的生成。

## 功能2

导入 JGraphT 用于图数据结构和 JGraphX 用于图的可视化，添加了缩放控制面板。

## 功能3

通过遍历图来查询桥接词。

## 功能4

根据功能3提供的函数实现生成新文本。

## 功能5

使用dijkstra算法来计算最短路径。
- 数组S：最短路径已确定的顶点集合
- 数组D：存放源点到各个顶点的最短距离
- 数组P：存放最短路径的前驱（后续打印路径进行回溯）

## 功能6

随机获取初始节点，然后开始游走
- 使用`boolean[][] path`来记录走过的路径

