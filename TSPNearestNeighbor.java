/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING ANYSOURCES
OUTSIDE OF THOSE APPROVED BY THE INSTRUCTOR. Adam Meller, 2343923
 *
 * @author adammeller
 */
import java.util.ArrayList;
public class TSPNearestNeighbor {
    public static ArrayList<Line2D> computeNearestNeighbor(ArrayList<Point2D> points){
        ArrayList<Point2D> points1 = new ArrayList<Point2D>();
        for(int i=0;i<points.size();i++)
        {
            points1.add(points.get(i));
        }
        
        Point2D start = points1.get(0);
        points1.remove(0);
        Point2D origin = start;
        ArrayList<Line2D> tour = new ArrayList<Line2D>();
        while(points1.isEmpty()==false)
        {
            int nearN = 0;
            double tempDist = dist(start,points1.get(nearN));
            for(int x = 1; x<points1.size();x++)
            {
                if(tempDist>dist(start,points1.get(x)))
                {
                    nearN=x;
                    tempDist = dist(start,points1.get(x));
                }
            }
            tour.add(new Line2D(start,points1.get(nearN)));
            start = points1.get(nearN);
            points1.remove(nearN);
            
        }
        tour.add(new Line2D(start,origin));
		return tour;
    }
    
    private static double dist(Point2D p, Point2D q)
    {
        double x1 = p.getX();
        double x2 = q.getX();
        double y1 = p.getY();
        double y2 = q.getY();
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
