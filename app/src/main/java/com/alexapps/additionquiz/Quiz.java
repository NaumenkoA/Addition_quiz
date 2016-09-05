package com.alexapps.additionquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {
    private int mAnswer;
    private List<String> mOptions;
    private String mQuestion;

    public Quiz() {
        Random random = new Random();
        int firstNumber = random.nextInt(90)+10;
        int secondNumber = random.nextInt(90)+10;
        mQuestion = "What is " + firstNumber + " + " + secondNumber + "?";
        mAnswer = firstNumber + secondNumber;
        mOptions = createOptions(firstNumber,secondNumber);
        }

    private List<String> createOptions (int firstNum, int secondNum) {
        List <String> options = new ArrayList<>();
        int answer = firstNum + secondNum;
        int lowerNumber = 0;
        Random random = new Random();
       if (firstNum > secondNum) {
            lowerNumber = secondNum;
    }
        else {
            lowerNumber = firstNum;
        }
        int percentChange1 = random.nextInt(20)+10;
        int percentChange2 = random.nextInt(20)+10;
        int firstSign = random.nextInt(2);
        if (firstSign == 0) {
            firstSign = -1;
        }
        int secondSign = random.nextInt(2);
        if (secondSign == 0) {
            secondSign = -1;
        }
        float firstChangeAsFloat = (float) (firstSign*lowerNumber*percentChange1/100);
        float secondChangeAsFloat = (float) (secondSign*lowerNumber*percentChange2/100);
        int firstChange = (int) firstChangeAsFloat;
        int secondChange = (int) secondChangeAsFloat;
        int firstOption = firstNum + secondNum + firstChange;
        int secondOption = firstNum + secondNum + secondChange;
        if (firstOption == secondOption) {
            secondOption++;
        }
        options.add(answer+"");
        options.add (firstOption+"");
        options.add(secondOption+"");
        return getRandomPositions(options);
        }
    public String getQuestion () {
        return mQuestion;
    }

    public List <String> getOptions () {
        return mOptions;}

    public List<String> getRandomPositions(List<String> options) {
        List <String> randomPositions= new ArrayList<>();
        Random random = new Random();
        int count = 0;
        while (count < 3) {
            int randomPosition = random.nextInt(3 - count);
            String randomOption = options.get(randomPosition);
            randomPositions.add(randomOption);
            options.remove(randomPosition);
            count++;
        }
        return randomPositions;
    }

    public String getAnswer () {
        return mAnswer + "";
        }
    }




