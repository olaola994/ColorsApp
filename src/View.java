import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;

public class View {
    private VBox rootNode;
    private Slider sliderR, sliderG, sliderB;
    private Label labelR;
    private Label labelG;
    private Label labelB;
    private static final int INIT_VALUE = 50;

    public VBox getRootNode(){
        return rootNode;
    }

    public Slider getSliderR() {
        return sliderR;
    }

    public Slider getSliderG() {
        return sliderG;
    }

    public Slider getSliderB() {
        return sliderB;
    }

    public Label getLabelR() {
        return labelR;
    }
    public Label getLabelG() {
        return labelG;
    }
    public Label getLabelB() {
        return labelB;
    }
    public View(){
        Pane upperComponent = new Pane();
        sliderR = new Slider(0,255, INIT_VALUE);
        sliderR.setShowTickLabels(true);
        sliderR.setMajorTickUnit(51);
        sliderR.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        sliderG = new Slider(0,255, INIT_VALUE);
        sliderG.setShowTickLabels(true);
        sliderG.setMajorTickUnit(51);
        sliderG.setOrientation(Orientation.VERTICAL);

        sliderB = new Slider(0,255, INIT_VALUE);
        sliderB.setShowTickLabels(true);
        sliderB.setMajorTickUnit(51);
        sliderB.setOrientation(Orientation.VERTICAL);

        Circle circle = new Circle();
        circle.setRadius(70);



        HBox hBox = new HBox();
        GridPane mainPane = new GridPane();
        mainPane.addColumn(0, sliderG);
        mainPane.getColumnConstraints().add(new ColumnConstraints(100));
        mainPane.addColumn(1, circle);
        mainPane.getColumnConstraints().add(new ColumnConstraints(250));
        mainPane.addColumn(2, sliderB);
        mainPane.getColumnConstraints().add(new ColumnConstraints(100));
        hBox.getChildren().add(mainPane);
        hBox.setAlignment(Pos.CENTER);

        mainPane.setHalignment(sliderG, HPos.CENTER);
        mainPane.setValignment(sliderG, VPos.CENTER);
        mainPane.setHalignment(circle, HPos.CENTER);
        mainPane.setValignment(circle, VPos.CENTER);
        mainPane.setHalignment(sliderB, HPos.CENTER);
        mainPane.setValignment(sliderB, VPos.CENTER);

        HBox labelContainer = new HBox();
        labelContainer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        labelContainer.setAlignment(Pos.CENTER);

        labelR = new Label();
        labelG = new Label();
        labelB = new Label();

        Insets padding = new Insets(0, 10, 0, 10);


        labelR.setPadding(padding);
        labelG.setPadding(padding);
        labelB.setPadding(padding);

        labelContainer.getChildren().add(labelR);
        labelContainer.getChildren().add(labelG);
        labelContainer.getChildren().add(labelB);



        ObjectBinding<Color> colorBinding = Bindings.createObjectBinding(() -> Color.rgb((int) sliderR.getValue(), (int) sliderG.getValue(), (int) sliderB.getValue()),
                sliderR.valueProperty(), sliderG.valueProperty(), sliderB.valueProperty());

        StringBinding labelRBinding = Bindings.createStringBinding(() ->
                        "R: " + (int) sliderR.getValue(),
                sliderR.valueProperty());

        StringBinding labelGBinding = Bindings.createStringBinding(() ->
                        "G: " + (int) sliderG.getValue(),
                sliderG.valueProperty());

        StringBinding labelBBinding = Bindings.createStringBinding(() ->
                        "B: " + (int) sliderB.getValue(),
                sliderB.valueProperty());

        labelR.textProperty().bind(labelRBinding);
        labelG.textProperty().bind(labelGBinding);
        labelB.textProperty().bind(labelBBinding);

        circle.fillProperty().bind(colorBinding);

        rootNode = new VBox();
        rootNode.getChildren().add(sliderR);
        rootNode.getChildren().add(hBox);
        rootNode.getChildren().add(labelContainer);
    }
}

