#### Graphical user interfaces
- [ ] We'll now take a look at creating graphical user interfaces (GUIs).
- [ ] When creating graphical user interfaces, we mostly make use of user-interface libraries that provide us with ready-made components, such as `buttons` and `text areas`.
- [ ]  These user-interface libraries take care of the drawing the components for us, meaning that we don't have to draw every single component in our program, only add them to it.
> Whereas text interfaces have the functionality coupled to a particular form of input, in GUIs it's added to the user-interface components.
- A programmer can, for instance, add a method to a button that lives on the interface, which handles an event associated with that button.
> We'll be using Java's [JavaFx] (https://en.wikipedia.org/wiki/JavaFX) user-interface library to create our graphical user interfaces. The applications we develop are desktop applications.
- We can create a simple window using JavaFX with the following program.
```java
package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {

    @Override
    public void start(Stage window) {
        window.setTitle("Hello World!");
        window.show();
    }

    public static void main(String[] args) {
        launch(JavaFxApplication.class);
    }
}
```
- When the launch method is called, the method of the Application class creates a new object from the given class (here JavaFxApplication) and calls its init method. The init method is defined in the Application class and is used, for instance, to initialize objects of a program. After calling the init method, the program calls the start method, which gets a [Stage] object as its parameter, which describes the window.
-  In the implementation of the start method above, the setTitle method sets the title of the Stage-type window object obtained as a parameter.
-  The method show is then called, which leads to the window being displayed. The program then stays in a state where it continuously listens to events on user interface, such as closing the window that causes the application to shut down.
