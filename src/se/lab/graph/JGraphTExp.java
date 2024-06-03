package se.lab.graph;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxRectangle;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JGraphTExp extends JFrame {
    /**
     * mxGraphComponent.
     */
    private final mxGraphComponent graphComponent;

    /**
     * 实现有向图的导入与可视化.
     * @param graphIn 自定义图
     */
    public JGraphTExp(final MyGraph graphIn) {
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
        graphComponent = new mxGraphComponent(graphAdapter);

        // 设置组件的大小
        final int width = 800;
        final int height = 600;
        graphComponent.setPreferredSize(new Dimension(width, height));

        getContentPane().add(graphComponent, BorderLayout.CENTER);

        // 添加缩放控制面板
        JPanel controlPanel = new JPanel();
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");
        JButton resetZoomButton = new JButton("Reset Zoom");

        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                graphComponent.zoomIn();
            }
        });

        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                graphComponent.zoomOut();
            }
        });

        resetZoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                graphComponent.zoomActual();
            }
        });

        controlPanel.add(zoomInButton);
        controlPanel.add(zoomOutButton);
        controlPanel.add(resetZoomButton);

        getContentPane().add(controlPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // 布局
        final int radius = 200;
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.setRadius(radius);  // 调整圆的半径以缩短边长
        layout.execute(graphAdapter.getDefaultParent());

        // 调整图形大小和位置以适应组件
        fitGraph(graphAdapter, graphComponent);
    }

    private void fitGraph(final JGraphXAdapter<String, DefaultEdge> graphAdapter, final mxGraphComponent graphComponent) {
        // 获取图形边界
        graphAdapter.refresh();
        mxRectangle bounds = graphAdapter.getGraphBounds();

        // 计算缩放因子
        double widthScale = graphComponent.getWidth() / bounds.getWidth();
        double heightScale = graphComponent.getHeight() / bounds.getHeight();
        double scale = Math.min(widthScale, heightScale);

        // 应用缩放和居中
        graphAdapter.getView().setScale(scale);
        graphAdapter.getView().revalidate();
    }
}
