#### UI components and their layout
- Programmers typically use ready-made libraries for implementing graphical user interfaces.
>  As an example, it would not make much sense to implement a button from scratch (which would require creating a class that draws a button and handles all of its functions) since pre-built button components can usually be found in existing libraries.
- Let's take a look now at some UI components.
- [ ] Text can be displayed using the **Label** class.
- [ ] The Label class provides a UI component for which text can be set, and offers methods for modifying the text it contains.
- [ ] The displayed text is either set in the constructor, or by using the `setText` method.
```java
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {

    @Override
    public void start(Stage window) {
        Label textComponent = new Label("Text element");

        FlowPane componentGroup = new FlowPane();
        componentGroup.getChildren().add(textComponent);

        Scene view = new Scene(componentGroup);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(JavaFxApplication.class);
    }
}
```
- [ ] Buttons can be added using the **Button** class.
- [ ]  Buttons are added the same way as labels.
```java
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {

    @Override
    public void start(Stage window) {
        Button buttonComponent = new Button("This is a button");

        FlowPane componentGroup = new FlowPane();
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
> You also have the ability to add multiple components at once. In the example below, both a button and a textComponent have been added.
```java
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {

    @Override
    public void start(Stage window) {
        Button buttonComponent = new Button("This is a button");
        Label textComponent = new Label("Text element");

        FlowPane componentGroup = new FlowPane();
        componentGroup.getChildren().add(buttonComponent);
        componentGroup.getChildren().add(textComponent);

        Scene view = new Scene(componentGroup);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(JavaFxSovellus.class);
    }
}
```
> You can find a list of the available UI components on https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/. The site also provides examples on how to use them.

- There is a considerable amount of different UI components. Using online materials, such as the one linked above, is a good way to learn about them. When staring out, components should be tried out in isolation by adding a single component at a time and inspecting how it works.
- As you become more familiar with the various components, using them becomes more straightforward. What's common to almost all of the components is the way that they're added to an interface - once you know how to add one, you can add almost any component to your interface.
- The biggest difference in adding components is where they're placed on the interface. You'll soon learn more about the lay these components out.




