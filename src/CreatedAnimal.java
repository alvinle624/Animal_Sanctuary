import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * class CreatedAnimal.
 * @author Alvin Le
 * @version 1.0
 */
public class CreatedAnimal {
    private String name;
    private Animal animalType;
    private int health;
    private Image image;
    private ImageView animalImage;
    /**
     * @param name String
     * @param animalType Animal enum
     * @param health int
     */
    public CreatedAnimal(String name, Animal animalType, int health) {
        if (name.equals("")) {
            this.name = "No Name Yet";
        } else {
            this.name = name;
        }

        this.animalType = animalType;
        this.animalImage = decideAnimalBackground(this.animalType);

        if (health <= 5 && health >= 1) {
            this.health = health;
        } else {
            this.health = 5;
        }
    }
    /**
     * @param type Animal enum
     * @return ImageView
     */
    public ImageView decideAnimalBackground(Animal type) {
        if (type.equals(Animal.CAT)) {
            setImage(new Image("Cat.png"));
            animalImage = new ImageView(getImage());
        } else if (type.equals(Animal.DOG)) {
            setImage(new Image("Dog.jpeg"));
            animalImage = new ImageView(getImage());
        } else if (type.equals(Animal.SQUIRREL)) {
            setImage(new Image("Squrriel.jpeg"));
            animalImage = new ImageView(getImage());
        } else if (type.equals(Animal.HORSE)) {
            setImage(new Image("Horse.jpeg"));
            animalImage = new ImageView(getImage());
        } else if (type.equals(Animal.BIRD)) {
            setImage(new Image("Bird.jpeg"));
            animalImage = new ImageView(getImage());
        } else {
            setImage(new Image("Lion.jpeg"));
            animalImage = new ImageView(getImage());
        }

        return animalImage;
    }

    /**
     * @param image Image
     */
    public void setImage(Image image) {
        this.image = image;
    }
    /**
     * @return String
     */
    public String getName() {
        return this.name;
    }
    /**
     * @return Animal enum
     */
    public Animal getAnimalType() {
        return this.animalType;
    }
    /**
     * @return int
     */
    public int getHealth() {
        return this.health;
    }
    /**
     * @return Image
     */
    public Image getImage() {
        return this.image;
    }
    /**
     * @return ImageView
     */
    public ImageView getAnimalImage() {
        return this.animalImage;
    }
}
