package gameobjects.brick_strategies;

import danogl.GameObject;

public class CollisionStrategy {

    void onCollision(GameObject thisObj, GameObject otherObj) {
        System.out.println("collision with brick detectet");
    }
}
