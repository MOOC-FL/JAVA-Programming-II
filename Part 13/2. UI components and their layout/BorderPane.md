#### BorderPane
- The BorderPane class lets you lay out components in five different primary positions: top, right, bottom, left and center.
- Traditional applications such as the web browser you are using take advantage of this layout.
- There's a menu and address bar at the top, and in the middle is the content of the page.
```java
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFxSovellus extends Application {

    @Override
    public void start(Stage window) {
        BorderPane layout = new BorderPane();
        layout.setTop(new Label("top"));
        layout.setRight(new Label("right"));
        layout.setBottom(new Label("bottom"));
        layout.setLeft(new Label("left"));
        layout.setCenter(new Label("center"));

        Scene view = new Scene(layout);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(JavaFxSovellus.class);
    }
}
```
`BorderPane` divides the window into five named regions — **top**, **bottom**, **left**, **right**, and **center** — and you place a component into whichever region you want using dedicated setter methods.

#### Basic usage

```java
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {

    @Override
    public void start(Stage window) {
        Label textComponent = new Label("Text element");
        Button buttonComponent = new Button("This is a button");

        BorderPane componentGroup = new BorderPane();
        componentGroup.setTop(textComponent);
        componentGroup.setCenter(buttonComponent);

        Scene view = new Scene(componentGroup);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(JavaFxApplication.class);
    }
}
```

This puts the Label at the top of the window and the Button in the center — satisfying a "Label above the Button" requirement, just like the `VBox` version did, but using named regions instead of add-order.

#### The five regions

| Method | Region | Notes |
|---|---|---|
| `setTop(node)` | Top | Usually stretches full width |
| `setBottom(node)` | Bottom | Usually stretches full width |
| `setLeft(node)` | Left | Usually stretches full height |
| `setRight(node)` | Right | Usually stretches full height |
| `setCenter(node)` | Center | Takes up remaining space |

Each region holds **exactly one node** directly. If you want multiple components in one region (e.g., several buttons at the bottom), wrap them in an `HBox` or `VBox` first, then pass that container into `setBottom(...)`:

```java
HBox bottomButtons = new HBox(10, new Button("OK"), new Button("Cancel"));
componentGroup.setBottom(bottomButtons);
```

#### When to use it vs. VBox/HBox/FlowPane

- **`BorderPane`** — best when you have a clear structural layout: a menu bar on top, a status bar on bottom, a sidebar, and a main content area.
- **`VBox` / `HBox`** — best for simple linear stacking of components (like the Label + Button case).
- **`FlowPane`** — best when components should wrap automatically based on available space.

#### Label to the left of the Button, using BorderPane

```java
componentGroup.setLeft(textComponent);
componentGroup.setCenter(buttonComponent);
```

That satisfies the "Label on the left side of the Button" requirement instead.

