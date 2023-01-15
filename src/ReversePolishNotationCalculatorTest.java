import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReversePolishNotationCalculatorTest {

    @Test
    public void shouldCalculateAddition() {
        ReversePolishNotationCalculator reversePolishNotationCalculator = new ReversePolishNotationCalculator();
        int sum = reversePolishNotationCalculator.calculatePolishNotation("4 3 +");
        Assertions.assertEquals(7, sum);
    }

    @Test
    public void shouldCalculateMinus() {
        ReversePolishNotationCalculator reversePolishNotationCalculator = new ReversePolishNotationCalculator();
        int sum = reversePolishNotationCalculator.calculatePolishNotation("4 3 -");
        Assertions.assertEquals(1, sum);
    }

    @Test
    public void shouldCalculateMultiplier() {
        ReversePolishNotationCalculator reversePolishNotationCalculator = new ReversePolishNotationCalculator();
        int sum = reversePolishNotationCalculator.calculatePolishNotation("4 3 *");
        Assertions.assertEquals(12, sum);
    }

    @Test
    public void shouldCalculateNegativeNumbers() {
        ReversePolishNotationCalculator reversePolishNotationCalculator = new ReversePolishNotationCalculator();
        int sum = reversePolishNotationCalculator.calculatePolishNotation("-4 -3 -");
        Assertions.assertEquals(-1, sum);
    }

    @Test
    public void shouldCalculateManySpaces() {
        ReversePolishNotationCalculator reversePolishNotationCalculator = new ReversePolishNotationCalculator();
        int sum = reversePolishNotationCalculator.calculatePolishNotation("4 3 2 1 + - *");
        Assertions.assertEquals(0, sum);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}