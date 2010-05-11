package pic;

import java.util.Arrays;

public class NormalizedAffinityMatrix implements AffinityMatrix{
  private AffinityMatrix A;
  private double dinv[];
  public NormalizedAffinityMatrix(AffinityMatrix A) {
    this.A = A;
    init();
  }
  
  private void init(){
    int dim = A.getDim();
    dinv = new double[dim];
    double ovec[] = new double[dim];
    Arrays.fill(ovec, 1.0);
    // calculate degree matrix D = A * 1
    A.multiply(ovec, dinv);
    // inverse each element
    for(int i = 0 ; i < dim ; ++i){
      if(dinv[i] == 0.0){
        continue;
      }
      dinv[i] = 1.0 / dinv[i];
    }
  }
  
  @Override
  public int getDim() {
    return A.getDim();
  }
  
  @Override
  public void multiply(double x[] , double y[]){
    A.multiply(x, y);
    for(int i = 0 ; i < A.getDim() ; ++i){
      y[i] *= dinv[i];
    }
  }
}
