package pic;

public class DenseAffinityMatrix implements AffinityMatrix{
  
  private double A[][];  
  public DenseAffinityMatrix(double A[][]) {
    int row = A.length;
    if(row == 0){
      throw new IllegalArgumentException("row must be positive");
    }
    int col = A[0].length;
    if(row != col){
      throw new IllegalArgumentException("row and column must be equal number");      
    }
    this.A = A;
  }
  
  @Override
  public int getDim() {
    return A.length;
  }
  
  @Override
  public void multiply(double[] x, double[] y) throws IllegalArgumentException {
    int n = A.length;
    if(x.length != n || y.length != n)
      throw new IllegalArgumentException();
    
    for(int i = 0 ; i < n ; ++i){
      double s = 0.0;
      for(int j = 0 ; j < n ; ++j){
        s += A[i][j] * x[j];
      }
      y[i] = s;
    }
  }

}
