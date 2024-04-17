package Task3;

public class HyperIntelligentEntity extends Person {

    public HyperIntelligentEntity(String name, Position position) {
        super(name, Race.HYPER_INTELLIGENT_ENTITY, position);
        this.isSitting = null;
    }

    public void playCricket(Human human) {
        Position old_position = position;
        moveTo(human.position);
        hit(human);
        runTo(old_position);
    }

    @Override
    public void sit() {
        System.out.printf("%s сидит всегда и везде%n", this);
    }

    @Override
    public void getUp() {
        System.out.printf("%s стоит всегда и везде%n", this);
    }

    @Override
    public void solveProblem(Question problem) {
        System.out.printf("%s быстро решает проблему %s%n", this, problem);
    }
}
