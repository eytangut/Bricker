package gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import gameobjects.brick_strategies.CollisionStrategy;
public class Brick  extends GameObject {
    private CollisionStrategy coll;
    private Counter count;

    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, CollisionStrategy coll, Counter count) {
        super(topLeftCorner, dimensions, renderable);
        this.coll = coll;
        this.count = count;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        coll.onCollision(this, other, count);


    }
}

