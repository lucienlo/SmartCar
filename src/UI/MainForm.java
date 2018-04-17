package UI;

import UI.components.Car;
import UI.components.Sensor;
import UI.components.SimulatedMap;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lucienlo on 2018/4/16.
 */
public class MainForm {

    private SimulatedMap map;
    private int height = 800;
    private int width = 800;

    public static void main(String[] args){

        MainForm mainform = new MainForm();
        mainform.map = new SimulatedMap(mainform.width, mainform.height, mainform.readInput("./src/mapConifg"));

        JFrame frame = new JFrame("Smart Car Simulator");
        frame.setSize(mainform.width, mainform.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainform.map);
        frame.setVisible(true);
        frame.setResizable(false);

        Car car = new Car(mainform.map, mainform.readInput("./src/mapConifg"));
        mainform.map.add(car);
        mainform.map.repaint();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        car.move(mainform.map.getRealLocation(0,1));

    }

    public List<String> readInput(String path){
        try {
            Scanner sc = new Scanner(new File(path));
            List<String> data = new LinkedList<String>();
            while(sc.hasNext()){
                data.add(sc.nextLine());
            }

            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

}
