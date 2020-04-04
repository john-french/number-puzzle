import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class BruteForceAnnoyingProblem {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ArrayList<String> solutions = new ArrayList<>();
        ArrayList<String> valuesToCheck = generatePossibleValues(7);
        for (String value : valuesToCheck) {
            if (
                    correctNumWrongPos("427", value, 1) &&
                            correctNumCorrectPos("490", value, 1) &&
                            correctNumWrongPos("032", value, 2) &&
                            allWrongNums("156", value) &&
                            correctNumWrongPos("593", value, 1)) {
                solutions.add(value);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Number of matches: " + solutions.size());
        System.out.println(solutions.toString());
        System.out.println("Time taken: " + (end - start) + " milliseconds");

    }

    public static boolean correctNumCorrectPos(String ruleValue, String testValue, int numMatches) {
        int countMatchRule = 0;
        int countCorrect = 0;
        for (int testPosition = 0; testPosition < testValue.length(); testPosition++) {
            int rulePosition = ruleValue.indexOf(testValue.charAt(testPosition));
            if (rulePosition == testPosition) {
                countMatchRule++;
            } else if (rulePosition != -1) {
                countCorrect++;
            }
        }
        return (countMatchRule == numMatches) && (countCorrect == 0);
    }

    public static boolean correctNumWrongPos(String ruleValue, String testValue, int numMatches) {
        int countMatchRule = 0;
        int countCorrect = 0;
        for (int testPosition = 0; testPosition < testValue.length(); testPosition++) {
            int rulePosition = ruleValue.indexOf(testValue.charAt(testPosition));
            if (rulePosition != -1 && rulePosition != testPosition) {
                countMatchRule++;
            } else if (rulePosition == testPosition) {
                countCorrect++;
            }
        }
        return (countMatchRule == numMatches) && (countCorrect == 0);
    }

    public static boolean allWrongNums(String ruleValue, String testValue) {
        boolean passes = true;
        for (int i = 0; i < testValue.length(); i++) {
            if (ruleValue.indexOf(testValue.charAt(i)) != -1) {
                passes = false;
                break;
            }
        }
        return passes;
    }


    public static ArrayList<String> generatePossibleValues(int numDigits) {
        ArrayList<String> possibleValues = new ArrayList<>();
        int numPossibleValues = 1;
        for (int x = 1; x <= numDigits; x++) {
            numPossibleValues = numPossibleValues * 10;
        }

        for (int value = 0; value < numPossibleValues; value++) {
            possibleValues.add(StringUtils.leftPad(Integer.toString(value), 3, '0'));
        }
        return possibleValues;
    }
}
