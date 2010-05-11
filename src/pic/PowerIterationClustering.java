package pic;

public class PowerIterationClustering {
  public static void getClustering(AffinityMatrix A , int k){
    PowerIteration pi = new PowerIteration(A);
    double v[] = pi.doIteration(1000 , 1.0e-5);
    double max = v[0];
    double min = v[0];
    for(int i = 1 ; i < v.length ; ++i){
      double d = v[i];
      if(d > max){
        max = d;
      }else if(min > d){
        min = d;
      }
    }
    // normalize v
    for(int i = 0 ; i < v.length ; ++i){
      v[i] = (v[i] - min) / (max - min);
      if(v[i] == 0.0)continue;
      System.err.println(i+"\t"+v[i]);
    }
    // TODO K-means clustering
  }  
}
