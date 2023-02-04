package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;

public class Fruit {

    private final int col = (int) (Math.random() * 98) + 1;
    private final int row = (int) (Math.random() * 23) + 1;


    public Position getPosition() {
        return new Position(this.col, this.row);
    }
}
