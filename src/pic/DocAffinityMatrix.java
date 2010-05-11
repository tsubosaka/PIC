package pic;

public class DocAffinityMatrix implements AffinityMatrix{
  double N[];
  private double F[][];  
  public DocAffinityMatrix(double F[][]) {    
    int row = F.length;
    this.F = F;
    N = new double[row];
    for(int i = 0 ; i < row ; ++i){
      double s = 0.0;
      for(int j = 0 ; j < F[i].length ; ++j){
        s += F[i][j] * F[i][j];
      }
      N[i] = 1.0 / Math.sqrt(s);
    }
  }
  
  @Override
  public int getDim() {
    return F.length;
  }
  
  @Override
  public void multiply(double[] x, double[] y) throws IllegalArgumentException {
    int n = F.length;
    if(x.length != n || y.length != n)
      throw new IllegalArgumentException();
    // Nv^T
    for(int i = 0 ; i < n ; ++i){
      y[i] = N[i] * x[i];
    }
    double C[] = new double[F[0].length];
    // F^T (Nv^T)
    for(int col = 0 ; col < C.length ; ++col){
      double s = 0.0;
      for(int row = 0 ; row < n ; ++row){
        s += F[row][col] * y[row];
      }
      C[col] = s;
    }
    // F(F^T(Nv^T))
    for(int row = 0 ; row < n ; ++row){
      double s = 0.0;
      for(int col = 0 ; col < C.length ; ++col){
        s += F[row][col] * C[col];
      }
      y[row] = s;
    }
    // N(F(F^T(Nv^T)))
    for(int i = 0 ; i < n ; ++i){
      y[i] = N[i] * y[i];
    }
  }

}
