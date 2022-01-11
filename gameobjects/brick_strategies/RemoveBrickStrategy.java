package gameobjects.brick_strategies;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;

public class CollisionStrategy implements BrickStrategy{
    private GameObjectCollection objects;

    public CollisionStrategy(GameObjectCollection objects){

        this.objects = objects;
    }
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter count) {
        objects.removeGameObject(thisObj, Layer.STATIC_OBJECTS);
        count.decrement();
    }
}
