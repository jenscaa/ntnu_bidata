// Note: This is not source code for Øving 4. This is a test material for deloppgave 2. This should present an error.

/*
class ExampleErrorInput {
  
  public static void bytt(int[] tabell, int indeks1, int indeks2) {
    int copyIndeks1 = tabell[indeks1];
    int copyIndeks2 = tabell[indeks2];
    tabell[indeks1] = copyIndeks2;
    tabell[indeks2] = copyIndeks1;
  }
  
  public static void boblesort(int[] t) {
    for (int i = t.length - 1; i > 0; --i) {
      for (int j = 0; j < i; ++j) {
        if (t[j] > t[j + 1]) {
          bytt(t, j, j + 1);
        }
      }
    }
  }
  
  
  public static void velgesort(int []t) {
    for (int i = t.length - 1; i > 0; --i) {
      int max = 0;
      for (int j = 1; j < i; ++j)) {
        if (t[j] > t[max]) max = j;
      }
      if (max != i) {
        bytt(t, i, max);
      }
    }
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
  
  public static void flettesort(int []t, int v, int h) {
    if (v < h) {
      int m = (v + h) / 2;
      flettesort(t, v, m);
      flettesort(t, m + 1, h);
      flett(t, v, m, h);
    }
  }
  
  public static void
  flett(int []t, int v, int m, int h) {
    int []ht = new int[h - v + 1];
    int i = 0, j = v, k = m + 1;
    while (j <= m && k <= h) {
      ht[i++] = (t[j] <= t[k]) ?
              t[j++] : t[k++];
    }
    while (j <= m) ht[i++] = t[j++];
    for (i = v; i < k; ++i) t[i] = ht[i - v];}
  
  
  public static void
  quicksort(int []t, int v, int h) {
    if (h - v > 2) {
      int delepos = splitt(t, v, h);
      quicksort(t, v, delepos - 1);
      quicksort(t, delepos + 1, h);
    } else median3sort(t, v, h);
  }
  
  private static int
  median3sort(int []t, int v, int h) {
    int m = (v + h) / 2;
    if (t[v] > t[m]) bytt(t, v, m);
    if (t[m] > t[h]) {
      bytt(t, m, h);
      if (t[v] > t[m]) bytt(t, v, m);
    }
    return m;
  }
  
  private static int
  splitt(int []t, int v, int h) {
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
  
  public static void tellesort(
          int []inn, int []ut, int k) {
    int i, n = inn.length;
    int []ht = new int[k + 1];
    for (i = 0; i <= k; ++i) ht[i] = 0;
    for (i = 0; i < n; ++i) ++ht[inn[i]];
    for (i = 1; i <= k; ++i)
      ht[i] += ht[i - 1];
    for (i = n - 1; i >= 0; --i)
      ut[--ht[inn[i]]] = inn[i];
  }
  
  
  
  
  public static void main(String[] args) {
    int[] t = new int[5];
    t[0] = 2;
    t[1] = 5;
    t[2] = 3;
    t[3] = 4;
    t[4] = 1;
    
    for (int i : t) System.out.println(i);
    
    boblesort(t);
    System.out.println("\nBoblesort: ");
    for (int i : t) System.out.println(i);
    
    System.out.println("\nTilbkestilt: ");
    t[0] = 2;
    t[1] = 5;
    t[2] = 3;
    t[3] = 4;
    t[4] = 1;
    for (int i : t) System.out.println(i);
    
    velgesort(t);
    System.out.println("\nVelgesort: ");
    for (int i : t) System.out.println(i);
    
    System.out.println("\nTilbkestilt: ");
    t[0] = 2;
    t[1] = 5;
    t[2] = 3;
    t[3] = 4;
    t[4] = 1;
    for (int i : t) System.out.println(i);
    
    innsettingssort(t);
    System.out.println("\nInnsettingssortering: ");
    for (int i : t) System.out.println(i);
    
    System.out.println("\nTilbkestilt: ");
    t[0] = 2;
    t[1] = 5;
    t[2] = 3;
    t[3] = 4;
    t[4] = 1;
    for (int i : t) System.out.println(i);
    
    flettesort(t, 0, t.length-1);
    System.out.println("\nFlettesortering: ");
    for (int i : t) System.out.println(i);
    
    System.out.println("\nTilbkestilt: ");
    t[0] = 2;
    t[1] = 5;
    t[2] = 3;
    t[3] = 4;
    t[4] = 1;
    for (int i : t) System.out.println(i);
    
    quicksort(t, 0, t.length-1);
    System.out.println("\nQuicksort: ");
    for (int i : t) System.out.println(i);
    
    System.out.println("\nTilbkestilt: ");
    t[0] = 2;
    t[1] = 5;
    t[2] = 3;
    t[3] = 4;
    t[4] = 1;
    for (int i : t) System.out.println(i);
    
    int[] u = new int[5];
    tellesort(t, u, 5);
    System.out.println("\nTellesortering: ");
    for (int i : u) System.out.println(i);
  }
}
*/