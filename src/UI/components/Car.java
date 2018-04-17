package UI.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lucienlo on 2018/4/17.
 */
public class Car extends JComponent{

    public final int radius = 3 * SimulatedMap.gridSize;
    public Car(Point location){
        setLocation(new Point(location.x - radius, location.y - radius));
        setSize(radius*2 + SimulatedMap.gridSize, radius*2 + SimulatedMap.gridSize);
    }

    public void move(Point location){
        setLocation(new Point(location.x - radius, location.y - radius));
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        graphics.setColor(Color.red);
        graphics.fillRect(radius, radius ,10, 10);
        graphics.drawOval(0, 0, radius*2, radius*2);

    }
}
