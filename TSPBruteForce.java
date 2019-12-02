import java.util.ArrayList;
public class TSPBruteForce {
    public static ArrayList<Line2D> computeBruteForce(ArrayList<Point2D> points){
        ArrayList<Line2D> tour = new ArrayList<Line2D>();
        ArrayList<Point2D> firstPoint = new ArrayList<>();
        firstPoint.add(points.get(0));
        ArrayList<Point2D> otherPoints = new ArrayList<>();
        for (int i = 1; i < points.size(); i++){
            otherPoints.add(points.get(i));
        }
        ArrayList<Point2D> bestTour = recursiveBruteForce(firstPoint, otherPoints);
        for (int i = 0; i < bestTour.size(); i++){
            Point2D p = bestTour.get(i), q = bestTour.get((i+1)%bestTour.size());
            tour.add(new Line2D(p, q));
        }
        return tour;
    }
    public static ArrayList<Point2D> recursiveBruteForce(ArrayList<Point2D> currentTour, 
                                                  ArrayList<Point2D> remainingVertices){
        if (remainingVertices.size() == 0){
            return currentTour;
        }
        else {
            ArrayList<Point2D> bestTour = new ArrayList<>();
            double bestLength = Double.MAX_VALUE;
            for (Point2D p : remainingVertices){
                ArrayList<Point2D> newCurrent = new ArrayList<>();
                ArrayList<Point2D> newRemaining = new ArrayList<>();
                for (Point2D q : currentTour) newCurrent.add(q);
                for (Point2D q : remainingVertices) if (p != q) newRemaining.add(q);
                newCurrent.add(p);
                ArrayList<Point2D> someTour = recursiveBruteForce(newCurrent, newRemaining);
                double length = getTourLength(someTour);
                if (length < bestLength){
                    bestTour = someTour;
                    bestLength = length;
                }
            }
            return bestTour;
        }
    }
    public static double getTourLength(ArrayList<Point2D> order){
        double sum = 0;
        for (int i = 0; i < order.size(); i++){
            Point2D p = order.get(i), q = order.get((i+1)%order.size());
            sum += p.distance(q);
        }
        return sum;
    }
}
