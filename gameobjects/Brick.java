package gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Brick  extends GameObject {
    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        CollisionStrategy.onCollision(this, other);
    }
}
class CollisionStrategy {

    static void onCollision(GameObject thisObj, GameObject otherObj) {
        System.out.println("collision with brick detectet");
    }
}

