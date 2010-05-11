package pic;

public class SparseDocAffinityMatrix implements AffinityMatrix{
  double N[];
//  private double F[][];
  Row F[];
  int C;
  public SparseDocAffinityMatrix(Row mat[] , int C) {    
    F = mat;
    N = new double[mat.length];
    for(int i = 0 ; i < mat.length ; ++i){
      N[i] = 1.0 / mat[i].norm();
    }
    this.C = C;
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
    double vec[] = new double[C];
    // F^T (Nv^T)
    for(int row = 0 ; row < n ; ++row){
      for(Elem e : F[row].list){
        vec[e.col] += e.weight * y[row];
      }
    }
    // F(F^T(Nv^T))
    for(int row = 0 ; row < n ; ++row){
      double s = 0.0;
      for(Elem e : F[row].list){
        s += e.weight * vec[e.col];
      }
      y[row] = s;
    }
    // N(F(F^T(Nv^T)))
    for(int i = 0 ; i < n ; ++i){
      y[i] = N[i] * y[i];
    }
  }

}
