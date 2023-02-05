package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

public class Fruit {

    private final int col = (int) (Math.random() * (Field.getWidth() - 2)) + 1;
    private final int row = (int) (Math.random() * (Field.getHeight() - 2)) + 1;


    public Position getPosition() {
        return new Position(this.col, this.row);
    }
}
