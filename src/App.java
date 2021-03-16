import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ClockContainer container = null;

    App() {
        setLayout(null);

        container = new ClockContainer();
        container.setLocation(0, 0);
        add(container);

        setBounds(20, 20, 300, 328);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void startClock(){
        List<Point> minutePoints = container.grabPoints(130);
        List<Point> secondPoints = container.grabPoints(140);
        List<Point> hourPoints = container.grabPoints(120);
        while(true){
            for(int i = 0; i < 360; i += 6){
                Point hourPoint = hourPoints.get(i);
                container.updateHour(150 + hourPoint.getX(), 150 - hourPoint.getY());
                for(int j = 0; j < 360; j += 6){
                    Point minutePoint = minutePoints.get(j);
                    container.updateMinute(150 + minutePoint.getX(), 150 - minutePoint.getY());
                    for(int k = 0; k < 360; k += 6){
                        Point secondPoint = secondPoints.get(k);
                        container.updateSecond(150 + secondPoint.getX(), 150 - secondPoint.getY());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }            
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setTitle("Analog Clock");

            new Thread(new Runnable() {
                public void run() {
                    app.startClock();
                }
            }).start();
        });
    }
}