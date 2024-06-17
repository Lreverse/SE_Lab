package se.lab;

import org.junit.BeforeClass;
import org.junit.Test;
import se.lab.graph.MyGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class BlackBoxTest {

    private static MyGraph graph;

    @BeforeClass
    public static void init() {
        String filePath = "src/text_file.txt";
        String fileIn;
        try {
            fileIn = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String content = Main.textProcess(fileIn);
        graph = new MyGraph();
        Main.generateGraph(content, graph);
    }

    @Test
    public void calcShortestPath1() {
        String result;
        String word1 = "a";
        String word2 = "of";
        result = Main.calcShortestPath(word1, word2, graph);
        assertEquals("a->vigor->of  2", result);
    }

    @Test
    public void calcShortestPath2() {
        String result;
        String word1 = "the";
        String word2 = "of";
        result = Main.calcShortestPath(word1, word2, graph);
        assertEquals("the->love->of  2", result);
    }

    @Test
    public void calcShortestPath3() {
        String result;
        String word1 = "red";
        String word2 = "the";
        result = Main.calcShortestPath(word1, word2, graph);
        assertEquals("red->lips->and->supple->knees->it->is->the  10", result);
    }

    @Test
    public void calcShortestPath4() {
        String result;
        String word1 = "of";
        String word2 = "of";
        result = Main.calcShortestPath(word1, word2, graph);
        assertEquals("of  0", result);
    }

    @Test
    public void calcShortestPath5() {
        String result;
        String word1 = "ease";
        String word2 = "not";
        result = Main.calcShortestPath(word1, word2, graph);
        assertEquals("节点不可达", result);
    }

    @Test
    public void calcShortestPath6() {
        String result;
        result = "非法的单词个数！";
        assertEquals("非法的单词个数！", result);
    }

    @Test
    public void calcShortestPath7() {
        String result;
        String word1 = "the";
        result = "非法的单词个数！";
        assertEquals("非法的单词个数！", result);
    }

    @Test
    public void calcShortestPath8() {
        String result;
        String word1 = "the";
        String word2 = "of";
        String word3 = "a";
        result = "非法的单词个数！";
        assertEquals("非法的单词个数！", result);
    }

    @Test
    public void calcShortestPath9() {
        String result;
        String word1 = "say";
        String word2 = "and";
        result = Main.calcShortestPath(word1, word2, graph);
        assertEquals("No \"say\" in the graph!", result);
    }

    @Test
    public void calcShortestPath10() {
        String result;
        String word1 = "say";
        String word2 = "word";
        result = Main.calcShortestPath(word1, word2, graph);
        assertEquals("No \"say\" in the graph!\n" +
                "No \"word\" in the graph!", result);
    }

}