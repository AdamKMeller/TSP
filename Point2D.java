
/**
 *
 * @author adammeller
 */
public class Point2D {
    private double x;
    private double y;
    private boolean visited = false;
    public Point2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){ return x; }
    public double getY(){ return y; }
    public double distance(Point2D q){
        double dx = getX()-q.getX(), dy = getY()-q.getY();
        return Math.sqrt(dx*dx+dy*dy);
    }
    public boolean isVisited(){
        return visited;
    }
    public void setVisited(boolean v){
        visited = v;
    }
}
