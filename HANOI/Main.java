import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 */
public class Main extends Application {

    /**
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        //Gamestart INI

        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(new Tower());
        towers.add(new Tower());
        towers.add(new Tower());

        int widht = 120;
        int left = 10;
        for (int x = 0; x < 6; x++) {
            towers.get(0).pushDisc(new Disc(widht, left));
            widht -= 20;
            left += 10;
        }

        //GUI
        primaryStage.setTitle("Hanoi!");

        //FlowPane Layout
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setVgap(3);
        flowPane.setHgap(3);
        flowPane.setMinWidth(1000);

        ArrayList<Canvas> canvases = new ArrayList<>();
        final ArrayList<GraphicsContext> gces = new ArrayList<>();

        for (int x = 0; x < 3; x++) {
            canvases.add(new Canvas(300, 300));
            flowPane.getChildren().add(canvases.get(x));
        }
        drowTower(gces, towers, canvases);


        //ComboBox FromBox
        ComboBox<String> fromBox = new ComboBox<String>();
        fromBox.getItems().addAll("1", "2", "3");
        fromBox.setEditable(true);
        fromBox.setValue("1");
        flowPane.getChildren().add(fromBox);

        //ComboBox toBox
        ComboBox<String> toBox = new ComboBox<String>();
        toBox.getItems().addAll("1", "2", "3");
        toBox.setEditable(true);
        toBox.setValue("2");
        flowPane.getChildren().add(toBox);

        //Button submit
        Button submitBtn = new Button("submit");
        flowPane.getChildren().add(submitBtn);
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (
                        fromBox.getValue().isEmpty() ||
                                toBox.getValue().isEmpty() ||
                                fromBox.getValue().equals(toBox.getValue()) ||
                                !fromBox.getValue().matches("[1-3]") ||
                                !toBox.getValue().matches("[1-3]")
                        ) {
                    return;
                }
                if (towers.get(Integer.parseInt(fromBox.getValue()) - 1).getDiscs().size() == 0) {
                    return;
                }
                if (!towers.get(Integer.parseInt(toBox.getValue()) - 1).getDiscs().isEmpty()) {
                    if (
                            towers.get(Integer.parseInt(fromBox.getValue()) - 1).getDiscs().get(towers.get(Integer.parseInt(fromBox.getValue()) - 1).getDiscs().size() - 1).getWight()
                                    >=
                                    towers.get(Integer.parseInt(toBox.getValue()) - 1).getDiscs().get(towers.get(Integer.parseInt(toBox.getValue()) - 1).getDiscs().size() - 1).getWight()
                            )
                    {
                        return;
                    }
                }

                //add disc frombox to tobox
                towers.get(Integer.parseInt(toBox.getValue()) - 1).pushDisc(
                        towers.get(Integer.parseInt(fromBox.getValue()) - 1).getDisc(
                                towers.get(Integer.parseInt(fromBox.getValue()) - 1).getDiscs().size() - 1
                        ));

                //delete disc from frombox
                towers.get(Integer.parseInt(fromBox.getValue()) - 1).getDiscs().remove
                        (towers.get(Integer.parseInt(fromBox.getValue()) - 1).getDiscs().size() - 1);

                //draw towers
                drowTower(gces, towers, canvases);
                if (towers.get(2).getDiscs().size() == 6) {
                    submitBtn.setDisable(true);
                    submitBtn.setText("YOU WON");
                }
            }
        });

        Scene scene = new Scene(flowPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param gces
     * @param towers
     */
    public void drowTower(ArrayList<GraphicsContext> gces, ArrayList<Tower> towers, ArrayList<Canvas> canvases) {

        for (int y = 0; y < 3; y++) {
            gces.add(canvases.get(y).getGraphicsContext2D());
            gces.get(y).clearRect(0, 0, canvases.get(y).getWidth(), canvases.get(y).getHeight());
            gces.get(y).fillRect(65, 30, 10, 100);

            int level = 120;
            for (int x = 0; x < towers.get(y).getDiscs().size(); x++) {
                gces.get(y).clearRect(
                        towers.get(y).getDisc(x).getLeft(),
                        level,
                        towers.get(y).getDisc(x).getWight(),
                        10);
                gces.get(y).clearRect(
                        towers.get(y).getDisc(x).getLeft(),
                        level,
                        towers.get(y).getDisc(x).getWight(),
                        10
                );
                gces.get(y).fillRect(
                        towers.get(y).getDisc(x).getLeft(),
                        level,
                        towers.get(y).getDisc(x).getWight(),
                        10
                );
                level -= 20;

            }
        }
    }
}

