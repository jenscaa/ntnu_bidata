import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Represents the solution for <i>IDATT2101 oving 7: Edmond-Karp</i>.
 *
 * @author Jens Christian Aanestad
 * @version 12.10.2023
 * @see Edge
 * @see Node
 * @see Predecessor
 * @see Queue
 */
public class Graph {
  int nodes;
  int edges;
  Node[] nodeArray;

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
   * Creates a new weighted graph from the stream of a BufferedReader.
   * The format is the following if only four nodes and three edges:
   * <pre>
   * 4 3
   * 0 1 10
   * 1 2 20
   * 2 3 30
   * (From node) (to node) (weight) </pre>
   *
   * @param bufferedReader the bufferedReader to read data from.
   * @throws IOException if file/file-format is wrong.
   */
  private void newGraph(BufferedReader bufferedReader) throws IOException {
    StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
    nodes = Integer.parseInt(st.nextToken());
    nodeArray = new Node[nodes];
    for (int i = 0; i < nodes; ++i) nodeArray[i] = new Node(i);
    edges = Integer.parseInt(st.nextToken());
    for (int i = 0; i < edges; ++i) {
      st = new StringTokenizer(bufferedReader.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());
      nodeArray[from].headEdge = new Edge(nodeArray[to], nodeArray[from].headEdge, value);
    }
  }
  
  /**
   * Initializes a predecessor for every node in the node array,
   * and sets all distances to infinity except the start node,
   * which distance is set to 0. This is done for a BFS.
   *
   * @param startNode the start node.
   */
  public void initPredecessor(Node startNode) {
    for (int i = nodes; i-- > 0;) {
      nodeArray[i].data = new Predecessor();
    }
    ((Predecessor)startNode.data).distance = 0;
  }
  
  /**
   * Represents a BFS for a start node adapted for the Edmond.Karp algorithm.
   *
   * @param sourceNode the start node for a BFS search.
   * @param endNode the end node for a BFS search.
   */
  public boolean bfs(Node sourceNode, Node endNode) {
    initPredecessor(sourceNode);
    Queue queue = new Queue(nodes - 1);
    queue.put(sourceNode);
    while (!queue.isEmpty()) {
      Node node = (Node) queue.next();
      for (Edge edge = node.headEdge; edge != null; edge = edge.nextEdge) {
        Predecessor predecessor = (Predecessor) edge.endNode.data;
        if (predecessor.distance == Predecessor.INFINITE) {
          predecessor.distance = ((Predecessor)node.data).distance + 1;
          predecessor.predecessor = node;
          queue.put(edge.endNode);
        }
        // Sjekker om vi finner endNode slik at vi kan stoppe bfs i edmondKarp metoden.
        if (edge.endNode == endNode) {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * Returns the edge between given nodes. This method assumes that there are only
   * edge in one direction between two nodes.
   *
   * @param fromNode the node at the start of the edge.
   * @param toNode the node at the end of the edge.
   * @return Edge. If not found, then returns null.
   */
  public Edge getEdge(Node fromNode, Node toNode) {
    for (Edge edge = fromNode.headEdge; edge != null; edge = edge.nextEdge) {
      if (edge.endNode == toNode) {
        return edge;
      }
    }
    return null;
  }
  
  /**
   * Checks if edge between two given nodes exists.
   *
   * @param fromNode the node at the start of the edge.
   * @param toNode the node at the end of the edge.
   * @return true if exists.
   */
  public boolean edgeExists(Node fromNode, Node toNode) {
    return getEdge(fromNode, toNode) != null;
  }
  
  /**
   * Deletes an edge between two given nodes. This method assumes that the edge does exist.
   *
   * @param fromNode the node at the start of the edge.
   * @param toNode the node at the end of the edge.
   */
  public void
  deleteEdge(Node fromNode, Node toNode) {
    Edge preEdge = null;
    Edge currentEdge = fromNode.headEdge;
    while (currentEdge != null && currentEdge!= getEdge(fromNode, toNode)) {
      preEdge = currentEdge;
      currentEdge = currentEdge.nextEdge;
    }
    if (currentEdge != null) {
      if (preEdge != null) preEdge.nextEdge = currentEdge.nextEdge;
      else fromNode.headEdge = currentEdge.nextEdge;
      currentEdge.nextEdge = null;
    }
  }
  
  /**
   * Adds an edge to the graph between two given nodes with a flow value.
   *
   * @param fromNode the node at the start of the edge.
   * @param toNode the node at the end of the edge.
   * @param value the flow value of this edge.
   */
  public void addEdge(Node fromNode, Node toNode, int value) {
    nodeArray[fromNode.number].headEdge = new Edge(nodeArray[toNode.number]
            , nodeArray[fromNode.number].headEdge, value);
  }
  
  /**
   * Updates an edge between given nodes with a flowIncreaseValue. If this increase
   * value is equal to the edge's flowValue, then the edge is deleted. An edge with opposite
   * direction is created/updated with the flowIncreaseValue. If the edge's flowValue is not equal
   * with the flowIncreaseValue (that means the flowIncreaseValue is lesser than the flowValue),
   * then the edge's flowValue will be decremented with the flowIncreaseValue.
   *
   * @param fromNode the node at the start of the edge.
   * @param toNode the node at the end of the edge.
   * @param flowIncrease the flowIncreaseValue from a bfs.
   */
  public void updateEdge(Node fromNode, Node toNode, int flowIncrease) {
    Edge edge = getEdge(fromNode, toNode);
    
    // Bytter kantens retning hvis flytverdien er det samme som flytøkningen.
    if (edge.flowValue == flowIncrease) {
      deleteEdge(fromNode, toNode);
      if (edgeExists(toNode, fromNode)) {
        getEdge(toNode, fromNode).flowValue += flowIncrease;
      } else {
        addEdge(toNode, fromNode, flowIncrease);
      }
    } else { // Oppdaterer kanten, og legger til/oppdaterer motsatt kant.
      if (edgeExists(toNode, fromNode)) {
        getEdge(toNode, fromNode).flowValue += flowIncrease;
      } else {
        addEdge(toNode, fromNode, flowIncrease);
      }
      edge.flowValue -= flowIncrease;
    }
  }
  
  /**
   * Updates the graph, so it is ready for a new bfs.
   *
   * @param nodePath the nodePath from former bfs search (List of node numbers from start node to end node).
   * @param flowIncrease the flow increase from the nodePath
   * (The lowest flow value through all edges in the nodePath).
   */
  public void updateGraph(List<Integer> nodePath, int flowIncrease) {
    for (int i = 0; i < nodePath.size() - 1; i++) {
      updateEdge(nodeArray[nodePath.get(i)], nodeArray[nodePath.get(i+1)], flowIncrease);
    }
  }
  
  /**
   * Returns the node path from the startNode to the endNode of the bfs result.
   *
   * @param endNode the endNode to find the path to.
   * @return a list of integers representing node numbers.
   */
  public List<Integer> findNodePath(Node endNode) {
    ArrayList<Integer> nodePath = new ArrayList<>();
    // Bruker Predecessor baklengs for å finne stien fra startnode til sluttnode
    for (Node node = endNode; node != null; node = ((Predecessor)node.data).predecessor) {
      nodePath.add(0, node.number);
    }
    return nodePath;
  }
  
  /**
   * Returns the flow increase value from a nodePath (the lowest flow value through a nodePath).
   *
   * @param nodePath the nodePath to find the flow increase value from.
   * @return the flow increase.
   */
  public int findFlowIncrease(List<Integer> nodePath) {
    int increase = Predecessor.INFINITE;
    for (int i = 0; i < nodePath.size()-1; i++) {
      int weight = getEdge(nodeArray[nodePath.get(i)], nodeArray[nodePath.get(i + 1)]).flowValue;
      if (weight < increase) {
        increase = weight;
      }
    }
    return increase;
  }
  
  /**
   * Generates a string representing a bfs result with the flow increase and nodePath.
   * This is used for the final result of the Edmond-Karp method.
   *
   * @param nodePath the list with node numbers.
   * @param flowIncrease the flow increase value through the nodePath.
   * @return a String representing one bfs result.
   */
  public String generateBfsResult(List<Integer> nodePath, int flowIncrease) {
    StringBuilder result = new StringBuilder();
    for (Integer i : nodePath) {
      result.append(i).append(" ");
    }
    return String.format("%s%d%s%s", " ".repeat(
        Math.abs(6 - String.valueOf(flowIncrease).length())), flowIncrease
        , " ".repeat(3), result);
  }
  
  /**
   * Represents the Edmond-Karp algorithm which finds the maximum flow from
   * source (sourceNode) and drain (endNode).
   *
   * @param sourceNode the node representing the source.
   * @param endNode the node representing the end.
   * @return a String representing the result of the Edmond-Karp algorithm.
   */
  public String edmondKarp(Node sourceNode, Node endNode) {
    StringBuilder result = new StringBuilder();
    result.append(String.format("""
        Maksimum flyt fra %d til %d med Edmond-Karp
        Økning : Flytøkende vei
        """, sourceNode.number, endNode.number));
    int maxFlow = 0;
    while (bfs(sourceNode, endNode)) {
      List<Integer> nodePath = findNodePath(endNode);
      int flowIncrease = findFlowIncrease(nodePath);
      maxFlow += flowIncrease;
      result.append(generateBfsResult(nodePath, flowIncrease)).append("\n");
      updateGraph(nodePath, flowIncrease);
    }
    result.append(String.format("Maksimal flyt ble %d", maxFlow));
    return result.toString();
  }

  
  /**
   * Main method - Entry point
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    Graph graph = new Graph();
    
    // Hvis ikke metoden newGraphFrom(<filnavn>) funker, bruk heller newGraphFromUrl(<fil-url>).
    System.out.println("\n\n--------------- Flytgraf1 ---------------");
    graph.newGraphFromFile("flytgraf1");
    System.out.println(graph.edmondKarp(graph.nodeArray[0], graph.nodeArray[7]));

    System.out.println("\n\n--------------- Flytgraf2 ---------------");
    graph.newGraphFromFile("flytgraf2");
    System.out.println(graph.edmondKarp(graph.nodeArray[0], graph.nodeArray[1]));

    System.out.println("\n\n--------------- Flytgraf3 ---------------");
    graph.newGraphFromFile("flytgraf3");
    System.out.println(graph.edmondKarp(graph.nodeArray[0], graph.nodeArray[1]));

    System.out.println("\n\n--------------- Flytgraf4 ---------------");
    graph.newGraphFromFile("flytgraf4");
    System.out.println(graph.edmondKarp(graph.nodeArray[0], graph.nodeArray[7]));

    System.out.println("\n\n--------------- Flytgraf5 ---------------");
    graph.newGraphFromFile("flytgraf5");
    System.out.println(graph.edmondKarp(graph.nodeArray[0], graph.nodeArray[7]));
  }
}

/**
 * Represents an edge as a linked-list in a graph used for <i>IDATT2101 oving 7</i>.
 *
 * @author Jens Christian Aanestad
 * @version 04.10.2023
 */
class Edge {
  Edge nextEdge;
  int flowValue;
  Node endNode;

  /**
   * Initializes an edge from <i>node</i> with the next edge.
   *
   * @param endNode the node from this edge.
   * @param nextEdge the next edge from this edge.
   * @param flowValue the value of this edge.
   */
  public Edge(Node endNode, Edge nextEdge, int flowValue) {
    this.endNode = endNode;
    this.nextEdge = nextEdge;
    this.flowValue = flowValue;
  }
}
/**
 * Represents a node in a graph used for <i>IDATT2101 oving 7</i>.
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
 * Represents a predecessor for a BFS algorithm used for <i>IDATT2101 oving 7</i>.
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
    distance = INFINITE;
  }
}

/**
 * Represents a queue for storing objects. Is used for <i>IDATT2101 oving 6</i>.
 * Much is taken from the book <i>Algoritmer og datastrukturer</i> on page 99.
 *
 * @author Jens Christian Aanestad
 * @version 03.10.2023
 */
class Queue {
  private final Object[] array;
  private int start = 0;
  private int end = 0;
  private int amount = 0;
  
  /**
   * Initializes a queue with a given size.
   *
   * @param size the size of the cue.
   */
  public Queue(int size) {
    array = new Object[size];
  }
  
  /**
   * Returns true if the amount attribute is 0.
   *
   * @return true if the amount attribute is 0. Returns false if otherwise.
   */
  public boolean isEmpty() {
    return amount == 0;
  }
  
  /**
   * Returns true if all slots in the array is occupied.
   *
   * @return true if all slots in the array is occupied. Returns false if otherwise.
   */
  public boolean isFull() {
    return amount == array.length;
  }
  
  /**
   * Puts an object in the queue. If the queue is full, the object will not be placed.
   *
   * @param e the object to put in the queue.
   */
  public void put(Object e) {
    if (isFull()) return;
    array[end] = e;
    end = (end + 1)% array.length;
    ++amount;
  }
  
  /**
   * Returns the object first in the queue, and removes it from the queue.
   *
   * @return the object first in the queue.
   */
  public Object next() {
    if (!isEmpty()) {
      Object e = array[start];
      start = (start + 1)% array.length;
      --amount;
      return  e;
    }
    else return null;
  }
  
  /**
   * Returns the object first in the queue without removing it from the queue.
   *
   * @return the object first in the queue.
   */
  public Object checkNext() {
    if (!isEmpty()) return array[start];
    else return null;
  }
}