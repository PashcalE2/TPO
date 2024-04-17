package Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CountingSortTest {
    @Test
    @DisplayName("Хорошие массивы")
    public void checkGood() {
        CountingSort counting_sort = new CountingSort();
        assertAll(
                () -> assertArrayEquals(new int[] { 1, 2, 3, 4 }, counting_sort.sort(new int[] { 4, 2, 3, 1 })),
                () -> assertArrayEquals(new int[] { 0, 1, 1, 2, 2, 3, 4, 5 }, counting_sort.sort(new int[] { 3, 5, 1, 2, 0, 1, 2, 4 })),
                () -> assertArrayEquals(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, counting_sort.sort(new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 })),
                () -> assertArrayEquals(new int[] { 0, 1, 2, 3 }, counting_sort.sort(new int[] { 0, 1, 2, 3 })),
                () -> assertArrayEquals(
                        new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 30 },
                        counting_sort.sort(new int[] { 30, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 0, 1, 2, 3, 4, 5, 6, 7, 26, 27, 28, 29, 30 })
                )
        );
    }

    @Test
    @DisplayName("Пустой массив")
    public void checkEmpty() {
        CountingSort counting_sort = new CountingSort();
        assertArrayEquals(new int[]{}, counting_sort.sort(new int[]{}));
    }

    @Test
    @DisplayName("Плохие массивы")
    public void checkBad() {
        CountingSort counting_sort = new CountingSort();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> counting_sort.sort(new int[] { -1, 0, 1 })),
                () -> assertThrows(IllegalArgumentException.class, () -> counting_sort.sort(new int[] { 1, 2, -30 })),
                () -> assertThrows(IllegalArgumentException.class, () -> counting_sort.sort(new int[] { 0, 1, 31 }))
        );
    }

    @Test
    @DisplayName("Одно и то же значение")
    public void checkSameNumbers() {
        CountingSort counting_sort = new CountingSort();
        assertAll(
                () -> assertArrayEquals(new int[] { 0, 0, 0 }, counting_sort.sort(new int[] { 0, 0, 0 })),
                () -> assertArrayEquals(new int[] { 30, 30, 30 }, counting_sort.sort(new int[] { 30, 30, 30 }))
        );
    }

    @Test
    @DisplayName("null указатель")
    public void checkNull() {
        CountingSort counting_sort = new CountingSort();
        assertThrows(NullPointerException.class, () -> counting_sort.sort(null));
    }

    @Test
    @DisplayName("Проверка промежуточных результатов")
    public void checkStdout() {
        CountingSort counting_sort = new CountingSort();
        String[] expected = new String[] {
                "Подсчитываем значения исходного массива",
                "countingArray[2] = 1",
                "countingArray[1] = 1",
                "countingArray[3] = 1",
                "Рассчитываем индексы в новом массиве для значений исходного",
                "countingArray[1] = 0",
                "countingArray[2] = 1",
                "countingArray[3] = 2",
                "Копируем элементы исходного массива на их места в отсортированном массиве",
                "countingArray[2] = 1",
                "countingArray[1] = 0",
                "countingArray[3] = 2"
        };

        assertArrayEquals(new int[] { 1, 2, 3 }, counting_sort.sort(new int[] { 2, 1, 3 }));
        assertLinesMatch(Arrays.asList(expected), counting_sort.getHistory());
    }
}
