package com.alexapps.additionquiz;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView mQuizTextView;
    private TextView mAttemptsNumber;
    private TextView mCorrectAnswers;
    private RadioButton mAnswerRadioButton1;
    private RadioButton mAnswerRadioButton2;
    private RadioButton mAnswerRadioButton3;
    private RadioGroup mRadioGroup;
    private Button mSubmitButton;
    private Quiz mQuiz;
    private int mAttempts;
    private int mCorrectAnswer;
    private MediaPlayer mPlayCorrect;
    private MediaPlayer mPlayNoChoice;
    private MediaPlayer mPlayIncorrect;

    public Quiz newQuiz() {
        Quiz quiz = new Quiz();
        List<String> options = quiz.getOptions();
        mQuizTextView.setText(quiz.getQuestion());
        mAnswerRadioButton1.setText(options.get(0));
        mAnswerRadioButton2.setText(options.get(1));
        mAnswerRadioButton3.setText(options.get(2));
        mAttempts++;
        return quiz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuizTextView = (TextView) findViewById(R.id.questionTextView);
        mAnswerRadioButton1 = (RadioButton) findViewById(R.id.answerButton1);
        mAnswerRadioButton2 = (RadioButton) findViewById(R.id.answerButton2);
        mAnswerRadioButton3 = (RadioButton) findViewById(R.id.answerButton3);
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mCorrectAnswers = (TextView) findViewById(R.id.correctAnswers);
        mAttemptsNumber = (TextView) findViewById(R.id.attemptsNumber);
        mQuiz = newQuiz();
        mAttempts = 0;
        mCorrectAnswer = 0;
        mPlayCorrect = MediaPlayer.create(this, R.raw.correct);
        mPlayIncorrect = MediaPlayer.create(this, R.raw.incorrect);
        mPlayNoChoice = MediaPlayer.create(this, R.raw.no_choice);

        View.OnClickListener submitListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mRadioGroup.getCheckedRadioButtonId() == -1) {
                    mPlayNoChoice.start();
                    Toast.makeText(QuizActivity.this, "Select your answer!", Toast.LENGTH_SHORT).show();
                                    } else {
                    int selectedButtonId = mRadioGroup.getCheckedRadioButtonId();
                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedButtonId);
                    if (selectedRadioButton.getText().equals(mQuiz.getAnswer())) {
                        Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                        mCorrectAnswer++;
                        mPlayCorrect.start();
                    } else {
                        Toast.makeText(QuizActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                      mPlayIncorrect.start();
                    }
                    mQuiz = newQuiz();
                    mRadioGroup.clearCheck();
                    mAttemptsNumber.setText("Attempts: "+mAttempts);
                    mCorrectAnswers.setText("Correct: " + mCorrectAnswer);
                                    }
                        }

                    };
        mSubmitButton.setOnClickListener(submitListener);
    } ;
}


