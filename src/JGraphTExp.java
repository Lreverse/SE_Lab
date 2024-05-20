import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import javax.swing.*;

public class JGraphTExp extends JFrame {
    public JGraphTExp(MyGraph graphIn) {
        // 创建一个有向图
        Graph<String, DefaultEdge> graph = new DirectedMultigraph<>(DefaultEdge.class);

        for (Vertex v : graphIn.getAdjlist()) {
            graph.addVertex(v.getName());
        }
        for (Vertex v : graphIn.getAdjlist()) {
            String vertex = v.getName();
            for (Edge edge : v.getEdgeList()) {
                graph.addEdge(vertex, edge.getTail().getName());
            }
        }

        // 使用 JGraphXAdapter 将 JGraphT 图转换为 JGraphX 图
        JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph);

        // 创建一个 JGraphX 图组件
        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        getContentPane().add(graphComponent);

        // 布局
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
    }
}
