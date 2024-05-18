import java.util.*;

public class Main {
    public static void main(String[] args) {
        testGraph();

    }

    public static void testGraph() {
        Graph graph = new Graph();
        graph.addVertex("See");
        graph.addVertex("you");
        graph.addVertex("later");
        graph.addVertex("See");
        graph.addEdge("See", "you");
        graph.addEdge("you", "later");
        graph.addEdge("See", "you");
        graph.addEdge("See", "you");
        graph.addEdge("you", "See");
        graph.addEdge("test", "you");

        graph.printGraph();
    }

    public void ShowDirectedGraph() {
        // todo
    }

    public String queryBridgeWords(String word1, String word2) {
        // todo
        return null;
    }

    public String generateNewText(String inputText) {
        // todo
        return null;
    }

    public String calcShortestPath(String word1, String word2) {
        // todo
        return null;
    }

    public String randomWalk() {
        // todo
        return null;
    }

}