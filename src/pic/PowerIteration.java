package pic;

import java.util.Arrays;

public class PowerIteration {
  final private AffinityMatrix A;
  final private NormalizedAffinityMatrix W;
  
  public PowerIteration(AffinityMatrix A) {
    this.A = A;
    this.W = new NormalizedAffinityMatrix(A);
  }
  
  private static double norm1(double vector[]){
    double ret = 0.0;
    for(double v : vector){
      ret += Math.abs(v);
    }
    return ret;
  }

  private double[] getInitialVector(){
    int n = A.getDim();
    double v[] = new double[n];
    double ovec[] = new double[n];
    Arrays.fill(ovec, 1.0);
    // calculate degree matrix D = A * 1
    A.multiply(ovec, v);
    double sum = 0.0;
    for(double d : v){
      sum += d;
    }
    double sinv = 1.0 / sum;
    for(int i = 0 ; i < n ; ++i){
      v[i] *= sinv;
    }
    return v;
  }

  public double[] doIteration(int maxIterationNum , double eps){
    int n = A.getDim();
    // pick an initial vector
    double v[] = getInitialVector();
    double nv[] = new double[n];
    for(int iter = 0 ; iter < maxIterationNum ; ++iter){
      // nv = W * v
      W.multiply(v, nv);
      double normInv = 1.0 / norm1(nv);
      for(int d = 0 ; d < n ; ++d){
        nv[d] *= normInv;
      }
      double delta = 0.0;
      for(int d = 0 ; d < n ; ++d){
        delta += Math.abs(nv[d] - v[d]);
      }
//      System.err.println(iter+" "+delta+" "+normInv);
      if(delta * n < eps){
        v = nv;
        break;
      }
      // swap v for nv
      double tmp[] = v;
      v = nv;
      nv  = tmp;
    }
    return v;
  }  
}
