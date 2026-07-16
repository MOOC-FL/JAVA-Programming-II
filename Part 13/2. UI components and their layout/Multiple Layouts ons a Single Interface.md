#### Multiple Layouts ons a Single Interface
- Layout components can be combined.
- A typical setup involves using the BorderPane layout as the base with other layouts inside it.
- In the example below, the top of the BorderPane contains a HBox used for horizontal layout and a VBox used for vertical layouts.
- A text box has been placed placed in the center.
```java
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFxSovellus extends Application {

    @Override
    public void start(Stage window) {
        BorderPane layout = new BorderPane();

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().add(new Button("First"));
        buttons.getChildren().add(new Button("Second"));
        buttons.getChildren().add(new Button("Third"));

        VBox texts = new VBox();
        texts.setSpacing(10);
        texts.getChildren().add(new Label("First"));
        texts.getChildren().add(new Label("Second"));
        texts.getChildren().add(new Label("Third"));

        layout.setTop(buttons);
        layout.setLeft(texts);

        layout.setCenter(new TextArea(""));

        Scene view = new Scene(layout);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(JavaFxSovellus.class);
    }
}
```
<img src="https://github.com/MOOC-FL/Media/blob/main/Java%20Programming%202/gui-useampi.webp" alt=""></img>


- **`BorderPane`** is the outer/base layout, giving you the five regions (top, bottom, left, right, center).
- **`HBox buttons`** is placed in `setTop(...)` — so "First", "Second", "Third" buttons sit in a horizontal row across the top.
- **`VBox texts`** is placed in `setLeft(...)` — so the three labels stack vertically down the left side.
- **`TextArea`** goes in `setCenter(...)` — filling the remaining space in the middle.

Each region of the `BorderPane` accepts exactly **one** node — that's why `HBox`/`VBox` are used as containers to group multiple components into a single node before handing them to `setTop`/`setLeft`/etc.

#### The general principle

Any layout node (`HBox`, `VBox`, `GridPane`, `FlowPane`, etc.) is itself a `Node`, so it can be added into another layout just like a `Button` or `Label` can. This is what makes nesting possible — you're not limited to one layout manager per window, you build a **tree of layouts**, each handling its own region.

```
BorderPane (outer)
 ├── top    → HBox (buttons row)
 ├── left   → VBox (labels column)
 └── center → TextArea
```

#### A common extension: nesting even further

You could go one level deeper — e.g., put a `GridPane` inside the `VBox`, or wrap the `HBox` buttons row inside another `VBox` alongside a title label:

```java
VBox topSection = new VBox(5);
topSection.getChildren().add(new Label("Toolbar"));
topSection.getChildren().add(buttons);  // the HBox from before

layout.setTop(topSection);
```


