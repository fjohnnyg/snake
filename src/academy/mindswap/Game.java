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
    private int score;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
        this.score = score;
    }

    public void start() throws InterruptedException {
        generateFruit();

        while (snake.isAlive()) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
        }
    }

    private void generateFruit() {
        fruit = new Fruit();
        Field.drawFruit(fruit);
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

    private int increaseScore() {
        return score++;
    }

    private void checkCollisions() {
        if (snake.getHead().getCol() == 1 || snake.getHead().getCol() == Field.getWidth() - 2 ||
                snake.getHead().getRow() == 1 || snake.getHead().getRow() == Field.getHeight() - 2)
            snake.die();

        for (int i = 1; i < snake.getSnakeSize(); i++) {
            if (snake.getHead().equals(snake.getFullSnake().get(i)))
                snake.die();
        }

        if (snake.getHead().equals(fruit.getPosition())) {
            snake.increaseSize();
            this.delay -= 5;
            increaseScore();
            generateFruit();
        }
    }

    public int getScore() {
        return score;
    }
}
