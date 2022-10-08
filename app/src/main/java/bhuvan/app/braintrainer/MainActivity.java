package bhuvan.app.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    Button playAgainBtn;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void start(View view){
        startBtn.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);

        timerTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {

                resultTextView.setText("Your score: "+score +"/"+ numberOfQuestions);
                playAgainBtn.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    public void generateQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(a +" + "+ b);

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for(int i = 0; i<4; i++){
            if(i==locationOfCorrectAnswer){

                answers.add(a+b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }
        else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(score +"/"+ numberOfQuestions);
        generateQuestion();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.startBtn);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
         button0 = (Button) findViewById(R.id.button0);
         button1 = (Button) findViewById(R.id.button1);
         button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainBtn = (Button) findViewById(R.id.playAgainButton);







    }
}