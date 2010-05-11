package pic;

import java.util.ArrayList;
import java.util.List;

class Elem implements Comparable<Elem>{
  int col;
  double weight;
  public Elem(int c , double w) {
    col = c;
    weight = w;
  }
  @Override
  public int compareTo(Elem o) {
    return col - o.col;
  }
}

public class Row {
  List<Elem> list;
  public Row() {
    list = new ArrayList<Elem>();
  }
  
  public void add(int c , double w){
    list.add(new Elem(c , w));
  }
  
  double norm(){
    double ret = 0.0;
    for(Elem e : list){
      ret += e.weight * e.weight;
    }
    return Math.sqrt(ret);
  }
}
