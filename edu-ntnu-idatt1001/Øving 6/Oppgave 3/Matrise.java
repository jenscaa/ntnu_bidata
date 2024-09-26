import static javax.swing.JOptionPane.*;

import java.util.ArrayList;
import java.util.List;
final class Matrise {
    private int m = -1;
    private int n = -1;
    private final String[] matriseRader;
    private final int[][] matriseTabell;

    public Matrise(String matrise) {
        int k = -1;
        matriseRader = matrise.split("],");
        for (String i : matriseRader) {
            this.m ++;
        } for (int i = 0; i <= m; i++) {
            this.matriseRader[i] = matriseRader[i].replace("[", "").replace("]", "");
        }
 
        String[] rader = matriseRader[0].split(",");
        for (String i : rader) {
            n++;
        
        } for (int i = 0; i <= m; i++) {
            rader = matriseRader[i].split(",");
            for (String j : rader) {
                k++;
            }
            if (k != n) {
                throw new IllegalArgumentException("Ufullstendig matrise. Start programmet på nytt");
            }    

            k = -1;
        }
        
        int[][] matrix = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                matrix[i][j] = Integer.parseInt(matriseRader[i].split(",")[j]); 
            }
        }
        matriseTabell = matrix;


    }

    public int[][] getMatrisetabell(String matrise){
        Matrise matrix = new Matrise(matrise);
        int[][] matrix2 = matrix.matriseTabell;
        return matrix2;
    }

    public int getM(String matrise){
        Matrise matrix = new Matrise(matrise);
        int m = matrix.m;
        return m;
    }

    public int getN(String matrise){
        Matrise matrix = new Matrise(matrise);
        int n = matrix.n;
        return n;
    }


    public String addere(int[][] matrise1, int[][] matrise2, int m_matrise1, int n_matrise1, int m_matrise2, int n_matrise2) {
        if (m_matrise1 == m_matrise2 && n_matrise1 == n_matrise2){
            int[][] matrise3 = new int[m_matrise1 + 1][n_matrise2 + 1];
            String matriseString3  = "";
            String matriseString1  = "";
            String matriseString2  = "";
            for (int i = 0; i <= m_matrise1; i++) {
                for (int j = 0; j <= n_matrise1; j++) {
                    matrise3[i][j] = matrise1[i][j] + matrise2[i][j];
                    matriseString3 += String.valueOf(matrise3[i][j]) + "  ";
                    matriseString1 += String.valueOf(matrise1[i][j]) + "  ";
                    matriseString2 += String.valueOf(matrise2[i][j]) + "  ";
                }
                matriseString3 += "\n";
                matriseString1 += "\n";
                matriseString2 += "\n";
            }
            return matriseString1 + " \n + \n \n" + matriseString2 + "\n = \n \n" + matriseString3;
        } else {
            return null;
        }
    }

    public String multiplisere(int[][] matrise1, int[][] matrise2, int m_matrise1, int n_matrise1, int m_matrise2, int n_matrise2) {
        if (n_matrise1 == m_matrise2){
            int c = 0;
            int[][] matrise3 = new int[m_matrise1 + 1][n_matrise2 + 1];
            String matriseString3  = "";
            String matriseString1  = "";
            String matriseString2  = "";
            for (int i = 0; i <= m_matrise1; i++) {
                for (int j = 0; j <= n_matrise2; j++) {
                    for (int k = 0; k <= m_matrise2; k++) {
                        c += matrise1[i][k] * matrise2[k][j];
                    }
                    matrise3[i][j] = c;
                    c = 0;
                }
            } for (int i = 0; i <= m_matrise1; i++) {
                for (int j = 0; j <= n_matrise2; j++) {
                    matriseString3 += String.valueOf(matrise3[i][j]) + "  ";
                }
                matriseString3 += "\n";
            } for (int i = 0; i <= m_matrise1; i++) {
                for (int j = 0; j <= n_matrise1; j++) {
                    matriseString1 += String.valueOf(matrise1[i][j]) + "  ";
                }
                matriseString1 += "\n";
            } for (int i = 0; i <= m_matrise2; i++) {
                for (int j = 0; j <= n_matrise2; j++) {
                    matriseString2 += String.valueOf(matrise2[i][j]) + "  ";
                }
                matriseString2 += "\n";
            }
            return matriseString1 + " \n * \n \n" + matriseString2 + "\n = \n \n" + matriseString3;
        } else {
            return null;
        }
    }

    public String transponere(int[][] matrise1, int m_matrise1, int n_matrise1) {
        int[][] matrise3 = new int[n_matrise1 + 1][m_matrise1 + 1];
        String matriseString1  = "";
        String matriseString3  = "";
        for (int i = 0; i <= n_matrise1; i++) {
            for (int j = 0; j <= m_matrise1; j++) {
                matrise3[i][j] = matrise1[j][i];
            }
        } for (int i = 0; i <= n_matrise1; i++) {
            for (int j = 0; j <= m_matrise1; j++) {
                matriseString3 += String.valueOf(matrise3[i][j]) + "  ";
            }
            matriseString3 += "\n";
        } for (int i = 0; i <= m_matrise1; i++) {
            for (int j = 0; j <= n_matrise1; j++) {
                matriseString1 += String.valueOf(matrise1[i][j]) + "  ";
            }
            matriseString1 += "\n";
        }
        return matriseString1 + "\n transponert: \n \n" + matriseString3;
    }
}