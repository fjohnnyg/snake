package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private int delay;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        generateFruit();

        while (true) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
            if (!snake.isAlive())
                break;
        }
    }

    private void generateFruit() {

        while (fruit == null) {
            fruit = new Fruit();
            Field.drawFruit(fruit);
        }
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }

    private void checkCollisions() {
        //check wall collision
        for (int i = 0; i < Field.getHeight(); i++) {
            if ((snake.getHead().getCol() == 0) || snake.getHead().getCol() == 99)
                snake.die();
        }
        for (int i = 0; i < Field.getWidth(); i++) {
            if (snake.getHead().getRow() == 0 || snake.getHead().getRow() == 24)
                snake.die();
        }

        //check body collision
        for (int i = 1; i < snake.getSnakeSize(); i++) {
            if (snake.getHead().equals(snake.getFullSnake().get(i))) {
                snake.die();
            }
        }

        //check fruit collision
        if (snake.getHead().equals(fruit.getPosition())) {
            snake.increaseSize();
            fruit = null;
            this.delay -= 5;
            generateFruit();
        }
    }
}
