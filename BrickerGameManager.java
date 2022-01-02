import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import gameobjects.*;
import java.util.Random;
import gameobjects.brick_strategies.CollisionStrategy;

public class BrickerGameManager extends GameManager{
    private Ball ball;
    private WindowController windowController;
    Counter brickNum = new Counter();

    public BrickerGameManager(String windowTitle, Vector2 windowDimensions){
        super(windowTitle, windowDimensions);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        this.windowController = windowController;
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        brickNum.increaseBy(63);
        // add ball
        Renderable ballImage =
                imageReader.readImage("assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop.wav");
        ball = new Ball(Vector2.ZERO, new Vector2(50, 50), ballImage, collisionSound);
        float ballVelX = 300;
        float ballVelY = 300;
        Random rand = new Random();
        if(rand.nextBoolean()){
            ballVelX *= -1;
        }if(rand.nextBoolean()){
            ballVelY *= -1;
        }
        ball.setVelocity(new Vector2(ballVelX, ballVelY));
        Vector2 windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult(0.5f));
        gameObjects().addGameObject(ball);
        // add paddle
        addPaddle(imageReader, inputListener, windowController, windowDimensions);
        // add BG
        addBG(imageReader, windowController);
        // add bricks
        addBricks(imageReader, windowDimensions);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkWonOrLost();
    }

    private void checkWonOrLost() {
        double ballHeight = ball.getCenter().y();
        String prompt = "";

        if(brickNum.value() ==0){
            prompt = "You Won! :) :)";
        }
        if (ballHeight > 900){
            prompt = "You Lost! :( :(";
        }
        if (!prompt.isEmpty()){
            prompt+="\nPlay Again?";
            if(windowController.openYesNoDialog(prompt)){
                windowController.resetGame();
            }
            else {windowController.closeWindow();}

        }
    }

    private void addPaddle(ImageReader imageReader, UserInputListener inputListener, WindowController windowController, Vector2 windowDimensions) {
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
        GameObject paddle = new UserPaddle(Vector2.ZERO, new Vector2(100, 15), paddleImage, inputListener, windowController.getWindowDimensions());
        paddle.setCenter(new Vector2(windowDimensions.x()/2, windowDimensions.y()-25));
        gameObjects().addGameObject(paddle);
        createBorders(windowDimensions);
    }

    private void addBG(ImageReader imageReader, WindowController windowController) {
        GameObject background = new GameObject(Vector2.ZERO, windowController.getWindowDimensions(), imageReader.readImage("assets/BG.jpeg", false));
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    private void addBricks(ImageReader imageReader, Vector2 windowDimensions) {
        addBrickRow1(imageReader, windowDimensions);
        addBrickRow2(imageReader, windowDimensions);
        addBrickRow3(imageReader, windowDimensions);
        addBrickRow4(imageReader, windowDimensions);
        addBrickRow5(imageReader, windowDimensions);
        addBrickRow6(imageReader, windowDimensions);
        addBrickRow7(imageReader, windowDimensions);
    }

    private void addBrickRow5(ImageReader imageReader, Vector2 windowDimensions) {
        int i = 0;
        while (i < 9){
            float x = (float) (5+1+(120.5*i));
            Brick brick = new Brick(new Vector2(x, 93), new Vector2((windowDimensions.x()/8)-17, 20), imageReader.readImage("assets/brick.png", false), new CollisionStrategy(gameObjects()), brickNum);
            gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            i++;
        }
    }
    private void addBrickRow6(ImageReader imageReader, Vector2 windowDimensions) {
        int i = 0;
        while (i < 9){
            float x = (float) (5+1+(120.5*i));
            Brick brick = new Brick(new Vector2(x, 115), new Vector2((windowDimensions.x()/8)-17, 20), imageReader.readImage("assets/brick.png", false), new CollisionStrategy(gameObjects()), brickNum);
            gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            i++;
        }
    }
    private void addBrickRow7(ImageReader imageReader, Vector2 windowDimensions) {
        int i = 0;
        while (i < 9){
            float x = (float) (5+1+(120.5*i));
            Brick brick = new Brick(new Vector2(x, 137), new Vector2((windowDimensions.x()/8)-17, 20), imageReader.readImage("assets/brick.png", false), new CollisionStrategy(gameObjects()), brickNum);
            gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            i++;
        }
    }

    private void addBrickRow4(ImageReader imageReader, Vector2 windowDimensions) {
        int i = 0;
        while (i < 9){
            float x = (float) (5+1+(120.5*i));
            Brick brick = new Brick(new Vector2(x, 71), new Vector2((windowDimensions.x()/8)-17, 20), imageReader.readImage("assets/brick.png", false), new CollisionStrategy(gameObjects()), brickNum);
            gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            i++;
        }
    }

    private void addBrickRow3(ImageReader imageReader, Vector2 windowDimensions) {
        int i = 0;
        while (i < 9){
            float x = (float) (5+1+(120.5*i));
            Brick brick = new Brick(new Vector2(x, 49), new Vector2((windowDimensions.x()/8)-17, 20), imageReader.readImage("assets/brick.png", false), new CollisionStrategy(gameObjects()), brickNum);
            gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            i++;
        }
    }

    private void addBrickRow2(ImageReader imageReader, Vector2 windowDimensions) {
        int i = 0;
        while (i < 9){
            float x = (float) (5+1+(120.5*i));
            Brick brick = new Brick(new Vector2(x, 27), new Vector2((windowDimensions.x()/8)-17, 20), imageReader.readImage("assets/brick.png", false), new CollisionStrategy(gameObjects()), brickNum);
            gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            i++;
        }
    }

    private void addBrickRow1(ImageReader imageReader, Vector2 windowDimensions) {
        int i = 0;
        while (i < 9){
            float x = (float) (5+2+(120.5*i));
            Brick brick = new Brick(new Vector2(x, 5), new Vector2((windowDimensions.x()/8)-17, 20), imageReader.readImage("assets/brick.png", false), new CollisionStrategy(gameObjects()), brickNum);
            gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            i++;
        }
    }

    private void createBorders(Vector2 windowDimensions){
        gameObjects().addGameObject(new GameObject(Vector2.ZERO, new Vector2(10, windowDimensions.y()), null), Layer.STATIC_OBJECTS);
        gameObjects().addGameObject(new GameObject(new Vector2(1040,0), new Vector2(10, windowDimensions.y()), null), Layer.STATIC_OBJECTS);
        gameObjects().addGameObject(new GameObject(new Vector2(0,0), new Vector2(windowDimensions.x(), 10), null), Layer.STATIC_OBJECTS);

    }

    public static void main(String[] args) {

        new BrickerGameManager("Bricker", new Vector2(1100, 900)).run();

    }
}