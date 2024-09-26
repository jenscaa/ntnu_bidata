import java.util.Random;

class Quicksort1 {
  
  /*
  public static void bytt(int[] tabell, int indeks1, int indeks2) {
    if (indeks1 == indeks2) {
      return; // Ingen bytte er nødvendig hvis indeksene er like.
    }
    
    tabell[indeks1] = tabell[indeks1] ^ tabell[indeks2];
    tabell[indeks2] = tabell[indeks1] ^ tabell[indeks2];
    tabell[indeks1] = tabell[indeks1] ^ tabell[indeks2];
  }
  
  public static void innsettingssort(int []t) {
    for (int j = 1; j < t.length; ++j) {
      int bytt = t[j];
      //Sett t[j] på rett plass:
      int i = j - 1;
      while (i >= 0 && t[i] > bytt) {
        t[i + 1] = t[i];
        --i;
      }
      t[i + 1] = bytt;
    }
  }
  
  public static void innsettingssort(int[] t, int v, int h) {
    for (int j = v + 1; j <= h; ++j) {
      int bytt = t[j];
      int i = j - 1;
      while (i >= v && t[i] > bytt) {
        t[i + 1] = t[i];
        --i;
      }
      t[i + 1] = bytt;
    }
  }
  
  private static int median3sort(int []t, int v, int h) {
    int m = (v + h) / 2;
    if (t[v] > t[m]) bytt(t, v, m);
    if (t[m] > t[h]) {
      bytt(t, m, h);
      if (t[v] > t[m]) bytt(t, v, m);
    }
    return m;
  }
  public static void quicksort(int []t, int v, int h) {
    if (h - v > 2) {
      if (h - v <= 100) {
        innsettingssort(t, v, h);
      } else {
        int delepos = splitt(t, v, h);
        quicksort(t, v, delepos - 1);
        quicksort(t, delepos + 1, h);
      }
    } else median3sort(t, v, h);
  }
  
  private static int splitt(int []t, int v, int h) {
    int iv, ih;
    int m = median3sort(t, v, h);
    int dv = t[m];
    bytt(t, m, h - 1);
    for (iv = v, ih = h - 1;;) {
      while (t[++iv] < dv) ;
      while (t[--ih] > dv) ;
      if (iv >= ih) break;
      bytt(t, iv, ih);
    }
    bytt(t, iv, h-1);
    return iv;
  }
  
  public static int[] generateRandomValues(int []tabell) {
    Random random = new Random();
    for (int i = 0; i < tabell.length - 1; i++) {
      tabell[i] = random.nextInt(20);
    }
    return tabell;
  }
  
  public static void main(String[] args) {
    int []tabell = new int[1000];
    generateRandomValues(tabell);
    for (int i : tabell) System.out.println(i);
    System.out.println("\n");
    quicksort(tabell, 0, tabell.length-1);
    for (int i : tabell) System.out.println(i);
  }
  
   */
  
}