package gameobjects.brick_strategies;
import danogl.collisions.Layer;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import gameobjects.Ball;
import gameobjects.StatusIndicator;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;
import gameobjects.UserPaddle;

public class ShrinkPaddleStrategy extends RemoveBrickStrategy implements BrickStrategy{


    private Renderable render;
    private Sound sound;
    private UserPaddle paddle;

    public ShrinkPaddleStrategy(GameObjectCollection objects, Renderable render, Sound sound, UserPaddle paddle) {
        super(objects);
        this.render = render;
        this.sound = sound;
        this.paddle = paddle;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, Counter count, GameObjectCollection objects) {
        objects.removeGameObject(thisObj, Layer.STATIC_OBJECTS);
        count.decrement();
        StatusIndicator ShrinkPaddle = new StatusIndicator(thisObj.getCenter(), new Vector2(50, 50), render, sound);
        objects.addGameObject(ShrinkPaddle);
        ShrinkPaddle.fall(paddle);
    }
}
