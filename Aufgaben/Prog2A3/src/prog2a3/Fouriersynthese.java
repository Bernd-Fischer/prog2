package prog2a3;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Fouriersynthese extends Application {

    private Group root;
	
    int cursorX = 0;
    int cursorY = 200;
    int i = 0;

    double f = 1.99; // 1.99 default
    double y = 0;
    double amplitude = 50;
    double frequence = 4;
	
    public void wave() {
            if(i > 600)
                return;
            y = amplitude * Math.sin(frequence * Math.PI * i /600);
            drawLine(i,(int)y);
            i++;
            wave();
	}

    void drawLine(int x, int y) {
            int endX =  x;
            int endY =  200 - y;
            Line line = new Line(cursorX, cursorY, endX , endY);
            cursorX = endX;
            cursorY = endY;
            line.setStroke(Color.RED);
            root.getChildren().add(line);	
	}
    
    @Override
    public void start(Stage primaryStage) throws Exception {
            root = new Group();
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Scribble");
            
            Rectangle panel = new Rectangle(600, 400, Color.WHITESMOKE);
            root.getChildren().add(panel);
            primaryStage.show();		
	
            wave();
// Aufgabe 3.2 -> Sie nährt sich einer geraden Linie            
// Aufgabe 3.3            
/*            double k = 0;
            k = y;
            System.out.println(k);
            amplitude = 50./3.;
            frequence = 4*3;
            wave();
            System.out.println(k);
            k += y;
            amplitude = 50./5.;
            frequence = 5*4;
            wave();
            System.out.println(k);
            k += y;
            amplitude = 50./7.;
            frequence = 7*4;
            wave();
            k += y;
            System.out.println(k);
*/
            
// Aufgabe 3.4 -> Sie nährt sich einer geraden Linie 
/*            double k = 0;
            k = y;
            System.out.println(k);
            amplitude = 50./9.;
            frequence = -3*4;
            wave();
            System.out.println(k);
            k += y;
            amplitude = 50./25.;
            frequence = 5*4;
            wave();
            System.out.println(k);
            k += y;
            amplitude = 50./9.;
            frequence = -7*4;
            wave();
            k += y;
            System.out.println(k); */
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}


/* AUFGABE 4

    1: zahl % 2 = 0 -> Gerade
       zahl % 2 = 1 -> Ungerade

    2: if ( zahl % 2 = 1) 
        zahl * (-1)

    3: 
*/