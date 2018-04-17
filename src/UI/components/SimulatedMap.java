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
    public final int gridSize = 10;

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


    public void drawStartPoint(Graphics graphics){

        String[] config = configStrings.get(0).split(",");
        Point startPoint= getRealLocation(Integer.parseInt(config[0]), Integer.parseInt(config[1]));


        graphics.setColor(new Color(247,247,209, 128));

        graphics.fillOval(startPoint.x-5, startPoint.y-5, 10, 10);
    }

    public void drawEdges(Graphics originalGraphics){

        Graphics2D graphics = (Graphics2D)originalGraphics;
        graphics.setStroke(new BasicStroke(3.0f));
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

    public void drawFinishZone(Graphics graphics){
        String[] ULFinish = configStrings.get(1).split(",");
        String[] LRFinish = configStrings.get(2).split(",");
        int width = (Integer.parseInt(LRFinish[0])-Integer.parseInt(ULFinish[0])) * gridSize;
        int height = (Integer.parseInt(ULFinish[1])-Integer.parseInt(LRFinish[1])) * gridSize;
        Point locationFinish = getRealLocation(Integer.parseInt(ULFinish[0]),Integer.parseInt(ULFinish[1]));


        graphics.setColor(new Color(247,247,209, 128));
        graphics.fillRect(locationFinish.x, locationFinish.y, width, height);

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
        drawStartPoint(graphics);
        drawFinishZone(graphics);
    };

}
