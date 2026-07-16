#### UI Component Layout
- Each UI component has its place on the interface. The location of a component is determined by the class used to layout the components.
- In previous examples, we used a class called `FlowPane` to set up our front-end components.
-  With `FlowPane`, components that you add to the `interface` are placed **side-by-side**.
-  . If the size of Window is reduced so that the components no longer fit next to eahch other, the components will be automatically aligned.
Here's an overview of UI components and layout in JavaFX, building on what we just covered with `Label`, `Button`, and `FlowPane`.

#### UI Components

JavaFX provides a large set of ready-made controls in `javafx.scene.control`, so you don't build things like buttons from scratch. Common ones:

| Component | Purpose |
|---|---|
| `Label` | Displays static text |
| `Button` | Clickable action trigger |
| `TextField` | Single-line text input |
| `TextArea` | Multi-line text input |
| `CheckBox` | Boolean toggle |
| `RadioButton` | Single choice among options |
| `ComboBox` | Dropdown selection |
| `ListView` | Scrollable list of items |
| `TableView` | Tabular data display |
| `TreeView` | Hierarchical data display |
| `TreeTableView` | Hierarchical data in columns |

All follow the same basic pattern: create the object, optionally configure it (`setText`, `setOnAction`, etc.), then add it to a layout container.

#### Layout Containers (Panes)

The container decides *where* components end up on screen. A few key ones:

| Layout class | Behavior |
|---|---|
| `FlowPane` | Places components in a row, wrapping to a new line when it runs out of space. Can be set to vertical orientation. |
| `HBox` | Places components strictly in a horizontal row |
| `VBox` | Places components strictly in a vertical column |
| `BorderPane` | Divides the window into top, bottom, left, right, and center regions |
| `GridPane` | Places components into rows and columns, like a table |
| `StackPane` | Stacks components on top of one another |

#### Example: Label above a Button, using VBox

```java
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {

    @Override
    public void start(Stage window) {
        Label textComponent = new Label("Text element");
        Button buttonComponent = new Button("This is a button");

        VBox componentGroup = new VBox();
        componentGroup.getChildren().add(textComponent);
        componentGroup.getChildren().add(buttonComponent);

        Scene view = new Scene(componentGroup);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(JavaFxApplication.class);
    }
}
```

`VBox` guarantees vertical stacking regardless of window size — unlike `FlowPane`, which only wraps when it runs out of horizontal space. That makes `VBox`/`HBox` a more predictable choice when you specifically want "on top" or "side by side" layouts.

#### A couple of useful configuration options

```java
VBox componentGroup = new VBox(10);   // 10px spacing between children
componentGroup.setPadding(new Insets(10, 10, 10, 10)); // padding around edges
```

(`Insets` comes from `javafx.geometry.Insets`.)
