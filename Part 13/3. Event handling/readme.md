#### Event handling
- The user interfaces we've previously implemented have not been able to react to events in the user interface.
- This inability to react is not due to the components of the interface themselves, but due to the fact that we've yet to added any functionality that handles component events.

-  [ ] Button presses are handled using a class that implements the EventHandler interface.
-  [ ] The type of the event in these cases is `ActionEvent`.
-  [ ] The `interface` implementation specifies what is done when a user presses a button
```java
Button button = new Button("This is a button");
button.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Pressed!");
    }
});
```
> If desired, the explicit implementation of the interface can be replaced by a `Lambda expr0ession`.
```java
Button button = new Button("This is a button");
button.setOnAction((event) -> {
    System.out.println("Pressed!");
});
```
- When the button is pressed, the program prints the text "Pressed!" to the console.
#### Event handlers
- **Event handlers** attached to user interface components, such as the EventHandler used previously, are always connected to specific user interface components. Whenever an action is performed on a user interface component, e.g., a button is pressed, each of the event handlers attached to that particular component is called and the functionality written for them is executed.
- We often want an event handler to change the state of some object. To get hold of an object, the event handler needs a reference to it. Let's think about the following user interface which has two text fields and a button.
```java
@Override
public void start(Stage window) {
    TextField leftText = new TextField();
    TextField rightText = new TextField();
    Button button = new Button("Copy");

    HBox componentGroup = new HBox();
    componentGroup.setSpacing(20);
    componentGroup.getChildren().addAll(leftText, button, rightText);

    Scene viewport = new Scene(componentGroup);

    window.setScene(viewport);
    window.show();
}
```
- There is a text field on both the left and right hand sides of the user interface. In addition to these, there's a button in the middle with the text "Copy".

<img src="https://github.com/MOOC-FL/Media/blob/main/Java%20Programming%202/gui-kopioija.webp"></img>

- We'd like to have an application where the content of the left text field is copied over to become the content of the right text field when the user presses the button. This can be done with the help of an object implementing the EventHandler interface.
```java
@Override
public void start(Stage window) {
    TextField leftText = new TextField();
    TextField rightText = new TextField();
    Button button = new Button("Copy");

    button.setOnAction((event) -> {
        rightText.setText(leftText.getText());
    });

    HBox componentGroup = new HBox();
    componentGroup.setSpacing(20);
    componentGroup.getChildren().addAll(leftText, button, rightText);

    Scene scene = new Scene(componentGroup);

    window.setScene(scene);
    window.show();
}
```
- Now pressing the button results in the content of the left text field being copied to the text field on the right.
- NB! The method implemented can use objects that were declared before the method definition, as long as the values of the objects being used are not reassigned using the equals operator, i.e., the references do not change.




