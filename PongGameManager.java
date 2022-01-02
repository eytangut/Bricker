import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.*;

import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import gameobjects.AIPaddle;
import gameobjects.Ball;
import gameobjects.UserPaddle;

import java.util.Random;

public class PongGameManager extends GameManager{

    private static final int BALL_SPEED = 300;
    private Ball ball;

    public PongGameManager(String windowTitle, Vector2 windowDimensions){
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
        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
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
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
        // add user paddle
        addUserPaddle(inputListener, windowController, windowDimensions, paddleImage);
        // add AI paddle
        addAIPaddle(windowDimensions, paddleImage);
        // add borders
        createBorders(windowDimensions);
        //add bg
        addBg(imageReader, windowController);


    }

    private void addUserPaddle(UserInputListener inputListener, WindowController windowController, Vector2 windowDimensions, Renderable paddleImage) {
        GameObject userPaddle = new UserPaddle(Vector2.ZERO, new Vector2(100, 15), paddleImage, inputListener, windowController.getWindowDimensions());
        userPaddle.setCenter(new Vector2(windowDimensions.x()/2, windowDimensions.y()-30));
        gameObjects().addGameObject(userPaddle);
    }

    private void addAIPaddle(Vector2 windowDimensions, Renderable paddleImage) {
        GameObject AIPaddle = new AIPaddle(Vector2.ZERO, new Vector2(100, 15), paddleImage, ball);
        AIPaddle.setCenter(new Vector2(windowDimensions.x()/2, 30));
        gameObjects().addGameObject(AIPaddle);
    }

    private void addBg(ImageReader imageReader, WindowController windowController) {
        GameObject background = new GameObject(Vector2.ZERO, windowController.getWindowDimensions(), imageReader.readImage("assets/BG.jpeg", false));
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    private void createBorders(Vector2 windowDimensions){
        gameObjects().addGameObject(new GameObject(Vector2.ZERO, new Vector2(10, windowDimensions.y()), null));
        gameObjects().addGameObject(new GameObject(new Vector2(1040,0), new Vector2(10, windowDimensions.y()), null));

    }
    public static void main(String[] args) {

        new PongGameManager("Pong", new Vector2(1100, 900)).run();

    }
}