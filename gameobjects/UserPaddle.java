package gameobjects;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

public class UserPaddle extends GameObject {
    private static final int MOVEMENT_SPEED = 400;
    private UserInputListener inputListener;
    private Vector2 windowDimensions;
    Vector2 MovementDir = Vector2.ZERO;
    final int MIN_DISTANCE_FROM_SCREEN_EDGE = 10;
    public UserPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, UserInputListener inputListener, Vector2 windowDimensions) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)){
            MovementDir = MovementDir.add(Vector2.LEFT);
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            MovementDir = MovementDir.add(Vector2.RIGHT);
        }
        setVelocity(MovementDir.mult(MOVEMENT_SPEED));
        MovementDir = Vector2.ZERO;
        if (getTopLeftCorner().x() < MIN_DISTANCE_FROM_SCREEN_EDGE){
            transform().setTopLeftCornerX(10);

        }
        if (getTopLeftCorner().x() > windowDimensions.x() - MIN_DISTANCE_FROM_SCREEN_EDGE - getDimensions().x()){
            transform().setTopLeftCornerX(990);
        }
    }
}
