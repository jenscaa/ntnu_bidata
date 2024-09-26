import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class KalvskinnetMoholt {
  
  public static void main(String[] args) {
    Graph graph = new Graph();
    graph.newGraphFromFile("ø6Skandinavia");
    
    // BFS på Kalvskinnet
    graph.bfs(graph.nodeArray[37774]);
    writeFile(graph.bfsResult(), "BFS_Result_Kalvskinnet.txt");
  
    // BFS på Moholt
    graph.bfs(graph.nodeArray[18058]);
    writeFile(graph.bfsResult(), "BFS_Result_Moholt.txt");
  
  }
  
  public static void writeFile(String content, String fileName) {
    
    File file = new File("D:/Random stuff/" + fileName);
    
    // Chaining with try-resource to automatically close the file if it fails.
    try (java.io.FileWriter fileWriter = new java.io.FileWriter(file);
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
      
      printWriter.print(content);
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

