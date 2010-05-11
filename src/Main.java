import java.io.File;

import java.util.Scanner;

import pic.PowerIterationClustering;
import pic.Row;
import pic.SparseDocAffinityMatrix;


public class Main {
  public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(new File("bbc/bbc.mtx"));
    while(sc.findInLine("%.*") != null){
      sc.nextLine();
    }    
    int col = sc.nextInt();
    int row = sc.nextInt();
    int R = sc.nextInt();
    Row mat[] = new Row[row];
//    double F[][] = new double[row][col];
    for(int i = 0 ; i < row ; ++i)
      mat[i] = new Row();
    for(int i = 0 ; i < R ; ++i){      
      int c = sc.nextInt() - 1;
      int r = sc.nextInt() - 1;
      double w = sc.nextDouble();
      mat[r].add(c, w);
//      F[r][c] = w;
    }
//    DocAffinityMatrix dam = new DocAffinityMatrix(F);
//    long t = System.currentTimeMillis();
    SparseDocAffinityMatrix dam = new SparseDocAffinityMatrix(mat, col);
    PowerIterationClustering.getClustering(dam, 2);    
//    System.out.println(System.currentTimeMillis() - t);
  }
}
