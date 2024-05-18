import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("只需要1个参数：文件路径");
        } else {
            String file_in;
            try {
                file_in = Files.readString(Paths.get(args[0]));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String content = TextProcess.text_process(file_in);
//            System.out.println(content);
            Graph graph = new Graph();
            generateGraph(content, graph);
            graph.printGraph();

            Scanner scanner = new Scanner(System.in);
            String result;
            while (true) {
                menu();
                int opt = scanner.nextInt();
                switch (opt) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        showDirectedGraph(graph);
                        break;
                    case 2:
                        result = queryBridgeWords("hello", "world");
                        break;
                    case 3:
                        result = generateNewText("test");
                        break;
                    case 4:
                        result = calcShortestPath("hello", "world", graph);
                        break;
                    case 5:
                        result = randomWalk();
                        break;
                    default:
                        System.out.println("非法输入");
                        break;
                }
            }
        }

    }

    public static void menu() {
        System.out.println("\n--------------------------");
        System.out.println("1. 展示有向图");
        System.out.println("2. 查询桥接词");
        System.out.println("3. 根据bridge word生成新文本");
        System.out.println("4. 计算两个单词之间的最短路径");
        System.out.println("5. 随机游走");
        System.out.println("0. 退出");
        System.out.println("--------------------------");
        System.out.print("> ");
    }

    public static void generateGraph(String content, Graph graph) {
        String[] words = content.split(" ");
        for (String word : words) {
            graph.addVertex(word);
        }
        for (int i=0; i < words.length-1; i++) {
            graph.addEdge(words[i], words[i+1]);
        }
    }

    public static void showDirectedGraph(Graph graph) {
        testGraph();
        // todo
    }

    public static String queryBridgeWords(String word1, String word2) {
        // todo
        return null;
    }

    public static String generateNewText(String inputText) {
        // todo
        return null;
    }

    public static String calcShortestPath(String word1, String word2, Graph graph) {
        boolean[] S = new boolean[graph.getN()];
        int[] D = new int[graph.getN()];

        return null;
    }

    public static String randomWalk() {
        // todo
        return null;
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
        graph.addEdge("test.txt", "you");

        graph.printGraph();
    }
}