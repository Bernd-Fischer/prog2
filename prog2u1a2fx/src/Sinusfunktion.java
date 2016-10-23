
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Sinusfunktion extends Application {

    private Group root;

    int cursorX = 0;
    int cursorY = 200;

    double z0;
    double z1 = 8;  // Amplitude
    double z2 = 0;
    int i = 0;

    public void sinus(double f) {
        if (i > 600) {
            return;
        }
        z0 = f * z1 - z2;
        z2 = z1;
        z1 = z0;
        System.out.println(i + " " + (int) z0);
        drawLine(i, (int) z0);
        i++;
        //f -= 0.01;
        sinus(f);
    }

    public static void main(String[] args) {
        launch(args);

    }

    void drawLine(int x, int y) {
        int endX = x;
        int endY = 200 - y;
        Line line = new Line(cursorX, cursorY, endX, endY);
        cursorX = endX;
        cursorY = endY;
        line.setStroke(Color.RED);
        root.getChildren().add(line);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        root = new Group();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scribble");
        Rectangle panel = new Rectangle(600, 400, Color.WHITESMOKE);
        Label label1 = new Label("f:");
        final TextField textField = new TextField();
        Label label2 = new Label("z1:");
        final TextField textField2 = new TextField();
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, label2, textField2);
        hb.setSpacing(20);
        hb.setLayoutY(365);
        hb.setLayoutX(110);
        root.getChildren().addAll(panel, hb);

        primaryStage.show();

        
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    z1 = Double.parseDouble(textField2.getText());
                    double e = Double.parseDouble(textField.getText());
                    sinus(e);
                }
            }
        });

        textField2.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    z1 = Double.parseDouble(textField2.getText());
                    double e = Double.parseDouble(textField.getText());
                    sinus(e);
                }
            }
        });
        

    }
}
