package UI.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lucienlo on 2018/4/17.
 */
public class Sensor extends JComponent implements CarComponent{

    public final Car car;
    public final int maxDistance;
    public int angle;
    private Point upperLeft;
    public int x;
    public int y;

    public Sensor(Car car, int angle){

        this.car = car;
        this.angle = angle;
        this.maxDistance = 50 * car.map.gridSize;

        renewLocation();
        renewLine();

    }


    public void renewLocation(){

        upperLeft = new Point(
                x < 0 ? this.car.center.x + this.x : this.car.center.x, y < 0 ? this.car.center.y: this.car.center.y - this.y
        );
        setLocation(upperLeft);
    }

    private void renewLine(){
        this.x = (int)(maxDistance *Math.cos(Math.toRadians(this.angle)));
        this.y = (int)(maxDistance *Math.sin(Math.toRadians(this.angle)));
        setSize(x==0 ? car.map.gridSize : Math.abs(x), y==0 ? car.map.gridSize : Math.abs(y));
        renewLocation();
    }

    public void rotate(int angle){
        this.angle = this.angle + angle;
        renewLine();
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
        graphics.drawLine(x * y < 0 ? 0 : Math.abs(x) , 0 , x * y < 0 ? Math.abs(x) : 0, Math.abs(y));

    }
}
