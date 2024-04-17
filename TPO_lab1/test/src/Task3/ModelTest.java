package Task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    @Nested
    public class Questions {
        private Map<String, Human> humans;
        private Map<String, HyperIntelligentEntity> hyperIntelligentEntities;
        private List<Question> questions;

        @BeforeEach
        public void init() {
            humans = new HashMap<>();
            humans.put("Аликс", new Human("Аликс", new Position("Где-то на Земле: 45, 60")));
            humans.put("Гордон", new Human("Гордон", new Position("Где-то на Земле: 32, 48")));

            hyperIntelligentEntities = new HashMap<>();
            hyperIntelligentEntities.put("Волна", new HyperIntelligentEntity("Волна", new Position("Где-то везде")));
            hyperIntelligentEntities.put("Пучок", new HyperIntelligentEntity("Пучок", new Position("Где-то везде")));

            questions = new ArrayList<>(Arrays.asList(
                    new Question("Почему всё так как оно есть?"),
                    new Question("Зачем всё так как оно есть?"),
                    new Question("Как снять штаны через голову не порвав их?")
            ));
        }

        @Test
        @DisplayName("Проверка удачных споров")
        public void checkGoodArguing() {
            Human h1 = humans.get("Аликс"), h2 = humans.get("Гордон");
            HyperIntelligentEntity hie1 = hyperIntelligentEntities.get("Волна"), hie2 = hyperIntelligentEntities.get("Пучок");

            h1.moveTo(h2.getPosition());
            h1.argue(questions.get(0), h2);

            h1.argue(questions.get(1), h2);

            h2.runTo(new Position("Где-то на Земле: 55, 55"));
            h1.runTo(h2.getPosition());
            h2.argue(questions.get(2), h1);

            hie2.argue(questions.get(0), hie1);
            hie1.argue(questions.get(2), hie2);

            hie2.moveTo(h2.position);
            hie2.argue(questions.get(1), h2);
        }

        @Test
        @DisplayName("Проверка неудачных споров")
        public void checkBadArguing() {
            Human h1 = humans.get("Аликс"), h2 = humans.get("Гордон");
            HyperIntelligentEntity hie1 = hyperIntelligentEntities.get("Волна"), hie2 = hyperIntelligentEntities.get("Пучок");

            assertThrows(RuntimeException.class, () -> h1.argue(questions.get(0), h2));
            assertThrows(RuntimeException.class, () -> h1.argue(questions.get(2), h1));
            assertThrows(RuntimeException.class, () -> h1.argue(questions.get(2), null));
            assertThrows(RuntimeException.class, () -> hie1.argue(questions.get(0), hie1));
            assertThrows(RuntimeException.class, () -> hie2.argue(questions.get(2), hie2));
        }

        @Test
        @DisplayName("Проверка удачных решений вопросов")
        public void checkGoodSolving() {
            Human h1 = humans.get("Аликс"), h2 = humans.get("Гордон");
            HyperIntelligentEntity hie1 = hyperIntelligentEntities.get("Волна"), hie2 = hyperIntelligentEntities.get("Пучок");

            h1.sit();
            h1.solveProblem(questions.get(1));
            h1.getUp();

            h1.sit();
            h1.solveProblem(questions.get(0));

            h2.sit();
            h2.solveProblem(questions.get(2));

            hie1.solveProblem(questions.get(0));
            hie2.solveProblem(questions.get(1));
        }

        @Test
        @DisplayName("Проверка неудачных решений вопросов")
        public void checkBadSolving() {
            Human h1 = humans.get("Аликс"), h2 = humans.get("Гордон");
            h1.getUp();
            h2.getUp();

            assertThrows(RuntimeException.class, () -> h1.solveProblem(questions.get(0)));
            assertThrows(RuntimeException.class, () -> h2.solveProblem(questions.get(2)));
            assertThrows(RuntimeException.class, () -> h2.solveProblem(null));
        }
    }

    @Nested
    public class Cricket {
        private Map<String, Human> humans;
        private Map<String, HyperIntelligentEntity> hyperIntelligentEntities;

        @BeforeEach
        public void init() {
            humans = new HashMap<>();
            humans.put("Аликс", new Human("Аликс", new Position("Где-то на Земле: 45, 60")));
            humans.put("Гордон", new Human("Гордон", new Position("Где-то на Земле: 32, 48")));

            hyperIntelligentEntities = new HashMap<>();
            hyperIntelligentEntities.put("Волна", new HyperIntelligentEntity("Волна", new Position("Где-то везде")));
            hyperIntelligentEntities.put("Пучок", new HyperIntelligentEntity("Пучок", new Position("Где-то везде")));
        }

        @Test
        @DisplayName("Проверка удачных игр в крикет")
        public void checkGoodPlay() {
            Human h1 = humans.get("Аликс"), h2 = humans.get("Гордон");
            HyperIntelligentEntity hie1 = hyperIntelligentEntities.get("Волна"), hie2 = hyperIntelligentEntities.get("Пучок");

            hie1.playCricket(h2);
            hie2.playCricket(h1);
        }

        @Test
        @DisplayName("Проверка неудачных игр в крикет")
        public void checkBadPlay() {
            Human h1 = humans.get("Аликс"), h2 = humans.get("Гордон");
            HyperIntelligentEntity hie1 = hyperIntelligentEntities.get("Волна"), hie2 = hyperIntelligentEntities.get("Пучок");

            assertThrows(RuntimeException.class, () -> hie1.playCricket(null));
        }
    }
}
