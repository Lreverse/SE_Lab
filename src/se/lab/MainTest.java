package se.lab;

import org.junit.BeforeClass;
import org.junit.Test;
import se.lab.graph.MyGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.Assert.*;

public class MainTest {

    private static MyGraph graph;

    @BeforeClass
    public static void init() {
        String fileIn;
        try {
            fileIn = Files.readString(Paths.get("src/text_file.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String content = Main.textProcess(fileIn);
        graph = new MyGraph();
        Main.generateGraph(content, graph);
    }

    @Test
    public void calcShortestPath() {
        String result;
        String temp[];
        String[] words = Main.input();
        boolean flag = false;
        for (String word : Objects.requireNonNull(words)) {
            if (graph.findVertex(word.toLowerCase()) == null) {
                System.out.printf(" No \"%s\" in the graph!\n", word);
                flag = true;
            }
        }
        if (flag) {
            result = "";
        } else {
            result = Main.calcShortestPath(words[0].toLowerCase(), words[1].toLowerCase(), graph);
        }
        assertEquals("", result);
    }
}