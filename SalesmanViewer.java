import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class SalesmanViewer extends JPanel implements ActionListener {
    public static final int DIM_X = 800;
    public static final int DIM_Y = 800;
    public static final int POINT_RADIUS = 4;
    private JButton b1, b2, b3, b4;
    private JPanel bottomButtons = new JPanel();
    private boolean toggle = true;
    
    public ArrayList<Line2D> tour = new ArrayList<Line2D>();
    public ArrayList<Line2D> tree = new ArrayList<Line2D>();
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("bf")){
            tree = new ArrayList<Line2D>();
            tour = TSPBruteForce.computeBruteForce(points);
        }
        else if (e.getActionCommand().equals("nn")){
            tree = new ArrayList<Line2D>();
            tour = TSPNearestNeighbor.computeNearestNeighbor(points);
        }
        else if (e.getActionCommand().equals("2mst")){
            tree = TSPMinimumSpanningTree.computeMST(points);
            tour = TSPMinimumSpanningTree.computeDFSTour(points, tree);
        }
        else if (e.getActionCommand().equals("toggle")){
            toggle = !toggle;
        }
        repaint();
    }
    public ArrayList<Point2D> points = new ArrayList<>();
    public SalesmanViewer(int n){  
        Random r = new Random();
        for (int i = 0; i < n; i++){
            Point2D p = new Point2D(r.nextDouble(), r.nextDouble());
            points.add(p);
        }
        setPreferredSize(new Dimension(DIM_X, DIM_Y));
        bottomButtons.setLayout(new GridLayout(1,4));
        b1 = new JButton("Brute Force");
        b1.setActionCommand("bf");
        b2 = new JButton("Nearest Neighbor");
        b2.setActionCommand("nn");
        b3 = new JButton("2-Minimum Spanning Tree");
        b3.setActionCommand("2mst");
        b4 = new JButton("Toggle Lines");
        b4.setActionCommand("toggle");
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        bottomButtons.add(b1);
        bottomButtons.add(b2);
        bottomButtons.add(b3);
        bottomButtons.add(b4);
    }
    public JPanel getButtons(){
        return bottomButtons;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawPoints(g);
        drawLines(g);
    }
    public void drawPoints(Graphics g){
        g.setColor(Color.BLACK);
        for (int i = 0; i < points.size(); i++){
            Point2D p = points.get(i);
            double x = p.getX();
            double y = p.getY();
            int ix = (int)(DIM_X*x);
            int iy = (int)(DIM_Y*y);
            g.drawOval(ix-POINT_RADIUS, iy-POINT_RADIUS, POINT_RADIUS*2, POINT_RADIUS*2);
        }
    }
    public void drawLines(Graphics g){
        g.setColor(Color.RED);
        for (Line2D line : tour){
            Point2D p1 = line.getP1(), p2 = line.getP2();
            int ix1 = (int)(DIM_X*p1.getX());
            int iy1 = (int)(DIM_Y*p1.getY());
            int ix2 = (int)(DIM_X*p2.getX());
            int iy2 = (int)(DIM_Y*p2.getY());
            g.drawLine(ix1, iy1, ix2, iy2);
        }
        if (toggle){
            g.setColor(Color.BLUE);
            for (Line2D line : tree){
                Point2D p1 = line.getP1(), p2 = line.getP2();
                int ix1 = (int)(DIM_X*p1.getX());
                int iy1 = (int)(DIM_Y*p1.getY());
                int ix2 = (int)(DIM_X*p2.getX());
                int iy2 = (int)(DIM_Y*p2.getY());
                g.drawLine(ix1, iy1, ix2, iy2);
            }
        }
    }
    public static void main(String[] args){
        JFrame j = new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLayout(new BorderLayout());
        int n = 100;
        if (args.length > 0){
            n = Integer.parseInt(args[0]);
        }
        SalesmanViewer sv = new SalesmanViewer(n);
        j.add(sv, BorderLayout.CENTER);
        j.add(sv.getButtons(), BorderLayout.PAGE_END);
        j.setVisible(true);
        j.setResizable(false);
        j.setTitle("TSP Viewer");
        j.pack();
    }
}
