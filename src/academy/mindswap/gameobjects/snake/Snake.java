package academy.mindswap.gameobjects.snake;

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
            snakePieces.add(new Position(50 - i, 12));
        }
    }

    public void increaseSize() {
        snakePieces.addLast(new Position(getTail().getCol() - 1, getTail().getRow()));
    }

    public void move(Direction direction) {
        switch (direction) {
            case RIGHT: {
                setDirection(Direction.RIGHT);
                snakePieces.addFirst(new Position(getHead().getCol() + 1, getHead().getRow()));
                snakePieces.removeLast();
                break;
            }
            case LEFT: {
                setDirection(Direction.LEFT);
                snakePieces.addFirst(new Position(getHead().getCol() -1 , getHead().getRow()));
                snakePieces.removeLast();
                break;
            }
            case DOWN: {
                setDirection(Direction.DOWN);
                snakePieces.addFirst(new Position(getHead().getCol(), getHead().getRow() + 1));
                snakePieces.removeLast();
                break;
            }
            case UP: {
                setDirection(Direction.UP);
                snakePieces.addFirst(new Position(getHead().getCol(), getHead().getRow() -1));
                snakePieces.removeLast();
                break;
            }
        }
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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}

