package pic;

public interface AffinityMatrix {
  /**
   * return dimension of this Matrix
   * @return dimension of this Matrix
   */
  public int getDim();
      
  /**
   * 
   * calculate matrix-vector multiply y = this * x
   * @param x source vector
   * @param y vector where results a stored
   * @throws IllegalArgumentException A.getDim() != x.length || x.length != y.length
   * 
   */
  void multiply(double x[] , double y[]) throws IllegalArgumentException;
}
