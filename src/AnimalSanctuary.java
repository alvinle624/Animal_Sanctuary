import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
/**
 * class AnimalSanctuary.
 * @author Alvin Le
 * @version 1.0
 */
public class AnimalSanctuary extends Application {
    private CreatedAnimal animal;
    private static ChoiceBox<Animal> animalChoiceBox;
    private static TextField nameInput;
    private static TextField healthInput;
    private static Sanctuary[] sanList;
    private static BorderPane layout;
    /**
     * @param args array of Strings
     * Launches.
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * @param primaryStage Stage
     * Runs the program.
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Animal Sanctuary");

        sanList = new Sanctuary[6];
        sanList = setDefaultSanctuaryArea();

        Image animalImage = new Image("CatFarm.jpeg");
        ImageView animalImageView = new ImageView(animalImage);

        TitledPane tPane = new TitledPane();
        tPane.setText("Add an animal to the sanctuary");
        tPane.setContent(animalInputs());

        layout = new BorderPane();
        layout.setCenter(sanctuaryArea(sanList));
        layout.setBottom(tPane);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(animalImageView, layout);

        Scene scene = new Scene(stack, animalImage.getWidth(), animalImage.getHeight());
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    /**
     * @param textField TextField
     * @return boolean
     * Checks if the health entered was a number
     */
    private boolean isInt(TextField textField) {
        try {
            int health = Integer.parseInt(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            displayAlertBox("Error: Number Format", textField.getText() + " is not a number.");
            return false;
        }
    }
    /**
     * @return GridPane
     * Creates the area where you create an Animal to add to the Sanctuary.
     */
    private GridPane animalInputs() {
        GridPane gPane = new GridPane();

        gPane.setPadding(new Insets(10));
        gPane.setHgap(10);
        gPane.setVgap(25);

        gPane.add(new Label("Type animal name: "), 0, 0);
        gPane.add(new Label("Type animal health: "), 0, 1);
        gPane.add(new Label("Pick animal type: "), 0, 2);

        setNameInput(new TextField());
        gPane.add(getNameInput(), 1, 0);

        setHealthInput(new TextField());
        gPane.add(getHealthInput(), 1, 1);
        gPane.add(animalTypeCBox(), 1, 2);

        Button addAnimalButton =  new Button("Add Animal");
        gPane.add(addAnimalButton, 0, 3);

        addAnimalButton.setOnAction(e -> handleAddAnimal(e));

        return gPane;
    }
    /**
     * @param event Event
     * Creates an Animal, adds it to the list of Sanctuaries, and clears all the animal creation inputs.
     */
    private void handleAddAnimal(Event event) {
        if (isInt(getHealthInput())) {
            int h = Integer.parseInt(getHealthInput().getText());
            this.animal = new CreatedAnimal(nameInput.getText(), animalChoiceBox.getValue(), h);

            sanList = addAnimalSanctuary(animalInSanctuary(this.animal));
            layout.setCenter(sanctuaryArea(sanList));
        }

        getAnimalChoiceBox().setValue(null);
        getNameInput().clear();
        getHealthInput().clear();
    }
    /**
     * @param list array of Sanctuary objects
     * @return boolean
     * Checks if all sanctuaries are occupied.
     */
    private boolean isSanctuaryFull(Sanctuary[] list) {
        int count = 0;
        boolean full = false;

        for (int i = 0; i < list.length; i = i + 1) {
            if (sanList[i] != null && !sanList[i].getIsEmpty()) {
                count = count + 1;
            }
        }

        if (count == list.length) {
            displayAlertBox("Error: Full Sanctuary", "There is no more room!");
            full = true;
        }
        return full;
    }
    /**
     * @param addedAnimal Sanctuary object
     * @return array of Sanctuary objects
     * Adds the animal to the list of Sanctuaries.
     */
    private Sanctuary[] addAnimalSanctuary(Sanctuary addedAnimal) {
        boolean con = true;
        int i = 0;

        if (!isSanctuaryFull(sanList)) {
            while (con && i < sanList.length) {
                if (sanList[i] == null || sanList[i].getIsEmpty()) {
                    sanList[i] = addedAnimal;
                    con = false;
                }
                i = i + 1;
            }
        }
        return sanList;
    }
    /**
     * @return ChoiceBox parameter Animal
     * Creates a ChoiceBox and sets it options.
     */
    private ChoiceBox<Animal> animalTypeCBox() {
        setAnimalChoiceBox(new ChoiceBox<Animal>());
        animalChoiceBox.getItems().addAll(Animal.CAT, Animal.DOG,
                Animal.BIRD, Animal.HORSE, Animal.SQUIRREL, Animal.LION);

        return animalChoiceBox;
    }
    /**
     * @param ani CreatedAnimal object
     * @return Sanctuary object
     * Creates a Sanctuary occupied by an animal.
     */
    private Sanctuary animalInSanctuary(CreatedAnimal ani) {
        Label name = new Label("Name:  " + ani.getName());
        name.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label type = new Label("Animal Type:  " + ani.getAnimalType());
        type.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label health = new Label("Health:  " + ani.getHealth());
        health.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox aniSan = new VBox();
        aniSan.getChildren().addAll(name, type, health);
        aniSan.setSpacing(5);

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(100, 100, 100, 100));
        border.setRight(aniSan);

        StackPane sPane = new StackPane();
        ani.getAnimalImage().setFitWidth(250);
        ani.getAnimalImage().setFitHeight(250);
        sPane.getChildren().addAll(ani.getAnimalImage(), border);

        Sanctuary aniSanctuary = new Sanctuary(sPane, false);
        sPane.setOnMouseClicked(e -> handleRemoveAnimal(e, aniSanctuary));

        return aniSanctuary;
    }
    /**
     * @param event Event
     * @param removedSan Sanctuary object
     * Removes an occupied sanctuary and sets it to empty.
     */
    private void handleRemoveAnimal(Event event, Sanctuary removedSan) {
        for (int i = 0; i < sanList.length; i = i + 1) {
            if (sanList[i].equals(removedSan)) {
                sanList[i] = emptySanctuary();
            }
        }
        layout.setCenter(sanctuaryArea(sanList));
    }
    /**
     * @return Sanctuary object
     * Creates an empty Sanctuary object.
     */
    private Sanctuary emptySanctuary() {
        StackPane san = new StackPane();
        san.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        san.setPadding(new Insets(100, 100, 100, 100));
        san.getChildren().add(new Label("Empty"));

        return (new Sanctuary(san, true));
    }
    /**
     * @return array of Sanctuary objects
     * Sets all 6 of the beginning sanctuaries to empty.
     */
    private Sanctuary[] setDefaultSanctuaryArea() {
        for (int i = 0; i < sanList.length; i = i + 1) {
            if (sanList[i] == null) {
                sanList[i] = emptySanctuary();
            }
        }
        return sanList;
    }
    /**
     * @param list array of Sanctuary objects
     * @return GridPane
     * Creates the sanctuary area.
     */
    private GridPane sanctuaryArea(Sanctuary[] list) {
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(25);
        grid.setHgap(20);

        GridPane.setConstraints(list[0].getArea(), 0, 0);
        GridPane.setConstraints(list[1].getArea(), 1, 0);
        GridPane.setConstraints(list[2].getArea(), 2, 0);
        GridPane.setConstraints(list[3].getArea(), 0, 1);
        GridPane.setConstraints(list[4].getArea(), 1, 1);
        GridPane.setConstraints(list[5].getArea(), 2, 1);

        grid.getChildren().addAll(list[0].getArea(), list[1].getArea(), list[2].getArea(),
                list[3].getArea(), list[4].getArea(), list[5].getArea());
        grid.setAlignment(Pos.CENTER);

        return grid;
    }
    /**
     * @param title String
     * @param message String
     * Displays an Alert box with a message.
     */
    private void displayAlertBox(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label mLabel = new Label(message);
        mLabel.setAlignment(Pos.CENTER);

        Button close = new Button("Close Window");
        close.setOnAction(e -> window.close());
        close.setAlignment(Pos.BOTTOM_CENTER);

        VBox display = new VBox();
        display.setAlignment(Pos.CENTER);
        display.setSpacing(20);
        display.getChildren().addAll(mLabel, close);

        Scene scene = new Scene(display, 250, 250);

        window.setScene(scene);
        window.showAndWait();
    }
    /**
     * @param input TextField
     */
    public void setHealthInput(TextField input) {
        healthInput = input;
    }
    /**
     * @param input TextField
     */
    public void setNameInput(TextField input) {
        nameInput = input;
    }

    /**
     * @return TextField
     */
    public TextField getHealthInput() {
        return healthInput;
    }
    /**
     * @return TextField
     */
    public TextField getNameInput() {
        return nameInput;
    }
    /**
     * @param cBox ChoiceBox parameter Animal
     */
    public void setAnimalChoiceBox(ChoiceBox<Animal> cBox) {
        animalChoiceBox = cBox;
    }
    /**
     * @return ChoiceBox parameter Animal
     */
    public ChoiceBox<Animal> getAnimalChoiceBox() {
        return animalChoiceBox;
    }
}
