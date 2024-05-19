import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
            MyGraph graph = new MyGraph();
            generateGraph(content, graph);
//            graph.printGraph();

            Scanner scanner = new Scanner(System.in);
            String result;
            while (true) {
                menu();
                String opt = scanner.nextLine();
                switch (opt) {
                    case "0":
                        System.exit(0);
                        break;
                    case "1":
                        showDirectedGraph(graph);
                        break;
                    case "2":
                        result = queryBridgeWords("hello", "world");
                        break;
                    case "3":
                        result = generateNewText("test");
                        break;
                    case "4":
                        System.out.print("> 输入两个单词（用空格隔开）\n> ");
                        String in = scanner.nextLine();
                        String[] words = in.split(" ");
                        // 检查词语是否合法
                        if (words.length != 2) {
                            System.out.println("非法的单词个数！");
                            continue;
                        }
                        boolean flag = false;
                        for (String word : words) {
                            if (graph.findVertex(word) == null) {
                                System.out.printf(" No \"%s\" in the graph!\n", word);
                                flag = true;
                            }
                        }
                        if (flag) {
                            continue;
                        }
                        result = calcShortestPath(words[0], words[1], graph);
                        System.out.println(result);
                        break;
                    case "5":
                        result = randomWalk(graph);
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

                        // 获取当前时间作为文件名
                        String filepath = "./randomWalk/randomWalk-" + formatter.format(date) + ".txt";
                        // 写入磁盘
                        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
                            bufferedWriter.write(result);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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

    public static void generateGraph(String content, MyGraph graph) {
        String[] words = content.split(" ");
        for (String word : words) {
            graph.addVertex(word);
        }
        for (int i=0; i < words.length-1; i++) {
            graph.addEdge(words[i], words[i+1]);
        }
    }

    public static void showDirectedGraph(MyGraph graph) {
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

    public static String calcShortestPath(String word1, String word2, MyGraph graph) {
        int max = Integer.MAX_VALUE;
        int n = graph.getN();
        boolean[] S = new boolean[n];
        int[] D = new int[n];   // 存放源点到各个顶点的最短距离
        int[] P = new int[n];   // 存放最短路径的前驱
        Vertex node = graph.findVertex(word1);
        int sour = node.getIndex();
        List<Vertex> adjList = graph.getAdjlist();

        // 初始化
        Arrays.fill(D, max);  // 初始化为极大值
        D[sour] = 0;
        for (Edge edge : node.getEdgeList()) {
            int i = edge.getTail().getIndex();
            D[i] = edge.getWeight();
        }
        Arrays.fill(P, sour);

        S[sour] = true;
        for (int i=0; i < graph.getN()-1; i++) {
            int temp = max, w = sour;
            for (int j=0; j < graph.getN(); j++) {   // 找出V-S中，使D[w]值最小的w
                if (!S[j] && temp > D[j]) {
                    temp = D[j];
                    w = j;
                }
            }

            S[w] = true;    // 把点w加入S
            List<Edge> edgeList_w = adjList.get(w).getEdgeList();
            for (Edge edge : edgeList_w) {
                int index = edge.getTail().getIndex();
                if (!S[index]) {
                    D[index] = Math.min(D[index], D[w] + edge.getWeight());   // 更新最短路径
                    P[index] = w;   // 把index的最短路径的前置节点置为w
                }
            }
        }

        int dest = graph.findVertex(word2).getIndex();
        if (D[dest] == max) {
            return "节点不可达";
        }
        return get_path_dijkstra(adjList, P, sour, dest);
    }

    /* 向前回溯最短路径（递归）*/
    public static String get_path_dijkstra(List<Vertex> adjlist, int[] P, int sour, int dest) {
        String result;
        if (dest != sour) {
            int prev = P[dest];
            result = get_path_dijkstra(adjlist, P, sour, prev);
            result = result + "->" + adjlist.get(dest).getName();
        }
        else {
            result = adjlist.get(dest).getName();
        }
        return result;
    }

    public static String randomWalk(MyGraph graph) {
        List<Vertex> adjList = graph.getAdjlist();
        int N = graph.getN();
        List<Edge> edgeList_start;
        Random rand = new Random();
        String result = "";

        // 随机获取节点
        Vertex start = adjList.get(rand.nextInt(N));
        Vertex end = start;
        boolean[][] path = new boolean[N][N];
        result = result + start.getName() + " ";
        System.out.print(start.getName() + " ");
        do {
            try {  // 休眠1秒，模拟游走的间歇感
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            path[start.getIndex()][end.getIndex()] = true;
            start = end;
            edgeList_start = start.getEdgeList();
            if (edgeList_start.isEmpty()) {
//                System.out.println("(节点不存在出边)");
                break;
            }
            end = edgeList_start.get(rand.nextInt(edgeList_start.size())).getTail();
            result = result + end.getName() + " ";
            System.out.print(end.getName() + " ");
        } while(!path[start.getIndex()][end.getIndex()]) ;   // 判断path是否已经被走过了
        System.out.println();
        return result;
    }

    public static void testGraph() {
        MyGraph graph = new MyGraph();
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