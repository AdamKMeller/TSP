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
public class TSPMinimumSpanningTree {
    public static ArrayList<Line2D> computeMST(ArrayList<Point2D> points){
    	// Initialize variables
    	ArrayList<Point2D> mstSet = new ArrayList<>();
    	//ArrayList<Point2D> inputSet = points;
        ArrayList<Point2D> inputSet = new ArrayList<Point2D>();
        for(int i=0;i<points.size();i++)
        {
            inputSet.add(points.get(i));
        }
    	ArrayList<Line2D> mstEdges = new ArrayList<>();
    	
    	// Distance values used to pick point to add to MST
    	ArrayList<Double> dists = new ArrayList<>();
    	// Variable to track of which node in MST provides start of edge in dists
    	ArrayList<Integer> fromNode = new ArrayList<>();
    	
    	// Begin Prim's algorithm at vertex 0
    	mstSet.add(points.get(0));
    	inputSet.remove(0);
    	
    	for (int i = 0; i < inputSet.size(); i++)
    	{
    		double dist = mstSet.get(0).distance(inputSet.get(i));
    		dists.add(i, dist);
    		fromNode.add(0);
    	}
    	//System.out.println(inputSet.size());
		//System.out.println(dists.size());
		//System.out.println(fromNode.size());
    	
    	while (!inputSet.isEmpty())
    	{
    		//System.out.println(inputSet);
    		//System.out.println(dists);
    		//System.out.println(fromNode);
    		
    		// Get minimum value in distances
    		double minDist = Double.MAX_VALUE;
    		int minIndex = 0;
    		for (int i = 0; i < dists.size();  i++)
    		{
    			if (minDist > dists.get(i))
    			{
    				minDist = dists.get(i);
    				minIndex = i;
    			}
    		}
    		
    		// Add closest point to mstSet and add edge from nearest point
    		mstSet.add(inputSet.get(minIndex));
    		Line2D edgeToAdd = new Line2D(mstSet.get(fromNode.get(minIndex)), inputSet.get(minIndex));
    		mstEdges.add(edgeToAdd);
    		
    		// Remove point from inputSet as well as dists and fromNode
    		inputSet.remove(minIndex);
    		dists.remove(minIndex);
    		fromNode.remove(minIndex);
    		
    		// Update distances for points not yet in mstSet
    		for (int i = 0; i < inputSet.size(); i++)
    		{
    			for (int j = 0; j < mstSet.size(); j++)
    			{
    				double dist = inputSet.get(i).distance(mstSet.get(j));
    				if (dist <= dists.get(i))
    				{
    					dists.set(i, dist);
    					fromNode.set(i, j);
    				}
    			}
    		}
    		
    		
    		
    	}
    	
		return mstEdges;
    }
    
    
    
    public static ArrayList<Line2D> computeDFSTour(ArrayList<Point2D> points, ArrayList<Line2D> mst){
	ArrayList<Line2D> tour = new ArrayList<Line2D>();
        ArrayList<Point2D> eulpath = new ArrayList<Point2D>();
        //This will be our higher prioritized mst
        ArrayList<Line2D> mst1 = new ArrayList<Line2D>();
        for(int i=0;i<mst.size();i++)
        {
            mst1.add(mst.get(i));
        }
        // our lower prioritized mst to make sure all points are hit        
        ArrayList<Line2D> mst2 = new ArrayList<Line2D>();
        for(int i=0;i<mst.size();i++)
        {
            mst2.add(mst.get(i));
        }
        Point2D origin = mst.get(0).getP1();
        eulpath.add(origin);
        origin.setVisited(true);
        
        
        
        
        
        //control flow
        boolean oneOP = false;
        
        //builds eulirian circuit 
        while(!mst2.isEmpty())
        {
            oneOP=false;
            Point2D currentP = eulpath.get(eulpath.size()-1);
            for(int x = 0; x<mst1.size();x++)
            {
                //if(pointsEqual(currentP,mst1.get(x).getP1())&& oneOP==false)
                if(!oneOP)
                {
                if(currentP==mst1.get(x).getP1())
                {
                    eulpath.add(mst1.get(x).getP2());
                    mst1.remove(x);
                    oneOP=true;
                }
                }
                
                //if(pointsEqual(currentP,mst1.get(x).getP2())&& oneOP==false)
                if(!oneOP)
                {
                if(currentP==mst1.get(x).getP2())
                {
                    eulpath.add(mst1.get(x).getP1());
                    mst1.remove(x);
                    oneOP=true;
                }
                }
            }
        if(!oneOP)
        {
            for(int x = 0; x<mst2.size();x++)
            {
                //if(pointsEqual(currentP,mst2.get(x).getP1())&& oneOP==false)
                if(!oneOP)
                {
                if(currentP==mst2.get(x).getP1())
                {
                    eulpath.add(mst2.get(x).getP2());
                    mst2.remove(x);
                    oneOP=true;
                }
                }
                
                //if(pointsEqual(currentP,mst2.get(x).getP2())&& oneOP==false)
                if(!oneOP)
                {
                if(currentP==mst2.get(x).getP2())
                {
                    eulpath.add(mst2.get(x).getP1());
                    mst2.remove(x);
                    oneOP=true;
                }
                }
            }
        }
           // System.out.println(eulpath);
        } 
        //cleans circuit to have optimal path
        ArrayList<Point2D> euluniquepath  = new ArrayList<Point2D>();
        for(int y= 0;y<eulpath.size();y++)
        {
            if(!euluniquepath.contains(eulpath.get(y)))
            {
                euluniquepath.add(eulpath.get(y));
            }              
        }
        //builds tour from points
        for(int z = 0; z< euluniquepath.size()-1;z++)
        {
            tour.add(new Line2D(euluniquepath.get(z),euluniquepath.get(z+1)));
        }
        tour.add(new Line2D(euluniquepath.get(0),euluniquepath.get(euluniquepath.size()-1)));
        return tour;
    }
    
    private static boolean pointsEqual(Point2D p,Point2D q)
    {
        return p.getX()==q.getX() && p.getY()==q.getY();
    }
    
    private static ArrayList<Point2D> dfs(Point2D p, ArrayList<Line2D> mst)
    {
        return new ArrayList<Point2D>();
    }
    
}

