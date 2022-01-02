import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import gameobjects.*;
import java.util.Random;
import gameobjects.brick_strategies.CollisionStrategy;

public class BrickerGameManager extends GameManager{
    private Ball ball;

    public BrickerGameManager(String windowTitle, Vector2 windowDimensions){
        super(windowTitle, windowDimensions);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
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
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
        GameObject paddle = new UserPaddle(Vector2.ZERO, new Vector2(100, 15), paddleImage, inputListener, windowController.getWindowDimensions());
        paddle.setCenter(new Vector2(windowDimensions.x()/2, windowDimensions.y()-25));
        gameObjects().addGameObject(paddle);
        createBorders(windowDimensions);
        // add BG
        GameObject background = new GameObject(Vector2.ZERO, windowController.getWindowDimensions(), imageReader.readImage("assets/BG.jpeg", false));
        gameObjects().addGameObject(background, Layer.BACKGROUND);
        // add bricks
        Brick brick = new Brick(new Vector2(0, 0), new Vector2(1100, 15), imageReader.readImage("assets/brick.png", false), new CollisionStrategy(gameObjects()));
        gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);

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