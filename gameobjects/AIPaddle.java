package gameobjects;

import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class AIPaddle extends GameObject {
    private GameObject objectToFollow;

    public AIPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, GameObject objectToFollow) {
        super(topLeftCorner, dimensions, renderable);
        this.objectToFollow = objectToFollow;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;
        if(objectToFollow.getCenter().x() < getCenter().x()){
            movementDir = Vector2.LEFT;
        }
        if(objectToFollow.getCenter().x() > getCenter().x()){
            movementDir = Vector2.RIGHT;
        }
        if (getTopLeftCorner().x() < 10){
            transform().setTopLeftCornerX(10);

        }
        if (getTopLeftCorner().x() > 1100 - 10 - getDimensions().x()){
            transform().setTopLeftCornerX(990);
        }
        setVelocity(movementDir.mult(400));
    }
}
