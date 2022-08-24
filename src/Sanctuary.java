import javafx.scene.layout.Pane;
/**
 * class Sanctuary.
 * @author Alvin Le
 * @version 1.0
 */
public class Sanctuary {
    private Pane area;
    private boolean isEmpty;
    /**
     * @param area Pane
     * @param isEmpty boolean
     */
    public Sanctuary(Pane area, boolean isEmpty) {
        this.area = area;
        this.isEmpty = isEmpty;
    }
    /**
     * @return Pane
     */
    public Pane getArea() {
        return this.area;
    }
    /**
     * @return boolean
     */
    public boolean getIsEmpty() {
        return this.isEmpty;
    }
}
