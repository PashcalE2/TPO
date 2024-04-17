package Task3;

public abstract class Person {
    protected final String name;
    protected final Race race;
    protected Position position;

    protected Boolean isSitting;

    public Person(String name, Race race, Position position) {
        this.name = name;
        this.race = race;
        this.position = position;
        this.isSitting = false;
    }

    public void argue(Question question, Person person) {
        if (!this.getPosition().equals(person.position)) {
            throw new RuntimeException("Чтобы спорить, нужно быть рядом");
        }

        if (this.equals(person)) {
            throw new RuntimeException("Спорить с самим собой нельзя");
        }

        System.out.printf("%s спорит с %s по вопросу %s%n", this, person, question);
    }

    public void moveTo(Position position) {
        System.out.printf("%s подбирается к %s%n", this, position);
        setPosition(position);
    }

    public void runTo(Position position) {
        System.out.printf("%s бежит к %s%n", this, position);
        setPosition(position);
    }

    public void hit(Person person) {
        System.out.printf("%s - удар по %s%n", this, person);
    }

    public abstract void sit();

    public abstract void getUp();

    public abstract void solveProblem(Question problem);

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean isSitting() {
        return isSitting;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", name, race.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        Person person = (Person) obj;
        return
                this.name.equals(person.getName()) &&
                this.race.equals(person.getRace());
    }
}
