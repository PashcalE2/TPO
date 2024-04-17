package Task3;

public class Human extends Person {
    public Human(String name, Position position) {
        super(name, Race.HUMAN, position);
    }

    @Override
    public void sit() {
        System.out.printf("%s садится куда-нибудь%n", this);
        isSitting = true;
    }

    @Override
    public void getUp() {
        System.out.printf("%s встаёт%n", this);
        isSitting = false;
    }

    @Override
    public void solveProblem(Question problem) {
        if (!isSitting) {
            throw new RuntimeException("Чтобы решать вопрос, нужно сначала сесть");
        }

        System.out.printf("%s долго решает вопрос %s%n", this, problem);
    }
}
