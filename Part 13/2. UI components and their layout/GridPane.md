#### GridPane
- The `GridPane` class can be used to lay the UI components in a grid.
- . In the example below, we create a 3x3 row in which each cell contains a button.
```java
@Override
public void start(Stage window) {
    GridPane layout = new GridPane();

    for (int x = 1; x <= 3; x++) {
        for (int y = 1; y <= 3; y++) {
            layout.add(new Button("" + x + ", " + y), x, y);
        }
    }

    Scene view = new Scene(layout);

    window.setScene(view);
    window.show();
}
```
`GridPane` arranges components into a table-like grid of rows and columns, with each child placed at a specific `(column, row)` coordinate.

#### Basic usage

```java
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {

    @Override
    public void start(Stage window) {
        GridPane componentGroup = new GridPane();
        componentGroup.setHgap(10);
        componentGroup.setVgap(10);
        componentGroup.setPadding(new Insets(10, 10, 10, 10));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Button submitButton = new Button("Submit");

        // add(node, columnIndex, rowIndex)
        componentGroup.add(nameLabel, 0, 0);
        componentGroup.add(nameField, 1, 0);
        componentGroup.add(emailLabel, 0, 1);
        componentGroup.add(emailField, 1, 1);
        componentGroup.add(submitButton, 1, 2);

        Scene view = new Scene(componentGroup);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(JavaFxApplication.class);
    }
}
```

This produces a two-column form: labels in column 0, input fields in column 1, each pair on its own row — a common pattern for forms.

#### Key methods

| Method | Effect |
|---|---|
| `add(node, col, row)` | Places a component at a specific grid cell |
| `add(node, col, row, colSpan, rowSpan)` | Places a component spanning multiple columns/rows |
| `setHgap(double)` | Horizontal spacing between columns |
| `setVgap(double)` | Vertical spacing between rows |
| `setPadding(new Insets(...))` | Padding around the whole grid |
| `setAlignment(Pos.CENTER)` | Aligns the whole grid within its parent |
| `GridPane.setHalignment(node, HPos.RIGHT)` | Aligns a specific child horizontally within its cell |
| `setGridLinesVisible(true)` | Debug aid — draws grid lines so you can see cell boundaries |

#### Column/row spanning example

```java
Label title = new Label("Registration Form");
componentGroup.add(title, 0, 0, 2, 1);  // spans 2 columns, 1 row
```

#### GridPane vs. the others

| Container | Best for |
|---|---|
| `HBox` / `VBox` | Simple single-direction stacking |
| `BorderPane` | Broad structural regions (top/bottom/left/right/center) |
| `FlowPane` | Components that should wrap automatically |
| `GridPane` | Tabular/aligned layouts — forms, calculators, settings panels |

`GridPane` is the natural choice whenever you want things to visually line up in both rows and columns — like labeled input fields, where you want all the `TextField`s to start at the same horizontal position.

