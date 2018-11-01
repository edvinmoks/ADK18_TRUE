public class Edge{
  int to, from, flow, capacity, rest;
  Edge opposite;
  public Edge(int from, int to, int flow, int capacity, Edge opposite){
    this.from = from;
    this.to = to;
    this.flow = flow;
    this.capacity = capacity;
    this.opposite = opposite;
    this.rest = 0;
  }
}
