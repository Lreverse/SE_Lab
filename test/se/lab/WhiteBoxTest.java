package se.lab;

import org.junit.BeforeClass;
import org.junit.Test;
import se.lab.graph.MyGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class WhiteBoxTest {

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
    public void testQueryBridgeWordsMultipleBridges() {
        String word1 = "a";
        String word2 = "of";
        String result = Main.queryBridgeWords(graph, word1, word2);
        assertEquals("The bridge words from word1 to word2 are: time, state, matter, quality, vigor", result);
    }

    @Test
    public void testQueryBridgeWordsSingleBridge() {
        String word1 = "state";
        String word2 = "mind";
        String result = Main.queryBridgeWords(graph, word1, word2);
        assertEquals("The bridge word from word1 to word2 is: of", result);
    }

    @Test
    public void testQueryBridgeWordsNoBridge() {
        String word1 = "not";
        String word2 = "life";
        String result = Main.queryBridgeWords(graph, word1, word2);
        assertEquals("No bridge words from word1 to word2!", result);
    }

    @Test
    public void testQueryBridgeWordsWordNotInGraph() {
        String result = Main.queryBridgeWords(graph, "happy", "smile");
        assertEquals("No word1 or word2 in the graph!", result);
    }
}
