package UI.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lucienlo on 2018/4/17.
 */
public class Sensor extends JComponent implements CarComponent{

    public final Car car;
    public final int maxDistance;
    public double theta;
    private Point upperLeft;
    public int x;
    public int y;

    public Sensor(Car car, int angle){

        this.car = car;
        this.maxDistance = 50 * car.map.gridSize;
        this.theta = Math.toRadians(angle);

        this.x = (int)(maxDistance *Math.cos(this.theta));
        this.y = (int)(maxDistance *Math.sin(this.theta));
        renewLocation();
        setSize(x==0 ? car.map.gridSize : Math.abs(x),y);

//        System.out.println(this.getLocationOnScreen());

    }


    public void renewLocation(){
        upperLeft =
            x < 0 ?
            new Point(this.car.center.x + this.x, this.car.center.y - this.y)
            :
            new Point(this.car.center.x, this.car.center.y - this.y);
        setLocation(upperLeft);
    }

    @Override
    public void paintComponent(Graphics originalGraphics){
        super.paintComponent(originalGraphics);

        Graphics2D graphics = (Graphics2D)originalGraphics;
        graphics.setStroke(new BasicStroke(
                1,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND,
                2.0f,
                new float[]{10, 10},
                2f
        ));
        graphics.setColor(Color.cyan);

        graphics.drawLine(this.x < 0 ? 0: Math.abs(x), 0 ,this.x < 0 ? Math.abs(x) : 0, y);
        System.out.println(upperLeft);

        System.out.println(Math.toDegrees(theta)+" called.");

    }
}
