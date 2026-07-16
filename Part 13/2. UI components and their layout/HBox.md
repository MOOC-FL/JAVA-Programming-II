#### HBox
- `HBox` -class enables UI components to be laid out in a single horizontal row.
```java
@Override
public void start(Stage window) {
    HBox layout = new HBox();

    layout.getChildren().add(new Label("first"));
    layout.getChildren().add(new Label("second"));
    layout.getChildren().add(new Label("third"));

    Scene view = new Scene(layout);

    window.setScene(view);
    window.show();
}
```
> HBox by default completely glues the UI components to each other. We can use the `setSpacing` method to add space in between the components.
```java
@Override
public void start(Stage window) {
    HBox layout = new HBox();
    layout.setSpacing(10);

    layout.getChildren().add(new Label("first"));
    layout.getChildren().add(new Label("second"));
    layout.getChildren().add(new Label("third"));

    Scene view = new Scene(layout);

    window.setScene(view);
    window.show();
}
```
> The class `VBox` works in a similar way, but instead sets the components in a vertical column.

`HBox` and `VBox` are both simple, single-direction layout containers — the difference is just the axis they stack children along.

| | `HBox` | `VBox` |
|---|---|---|
| Direction | Horizontal (row) | Vertical (column) |
| Children arranged | Left to right | Top to bottom |
| Package | `javafx.scene.layout.HBox` | `javafx.scene.layout.VBox` |
| Typical use | Toolbars, button rows, side-by-side fields | Forms, stacked labels/buttons, vertical menus |

Both share the same API shape:

```java
HBox row = new HBox(10);   // 10px spacing between children
VBox column = new VBox(10);

row.getChildren().addAll(new Label("Name:"), new TextField());
column.getChildren().addAll(new Label("Text element"), new Button("This is a button"));
```

#### Key configuration options (identical on both)

| Method | Effect |
|---|---|
| `new HBox(spacing)` / `new VBox(spacing)` | Sets gap (px) between children |
| `setSpacing(double)` | Same, set after construction |
| `setPadding(new Insets(top, right, bottom, left))` | Padding around the edges |
| `setAlignment(Pos.CENTER)` | Aligns children within the box (e.g. `Pos.CENTER`, `Pos.TOP_LEFT`) |
| `HBox.setHgrow(node, Priority.ALWAYS)` | Lets a specific child grow to fill extra horizontal space (HBox only) |
| `VBox.setVgrow(node, Priority.ALWAYS)` | Same idea, vertical (VBox only) |

#### Nesting them together

Because both are just `Pane` subclasses, you can nest an `HBox` inside a `VBox` (or vice versa) to build more complex layouts — e.g. a form where each row is an `HBox` (label + field side by side), and the rows are stacked in a `VBox`:

```java
HBox row1 = new HBox(10, new Label("Name:"), new TextField());
HBox row2 = new HBox(10, new Label("Email:"), new TextField());

VBox form = new VBox(15, row1, row2);
```

#### When to pick which

- **`VBox`** — Label-above-Button, stacked form fields, vertical menus/lists.
- **`HBox`** — Label-left-of-Button, a row of buttons (OK/Cancel), toolbar icons.
- Combine both when you need a grid-like structure without using `GridPane`.
