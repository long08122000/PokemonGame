import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameCard extends JLabel {
    private boolean isSelected = false;
    private boolean isDrop = false;
    private String name = "";

    public void setDrop(boolean drop) {
        isDrop = drop;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        if (isSelected) {
            setBorder(new LineBorder(Color.RED, 4));
        } else {
            setBorder(null);
        }
    }

    public void drop() {
        isDrop = true;
        setSelected(false);
        setIcon(null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public boolean isDrop() {
        return isDrop;
    }
}
