package academy.mindswap.gameobjects.snake;
import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive;

    private final LinkedList<Position> snakePieces;

    public Snake() {
        snakePieces = new LinkedList<>();
        this.direction = Direction.RIGHT;
        this.alive = true;

        for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
            snakePieces.add(new Position((Field.getWidth() / 2) - i, Field.getHeight() / 2));
        }
    }

    public void increaseSize() {
        snakePieces.addLast(new Position(getTail().getCol() - 1, getTail().getRow()));
    }

    public void move(Direction direction) {
        if (movementExceptions(direction)) return;

        this.direction = direction;
        switch (direction) {
            case RIGHT: {
                snakePieces.addFirst(new Position(getHead().getCol() + 1, getHead().getRow()));
                snakePieces.removeLast();
                break;

            }
            case LEFT: {
                snakePieces.addFirst(new Position(getHead().getCol() -1 , getHead().getRow()));
                snakePieces.removeLast();
                break;

            }
            case DOWN: {
                snakePieces.addFirst(new Position(getHead().getCol(), getHead().getRow() + 1));
                snakePieces.removeLast();
                break;

            }
            case UP: {
                snakePieces.addFirst(new Position(getHead().getCol(), getHead().getRow() -1));
                snakePieces.removeLast();
                break;
            }
        }
    }

    private boolean movementExceptions(Direction direction) {
        if ((this.direction == Direction.RIGHT) && (direction == Direction.LEFT))
            return true;

        if ((this.direction == Direction.LEFT) && (direction == Direction.RIGHT))
            return true;

        if ((this.direction == Direction.UP) && (direction == Direction.DOWN))
            return true;

        if ((this.direction == Direction.DOWN) && (direction == Direction.UP))
            return true;

        return false;
    }

    public void move(){
        move(direction);
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {
        return snakePieces.getFirst();
    }

    public Position getTail() {
        return snakePieces.getLast();
    }

    public LinkedList<Position> getFullSnake(){
        return snakePieces;
    }

    public int getSnakeSize() {
        return snakePieces.size();
    }
}

