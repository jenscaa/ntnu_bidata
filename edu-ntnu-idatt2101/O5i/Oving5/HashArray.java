import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Represents the solution for IDATT2101 oving 5 deloppgave 1: Hashtabell med tekstnøkler.
 *
 * @author Jens Christian Aanestad
 * @version 26.09.2023
 * @see LinkedList
 * @see Node
 */
public class HashArray {
  private final LinkedList[] array;
  private int collisions = 0;
  
  /**
   * Initializes the array with given size and initializes each index
   * with an empty LinkedList.
   *
   * @param size the size of the array
   */
  public HashArray(int size) {
    this.array = new LinkedList[size];
    for (int i = 0; i < size; i++) {
      this.array[i] = new LinkedList();
    }
  }
  
  /**
   * Returns the size of the array.
   *
   * @return the size of the array.
   */
  public int getSize() {
    return array.length;
  }
  
  /**
   * Returns the amount of collisions.
   *
   * @return number of collisions.
   */
  public int getCollisions() {
    return collisions;
  }
  
  /**
   * The hash function for a String. Iterates through all the chars
   * in the String, and weights them separately. This method is much
   * taken from IDATT2101 Lecture 6 26.09.23.
   *
   * @param key the String to use as key/index.
   * @param n the size of the array.
   * @return the hash from this String.
   */
  public static int hashFunction(String key, int n) {
    int hash = 0;
    for (int i = 0; i < key.length(); i++) {
      hash = (17*hash + key.charAt(i)) % n ;
    }
    return hash ;
  }
  
  /**
   * Puts a name in the array at an index provided by the hash function of the name.
   * For each collision, the method prints to the terminal which name collided with which name.
   *
   * @param name the name to put in array.
   */
  public void putIn(String name) {
    int index = hashFunction(name, getSize());
    if (array[index].head.hasValue()) {
      System.out.printf(
              "Kollisjon på indeks %d%s mellom %s og %s %n"
              ,index, " ".repeat(3 - String.valueOf(index).length())
              ,name, array[index].head.getValue());
      collisions++;
      array[index].placeNodeBehind(name);
    } else {
      array[index].head.SetValue(name);
    }
  }
  
  /**
   * Finds the index position to the provided name. Then looks through the
   * linked list to confirm that the name that was provided exists in the linked list.
   * The method returns a String representation of this information.
   *
   * @param name the name to find in the array.
   * @return String representation of confirmation and index to the name provided.
   */
  public String findName(String name) {
    int index = hashFunction(name, getSize());
    Node currentNode = array[index].head;
    while (currentNode != null) {
      if (name.equals(currentNode.getValue())) {
        return String.format("Fant %s på indeks %d", name, index);
      }
      currentNode = currentNode.nextNode;
    }
    return String.format("Fant ikke %s", name);
  }
  
  /**
   * Finds the load factor for the array.
   *
   * @return the load factor for the array.
   */
  public double findLoadFactor() {
    int n = 0;
    for (LinkedList list : array) if (list.getHead().hasValue()) n++;
    int m = getSize();
    
    return (double) n/m;
  }
  
  /**
   * Reads the file navn.txt from <i>https://www.idi.ntnu.no/emner/idatt2101/hash/navn.txt</i>
   * and returns a list of names from the file.
   *
   * @return list of names from the file.
   */
  public static List<String> readFile() {
    String fileUrl = "https://www.idi.ntnu.no/emner/idatt2101/hash/navn.txt";
    List<String> listOfNames = new ArrayList<>();
    try {
      URL url = new URL(fileUrl);
      // Using BufferedReader to read from file
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        listOfNames.add(line);
      }
      reader.close();
      return listOfNames;
      
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * Main method - Entry point.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    List<String> listOfNames = readFile();
    HashArray ht = new HashArray(listOfNames.size() + 10);
    
    for (String name : listOfNames) {
      ht.putIn(name);
    }
  
    // Resultat.
    System.out.printf("\nAntall kolisjoner: %d%n", ht.getCollisions());
    System.out.printf("Antall kollisjoner/person: %f%n",
            (double) ht.getCollisions()/ht.getSize());
    System.out.printf("Lastfaktor: %f%n", ht.findLoadFactor());
  
    // Find meg selv.
    System.out.printf("\n%s%n", ht.findName("Jens Christian Aanestad"));
  }
}
