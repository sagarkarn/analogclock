import java.awt.*;
import java.awt.Graphics;
import java.util.*;
import java.util.List;

import javax.swing.JPanel;

public class ClockContainer extends JPanel {
    private static final long serialVersionUID = 1L;

    int secondX = 0, secondY = 0;

    private int minuteX = 0;

    private int minuteY = 0;

    private int hourX = 0;

    private int hourY = 0;

    public ClockContainer() {
        setBackground(Color.GRAY);

        setSize(300, 300);
        setPreferredSize(new Dimension(300, 300));

    }

    public List<Point> grabPoints(int handLen) {
        if(handLen == 0){
            handLen = 140;
        }
        List<Point> list = new ArrayList<>();

        for (int i = 0; i < 360; i++) {

            double radians = Math.toRadians(i);
            double sinValue = Math.sin(radians);
            double cosValue = Math.cos(radians);

            final int X = (int) (sinValue * handLen);
            final int Y = (int) (cosValue * handLen);

            list.add(new Point(X, Y));
        }

        return list;
    }

    public void updateSecond(int secondX, int secondY) {
        this.secondX = secondX;
        this.secondY = secondY;

        repaint();
        updateUI();
    }

    public void updateMinute(int minuteX, int minuteY){
        this.minuteX = minuteX;
        this.minuteY = minuteY;
    }

    public void updateHour(int hourX, int hourY){
        this.hourX = hourX;
        this.hourY = hourY;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDial(g);
        drawSecondHand(g);
        drawMinuteHand(g);
        drawLabel(g);
        drawHourHand(g);
    }

    private void drawLabel(Graphics g) {
        List<Point> p = grabPoints(140);
        List<Point> points = new ArrayList<>();

        for(int i = p.size() - 1; i >= 0; i--){
            points.add(p.get(i));
        }


        for(int j = 0; j < 360; j++){
            if((j+1) % 30 == 0){
                    int hour = (j+1) / 30;
                    if( hour == 0){
                        hour = 12;
                    }
                    g.setColor(Color.ORANGE);
                    g.drawString("" + hour, 145 - points.get(j).getX(), 150 - points.get(j).getY());
            }

            else if((j+1) % 6 == 0){
                g.setColor(Color.BLACK);
                g.fillOval(148 - points.get(j).getX(), 148 - points.get(j).getY(), 4, 4);
            }
        }

    }

    public void drawDial(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawOval(0, 0, 300, 300);
        g.fillOval(145, 145, 10, 10);
    }

    public void drawSecondHand(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawLine(150, 150, secondX, secondY);
    }

    public void drawMinuteHand(Graphics g){

        g.setColor(Color.RED);
        g.drawLine(150, 150, minuteX, minuteY);
    }

    public void drawHourHand(Graphics g){
        g.setColor(Color.ORANGE);
        g.drawLine(150, 150, hourX, hourY);
    }
}