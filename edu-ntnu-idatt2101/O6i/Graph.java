import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Represents the solution for <i>IDATT2101 oving 6</i>.
 * Much is inspired from the book <i>Algoritmer og datastrukturer</i>
 * on chapter 9: <i>Grafteori</i>.
 *
 * @author Jens Christian Aanestad
 * @version 04.10.2023
 * @see Cue
 * @see Node
 * @see Edge
 * @see Predecessor
 * @see TopologyList
 */
public class Graph {
  int nodes;
  int edges;
  Node[] nodeArray;
  
  /**
   * Initializes a predecessor for every node in the node array,
   * and sets all distances to infinity except the start node,
   * which distance is set to 0. This is done for a BFS.
   *
   * @param startNode the start node.
   */
  public void initPredecessor(Node startNode) {
    for (int i = nodes; i--> 0;) {
      nodeArray[i].data = new Predecessor();
    }
    ((Predecessor)startNode.data).distance = 0;
  }
  
  /**
   * Represents a BFS for a start node.
   *
   * @param startNode the start node for a BFS search.
   */
  public void bfs(Node startNode) {
    initPredecessor(startNode);
    Queue queue = new Queue(nodes - 1);
    queue.put(startNode);
    while (!queue.isEmpty()) {
      Node node = (Node) queue.next();
      for (Edge edge = node.headEdge; edge != null; edge = edge.next) {
        Predecessor predecessor = (Predecessor) edge.endNode.data;
        if (predecessor.distance == Predecessor.INFINITE) {
          predecessor.distance = ((Predecessor)node.data).distance + 1;
          predecessor.predecessor = node;
          queue.put(edge.endNode);
        }
      }
    }
  }
  
  /**
   * Reads a file from an url and creates a new graph.
   *
   * @param fileUrl the url to read from.
   * @throws IOException if provided url is wrong.
   */
  public void newGraphFromUrl(String fileUrl) throws IOException {
    URL url = new URL(fileUrl);

    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
      newGraph(bufferedReader);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Reads a file and creates a new graph.
   *
   * @param fileLocation the file location to this file.
   */
  public void newGraphFromFile(String fileLocation) {
    File file = new File(fileLocation);
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      newGraph(bufferedReader);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Creates a new graph from the stream of a BufferedReader.
   * The format is the following if only four nodes and three edges:
   * <pre>  4  3
   * 0  1
   * 1  2
   * 2  3</pre>
   *
   * @param bufferedReader the bufferedReader to read data from.
   * @throws IOException
   */
  private void newGraph(BufferedReader bufferedReader) throws IOException {
    StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
    nodes = Integer.parseInt(st.nextToken());
    nodeArray = new  Node[nodes];
    for (int i = 0; i < nodes; ++i) nodeArray[i] = new Node(i);
    edges = Integer.parseInt(st.nextToken());
    for (int i = 0; i < edges; ++i) {
      st = new StringTokenizer(bufferedReader.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      nodeArray[from].headEdge = new Edge(nodeArray[to], nodeArray[from].headEdge);
    }
  }
  
  /**
   * Shows a graph on following format:
   * <pre>  Node: 0    har kanter til node: 1   2   3
   * Node: 1    har kanter til node: 4   7
   * Node: ...  har kanter til node: ...</pre>
   */
  public void showGraph() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < nodes; ++i) {
      StringBuilder kanter = new StringBuilder();
      for (Edge k = nodeArray[i].headEdge; k != null; k = k.next) {
        kanter.append(k.endNode.number).append("   ");
      }
      sb.append(String.format("Node: %d    har kanter til Node: %s\n", i, kanter));
    }
    System.out.println(sb);
  }
  
  /**
   * Returns the result of a BFS search given that the BFS has been run,
   * and that predecessors have been initialized. Example of format for
   * this is if one is the start node:
   * <pre>Node Forgj Dist
   *  0     3    2
   *  1          2
   *  2     5    1
   *  2        inf</pre>
   *  <i>inf</i> if distance is infinity. Note that nodes with distance
   *  does not have a predecessor either.
   *
   * @return the result.
   */
  public String bfsResult() {
    StringBuilder result = new StringBuilder("Node Forgj Dist\n");
    
    for (int i = 0; i < nodes; ++i) {
      result.append(String.format("%s%d", " ".repeat(Math.abs( 4 - String.valueOf(i).length())), i));
      
      result.append(String.format("%s%s", " ".repeat(Math.abs( 6 - String.valueOf(((Predecessor) nodeArray[i].data)
                      .predecessor == null ? " " : ((Predecessor) nodeArray[i].data).predecessor.number).length()))
              , ((Predecessor) nodeArray[i].data).predecessor == null ? " " : ((Predecessor) nodeArray[i].data).predecessor.number));
      
      result.append(String.format("%s%s\n", " ".repeat(((Predecessor) nodeArray[i].data).distance == 1_000_000_000
                      ? 2 : Math.abs(5 - String.valueOf(((Predecessor) nodeArray[i].data).distance).length()))
              , ((Predecessor) nodeArray[i].data).distance == 1_000_000_000 ? "inf" : ((Predecessor) nodeArray[i].data).distance));
    }
    return result.toString();
  }
  
  /**
   * Returns the order of nodes in topological order.
   *
   * @return the order of nodes in topological order.
   */
  public String topologySortResult() {
    StringBuilder result = new StringBuilder();
    for (Node node = topologySort(); node != null; node = ((TopologyList)node.data).nextNode) {
      result.append(String.format("%d  ", node.number));
    }
    return result.toString();
  }
  
  /**
   * Represents a DPS adapted for topology sorting.
   *
   * @param startNode the start node for the DPS.
   * @param firstNode the first node in the linked topology list.
   * @return the startNode.
   */
  Node dfTopology(Node startNode, Node firstNode) {
    TopologyList topologyList = (TopologyList)startNode.data;
    if (topologyList.found) return firstNode;
    topologyList.found = true;
    for (Edge edge = startNode.headEdge; edge != null; edge = edge.next) {
      firstNode = dfTopology(edge.endNode, firstNode);
    }
    topologyList.nextNode = firstNode;
    return startNode;
  }
  
  /**
   * Sorts the nodes in a graph topology, and returns the first node in the
   * topology list.
   *
   * @return the first node in the topology list.
   */
  Node topologySort() {
    Node node = null;
    for (int i = 0; i < nodes; i++) {nodeArray[i].data = new TopologyList();}
    for (int i = 0; i < nodes; i++) {node = dfTopology(nodeArray[i], node);}
    return node;
  }
  
  /**
   * Generates a random number between 1 and the amount of nodes.
   *
   * @return random number between 1 and the amount of nodes.
   */
  public int generateRandomNumber() {
    Random random = new Random();
    return random.nextInt(nodes - 1) + 1;
  }
  
  /**
   * Main method - Entry point.
   *
   * @param args String[]
   * @throws IOException if an urlLink or file location is invalid.
   */
  public static void main(String[] args) throws IOException {
    
    // URL-ene til alle filene i denne øvingen.
    final String ø6g1 = "https://www.idi.ntnu.no/emner/idatt2101/uv-graf/%C3%B86g1";
    final String ø6g2 = "https://www.idi.ntnu.no/emner/idatt2101/uv-graf/%C3%B86g2";
    final String ø6g3 = "https://www.idi.ntnu.no/emner/idatt2101/uv-graf/%C3%B86g3";
    final String ø6g5 = "https://www.idi.ntnu.no/emner/idatt2101/uv-graf/%C3%B86g5";
    final String ø6g7 = "https://www.idi.ntnu.no/emner/idatt2101/uv-graf/%C3%B86g7";
    final String ø6Skandinavia = "https://www.idi.ntnu.no/emner/idatt2101/uv-graf/%C3%B86Skandinavia";
    final String ø6Skandinavia_navn = "https://www.idi.ntnu.no/emner/idatt2101/uv-graf/%C3%B86Skandinavia-navn";
    
    // Liste over alle URL-ene
    List<String> urlList = List.of(ø6g1, ø6g2, ø6g3, ø6g5, ø6g7);
    // Liste over URL-er som kan sorteres topologsik.
    List<String> urlListTopology = List.of(ø6g5, ø6g7);
    
    Graph graph = new Graph();
   
    // Bredde-først søk:
    for (String url : urlList) {
      System.out.println(url);
      // Kan også bruke metoden newGraphFromFile("Filnavn")
      // hvis du har data-filene ved siden av java-filen.
      graph.newGraphFromUrl(url);
      int randomNumber = graph.generateRandomNumber();
      graph.bfs(graph.nodeArray[randomNumber]);
      System.out.printf("BFS resultat med node %d som start node:\n%s", randomNumber, graph.bfsResult());
    }
    
    // Topologisk sortering:
    for (String url : urlListTopology) {
      System.out.println("\n" + url);
      graph.newGraphFromUrl(url);
      System.out.println(String.format("Topologsik sortert: \n%s", graph.topologySortResult()));
    }
  }
}

/**
 * Represents a node in a graph used for <i>IDATT2101 oving 6</i>.
 *
 * @author Jens Christian Aanestad
 * @version 04.10.2023
 */
class Node {
  Edge headEdge;
  Object data;
  int number;
  
  /**
   * Initializes a node with a number.
   *
   * @param number the nodes number.
   */
  public Node(int number) {this.number = number;}
}

/**
 * Represents an edge as a linked-list in a graph used for <i>IDATT2101 oving 6</i>.
 *
 * @author Jens Christian Aanestad
 * @version 04.10.2023
 */
class Edge {
  Edge next;
  Node endNode;
  
  /**
   * Initializes an edge from <i>node</i> with the next edge.
   * @param endNode the node from this edge.
   * @param next the next edge from this edge.
   */
  public Edge(Node endNode, Edge next) {
    this.endNode = endNode;
    this.next = next;
  }
}

/**
 * Represents a predecessor for a BFS algorithm used for <i>IDATT2101 oving 6</i>.
 *
 * @author Jens Christian Aanestad
 * @version 04.10.2023
 */
class Predecessor {
  int distance;
  Node predecessor;
  final static int INFINITE = 1_000_000_000;
  
  /**
   * Initializes a predecessor by setting the distance to infinite.
   */
  public Predecessor() {
    distance = INFINITE;}
  
  /**
   * Returns the distance.
   *
   * @return the distance.
   */
  public int findDistance() {return distance;}
  
  /**
   * Returns the predecessor.
   *
   * @return the predecessor.
   */
  public Node findPredecessor() {return predecessor;}
}

/**
 * Represents a data structure for topology sorting used for <i>IDATT2101 oving 6</i>.
 */
class TopologyList {
  boolean found;
  Node nextNode;
}
