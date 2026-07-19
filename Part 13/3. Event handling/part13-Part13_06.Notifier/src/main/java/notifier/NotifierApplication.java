package notifier;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import java.util.EventListener;

public class NotifierApplication extends Application {

    @Override
    public void start(Stage window) {
        Label text = new Label();
        TextField textField = new TextField();
        Button button = new Button("Update");

        button.setOnAction(event -> text.setText(textField.getText()));

        VBox box = new VBox();
        box.getChildren().add(textField);
        box.getChildren().add(button);
        box.getChildren().add(text);

        Scene scene = new Scene(box);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(NotifierApplication.class);
    }

}
