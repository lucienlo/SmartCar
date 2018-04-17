package UI.components;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by lucienlo on 2018/4/17.
 */


public class SimulatedMap extends JPanel{

    public final int width;
    public final int height;
    public final static int gridSize = 10;

    private final int reserved = 10*gridSize;
    private final List<String> configStrings;

    public SimulatedMap(int width, int height, List<String> configStrings){
        this.width = width;
        this.height = height;
        this.setBackground(Color.GRAY);

        this.configStrings = configStrings;

    }

    public Point getRealLocation(Point position){
        return new Point( this.width/2 + position.x*gridSize, this.height - reserved - position.y*gridSize);
    }

    public Point getRealLocation(int x, int y){
        return new Point( this.width/2 + x*gridSize, this.height - reserved - y*gridSize);
    }

    public void drawEdges(Graphics graphics){

        graphics.setColor(Color.black);

        //draw the edge
        String[] start = configStrings.get(3).split(",");
        for(int i=4; i<configStrings.size(); i++){
            String[] end = configStrings.get(i).split(",");

            Point startPoint = getRealLocation( Integer.parseInt(start[0]), Integer.parseInt(start[1]) );
            Point endPoint = getRealLocation( Integer.parseInt(end[0]), Integer.parseInt(end[1]) );
            graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

            start = end;

        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {

        //paint grid
        super.paintComponent(graphics);

        graphics.setColor(Color.LIGHT_GRAY);
        for(int i = 0; i<width; i+= gridSize) {
            graphics.drawLine(i, 0, i, height);
        }
        for(int i = 0; i<height; i+= gridSize){
            graphics.drawLine(0, i, width, i);
        }

        drawEdges(graphics);
    };

}
