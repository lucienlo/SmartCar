package UI.components;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by lucienlo on 2018/4/17.
 */
public class Car extends JComponent{

    private enum position{ left, front, right, size};
    public Sensor[] sensors = new Sensor[position.size.ordinal()];
    public final int radiusScale = 3;
    public final int radius;
    public Point center;
    public SimulatedMap map;
    public int angle;

    private List<CarComponent> components = new LinkedList<CarComponent>();

    public Car(SimulatedMap map, List<String> configStrings){

        String[] config = configStrings.get(0).split(",");
        this.center = map.getRealLocation(Integer.parseInt(config[0]), Integer.parseInt(config[1]));
        this.angle = Integer.parseInt(config[2]);

        this.map = map;
        this.radius = this.radiusScale * map.gridSize;

        setLocation(new Point(this.center.x - radius, this.center.y - radius));
        setSize(radius*2 + map.gridSize, radius*2 + map.gridSize);

        sensors[position.left.ordinal()] = new Sensor(this, this.angle - 45);
        sensors[position.front.ordinal()] = new Sensor(this, this.angle);
        sensors[position.right.ordinal()] = new Sensor(this, this.angle + 45);
        for(Sensor sensor : sensors) {
            components.add(sensor);
            map.add(sensor);
        }
    }

    public void move(Point location){
        //move car-self
        this.center.x = this.center.x + location.x * map.gridSize;
        this.center.y = this.center.y - location.y * map.gridSize;
        setLocation(new Point(this.center.x - radius, this.center.y - radius));

        //move installed components
        for(CarComponent carComponent: components){
            carComponent.renewLocation();
        }


    }

    public void rotate(int angle){
        for(CarComponent carComponent: components){
            carComponent.rotate(angle);
        }
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;

        graphics2D.setStroke(new BasicStroke(3.0f));
        graphics2D.setColor(Color.white);
        graphics2D.drawOval(0, 0, radius*2, radius*2);
        graphics2D.fillPolygon(
                new int[]{radius, radius - this.map.gridSize/2, radius + this.map.gridSize/2},
                new int[]{radius - this.map.gridSize/2, radius + this.map.gridSize/2, radius + this.map.gridSize/2},
                3);
    }
}
