package jab.aoc.day7.tree;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.Traverser;

public class GraphTest {

    public static void main(String[] argv) {
        enum Type {
            DIRECTORY,
            FILE,
        }

        record Node(Type type, String name, Integer value) {}

        MutableGraph<Node> myGraph = GraphBuilder.directed().build();

        var root = new Node(Type.DIRECTORY, "/", 0);
        var f_b = new Node(Type.FILE, "b.txt", 14848514);
        var f_c = new Node(Type.FILE, "c.dat", 8504156);

        var d_a = new Node(Type.DIRECTORY, "a", 0);
        var f_f = new Node(Type.FILE, "f", 29116);
        var f_g = new Node(Type.FILE, "g", 2557);
        var f_h = new Node(Type.FILE, "h.lst", 62596);

        var d_e = new Node(Type.DIRECTORY, "e", 0);
        var f_i = new Node(Type.FILE, "i", 584);

        var d_d = new Node(Type.DIRECTORY, "d", 0);
        var f_j = new Node(Type.FILE, "j", 4060174);
        var f_d = new Node(Type.FILE, "d.log", 8033020);
        var f_de = new Node(Type.FILE, "d.ext", 5626152);
        var f_k = new Node(Type.FILE, "k", 7214296);

        myGraph.putEdge(root, f_b);
        myGraph.putEdge(root, f_c);
        myGraph.putEdge(root, d_a);
        myGraph.putEdge(d_a, f_f);
        myGraph.putEdge(d_a, f_g);
        myGraph.putEdge(d_a, f_h);
        myGraph.putEdge(d_a, d_e);
        myGraph.putEdge(d_e, f_i);
        myGraph.putEdge(root, d_d);
        myGraph.putEdge(d_d, f_j);
        myGraph.putEdge(d_d, f_d);
        myGraph.putEdge(d_d, f_de);
        myGraph.putEdge(d_d, f_k);

        //Print the nodes Depth First
        System.out.println("==============Dept First==============");
        Traverser.forGraph(myGraph).depthFirstPostOrder(root).forEach(x -> System.out.println(x));
        //Print the nodes Bread First
        System.out.println("==============Breath First==============");
        Traverser.forGraph(myGraph).breadthFirst(root).forEach(x -> System.out.println(x));
    }
}
