package prog2aufg2;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Sinusfunktion extends Application {

	private Group root;
	
	int cursorX = 0;
	int cursorY = 200;
        
        int i = 0;

        double b = 1;
        
	double z0;
	double z1 = 8;  // Amplitude - 8 default
	double z2 = 0;
    double zGlobal = z1;
        
        double f = 1.99; // 1.99 default
	
	
	public void sinus(double f) {
		if(i > 600)
			return;
		z0 = f * b * z1 - z2;
		z2 = z1;
		z1 = z0;
		drawLine(i, (int)z0) ;
		i++;
		sinus(f);
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
        
        private void resetParams(){
            cursorX = 0;
            cursorY = 200;
            z2 = 0;
            i = 0;
            z1=8;
        }
        
                private void newParams(){
            cursorX = 0;
            cursorY = 200;
            z2 = 0;
            //z1 = 8;
            i = 0;
        }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new Group();
		Scene scene = new Scene(root, 600, 450);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Scribble");
		
                Line line = new Line(0, 400, 600, 400);
                line.setStroke(Color.BLACK);
                line.setStrokeWidth(5);
                root.getChildren().add(line);
                
                Label z1_Lbl = new Label("z1:");
                TextField z1_Tf = new TextField();
                Label showZ1_Lbl = new Label("z1: " + z1);
                
                Label f_Lbl = new Label("f:");
                TextField f_Tf = new TextField();
                Label showF_Lbl = new Label("f: " + f);
                
                Label b_Lbl = new Label("b:");
                TextField b_Tf = new TextField();
                Label showB_Lbl = new Label("b: " + b);
                
                z1_Tf.setOnKeyReleased(event -> {
                    if(event.getCode() == KeyCode.ENTER) {
                        try{
                            z1 = Double.parseDouble(z1_Tf.getText());
                            zGlobal = z1;

                            showZ1_Lbl.setText("z1:" + Double.toString(z1));
                            z1_Tf.clear();
                            root.getChildren().remove(11, root.getChildren().size());
                            newParams();
                            sinus(f);
                        }catch(Exception e){
                            System.out.println("z1 input is not a double!");
                            z1_Tf.clear();
                        }
                    }
                });
                
                f_Tf.setOnKeyReleased(event -> {
                    if(event.getCode() == KeyCode.ENTER){
                        try{
                            f = Double.parseDouble(f_Tf.getText());
                            showF_Lbl.setText("f:" + Double.toString(f));
                            f_Tf.clear();
                            root.getChildren().remove(11, root.getChildren().size());

                            newParams();
                            z1 = zGlobal;
                            sinus(f);


                        }catch(Exception e){
                            System.out.println("f input is not a double!");
                            f_Tf.clear();
                        }
                    }
                });
                
                b_Tf.setOnKeyReleased(event -> {
                   if(event.getCode() == KeyCode.ENTER){
                       try{
                           b = Double.parseDouble(b_Tf.getText());
                           showB_Lbl.setText("b:" + Double.toString(b));
                           b_Tf.clear();
                           root.getChildren().remove(11, root.getChildren().size());
                           newParams();
                           sinus(f);
                       }catch(Exception e){
                           System.out.println("b input is not a double!");
                           b_Tf.clear();
                       }
                   } 
                });
// z1                
                z1_Lbl.setLayoutX(40);
                z1_Lbl.setLayoutY(415);
                z1_Lbl.setPrefSize(40,20);
                z1_Lbl.setFont(new Font("Arial", 20));
                
                z1_Tf.setLayoutX(70);
                z1_Tf.setLayoutY(415);
                z1_Tf.setPrefSize(45, 20);
                z1_Tf.setFocusTraversable(false);
                
                showZ1_Lbl.setLayoutX(320);
                showZ1_Lbl.setLayoutY(415);
                showZ1_Lbl.setPrefSize(80,20);
                showZ1_Lbl.setFont(new Font("Arial", 16));
// f                
                f_Lbl.setLayoutX(130);
                f_Lbl.setLayoutY(415);
                f_Lbl.setPrefSize(40,20);
                f_Lbl.setFont(new Font("Arial", 20));
                
                f_Tf.setLayoutX(150);
                f_Tf.setLayoutY(415);
                f_Tf.setPrefSize(45, 20);
                f_Tf.setFocusTraversable(false);
                
                showF_Lbl.setLayoutX(380);
                showF_Lbl.setLayoutY(415);
                showF_Lbl.setPrefSize(80,20);
                showF_Lbl.setFont(new Font("Arial", 16));
// b              
                b_Lbl.setLayoutX(210);
                b_Lbl.setLayoutY(415);
                b_Lbl.setPrefSize(40, 20);
                b_Lbl.setFont(new Font("Arial", 20));
                
                b_Tf.setLayoutX(230);
                b_Tf.setLayoutY(415);
                b_Tf.setPrefSize(45, 20);
                b_Tf.setFocusTraversable(false);
                
                showB_Lbl.setLayoutX(440);
                showB_Lbl.setLayoutY(415);
                showB_Lbl.setPrefSize(80,20);
                showB_Lbl.setFont(new Font("Arial", 16));
                
                root.getChildren().add(z1_Lbl);
                root.getChildren().add(f_Lbl);
                root.getChildren().add(b_Lbl);
                
                root.getChildren().add(z1_Tf);
                root.getChildren().add(f_Tf);
                root.getChildren().add(b_Tf);
                
                root.getChildren().add(showZ1_Lbl);
                root.getChildren().add(showF_Lbl);
                root.getChildren().add(showB_Lbl);
                
		Rectangle panel = new Rectangle(600, 400, Color.WHITESMOKE);
		root.getChildren().add(panel);
		primaryStage.show();		
	
		sinus(f);
	}
        
        public static void main(String[] args) {
		launch(args);
	}
}