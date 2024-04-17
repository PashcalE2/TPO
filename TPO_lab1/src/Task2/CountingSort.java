package Task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountingSort {
    private static final int max_value = 30;
    private List<String> history;

    public CountingSort() {
        this.history = new ArrayList<>(256);
    }

    public int[] sort(int[] array) {
        history.clear();

        if (array == null) {
            throw new NullPointerException("null указатель");
        }

        if (array.length <= 1) {
            return array;
        }

        int min = Arrays.stream(array).min().getAsInt();
        if (min < 0) {
            throw new IllegalArgumentException(String.format("min(array) = %d - поддерживаются только натуральные числа и 0", min));
        }

        int max = Arrays.stream(array).max().getAsInt();
        if (max > max_value) {
            throw new IllegalArgumentException(String.format("max(array) = %d, максимальное натуральное должно быть не больше %d", max, max_value));
        }

        int[] countingArray = new int[max + 1];
        Arrays.fill(countingArray, 0);

        history.add("Подсчитываем значения исходного массива");
        for (int value : array) {
            countingArray[value]++;
            history.add(String.format("countingArray[%d] = %d", value, countingArray[value]));
        }

        history.add("Рассчитываем индексы в новом массиве для значений исходного");
        for (int i = 0; i < max; i++) {
            countingArray[i + 1] += countingArray[i];
            history.add(String.format("countingArray[%d] = %d", i + 1, countingArray[i]));
        }

        int[] sortedArray = new int[array.length];

        history.add("Копируем элементы исходного массива на их места в отсортированном массиве");
        for (int j : array) {
            sortedArray[--countingArray[j]] = j;
            history.add(String.format("countingArray[%d] = %d", j, countingArray[j]));
        }

        return sortedArray;
    }

    public List<String> getHistory() {
        return history;
    }
}
