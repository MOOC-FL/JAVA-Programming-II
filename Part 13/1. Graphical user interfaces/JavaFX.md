
## My First Application - Solution

| **Component** | **Code** | **Purpose** |
|---------------|----------|-------------|
| **Package** | `package application;` | Defines the package for the application class |
| **Imports** | `import javafx.application.Application;`<br>`import javafx.stage.Stage;` | Imports JavaFX core classes for applications and windows |
| **Class Declaration** | `public class MyFirstApplication extends Application` | Creates a JavaFX application class that extends the Application class |
| **start() Method** | `@Override`<br>`public void start(Stage window) {`<br>&nbsp;&nbsp;&nbsp;&nbsp;`window.setTitle("My first application");`<br>&nbsp;&nbsp;&nbsp;&nbsp;`window.show();`<br>`}` | Overrides the start method to configure and display the window |
| **setTitle()** | `window.setTitle("My first application");` | Sets the window title to "My first application" |
| **show()** | `window.show();` | Makes the window visible on screen |
| **main() Method** | `public static void main(String[] args) {`<br>&nbsp;&nbsp;&nbsp;&nbsp;`launch(MyFirstApplication.class);`<br>`}` | Entry point that launches the JavaFX application |

---

### How the Application Works

| **Step** | **What Happens** |
|----------|------------------|
| 1 | `main()` method is called when the program starts |
| 2 | `launch()` initializes the JavaFX framework |
| 3 | `Application` creates an instance of `MyFirstApplication` |
| 4 | `init()` method runs (inherited from Application) |
| 5 | `start(Stage window)` is called with a new Stage object |
| 6 | Window title is set to "My first application" |
| 7 | `show()` displays the empty window |
| 8 | Application enters event loop, waiting for user interaction |

---

### Key JavaFX Concepts Used

| **Term** | **Explanation** |
|----------|-----------------|
| **Application** | Base class for JavaFX programs; requires `start()` to be overridden |
| **Stage** | Represents the main window of the application |
| **launch()** | Static method that starts the JavaFX application lifecycle |
| **@Override** | Annotation indicating we're overriding the `start()` method from `Application` |

---

### Complete Code (Copy-Paste Ready)

```java
package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class MyFirstApplication extends Application {

    @Override
    public void start(Stage window) {
        window.setTitle("My first application");
        window.show();
    }

    public static void main(String[] args) {
        launch(MyFirstApplication.class);
    }
}
```

---

### Expected Output

| **Visual Element** | **Description** |
|-------------------|-----------------|
| **Window** | Empty white window |
| **Title Bar** | Displays "My first application" |
| **Close Button** | Window can be closed with the standard close button |
