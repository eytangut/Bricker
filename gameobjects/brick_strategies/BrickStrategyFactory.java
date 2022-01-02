package gameobjects.brick_strategies;

public class BrickStrategyFactory {
    public CollisionStrategy getStrategy(){
        return new CollisionStrategy(null);
    }
}
