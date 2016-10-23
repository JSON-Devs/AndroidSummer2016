package jasongagnon.androidfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;

public class Maze extends AppCompatActivity {

    Button left, right, up, down, add;
    EditText Name;
    DatabaseHandler db = new DatabaseHandler(this);
    int[][] myLevel;
    static Level lvlClass = new Level();
    int manI = 0, manJ = 0;
    TextView feedback, time;
    private long startTime = 0L;
    private Handler myHandler = new Handler();
    long timeInMillies = 0L;
    long timeSwap = 0L;
    long finalTime = 0L;
    String sTime;
    public static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);

        feedback = (TextView)findViewById(R.id.txtFeedback);
        time = (TextView)findViewById(R.id.txtTime);

        startTime = SystemClock.uptimeMillis();
        myHandler.postDelayed(updateTimerMethod, 0);

        add = (Button)findViewById(R.id.btnAddName);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                db.addLeader(new Leader(Name.getText().toString(), sTime));
                feedback.setText("Name Added!");
                finish();
            }
        });
        Name = (EditText)findViewById(R.id.etName);

        left = (Button)findViewById(R.id.btnLeft);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                myLevel = lvlClass.getLevel();
                manI = lvlClass.getManI();
                manJ = lvlClass.getManJ();

                switch (myLevel[manI - 1][manJ]) {
                    case 1:
                        break;
                    case 3:
                        myLevel[manI][manJ] = 0;
                        lvlClass.setManI(manI - 1);
                        feedback.setText("You Win!");
                        endTimer();
                        break;
                    default:
                        myLevel[manI][manJ] = 0;
                        myLevel[manI - 1][manJ] = 2;
                        lvlClass.setManI(manI - 1);
                }

                lvlClass.setLevel(myLevel);
            }
        });
        right = (Button)findViewById(R.id.btnRight);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                myLevel = lvlClass.getLevel();
                manI = lvlClass.getManI();
                manJ = lvlClass.getManJ();

                switch (myLevel[manI + 1][manJ]) {
                    case 1:
                        break;
                    case 3:
                        myLevel[manI][manJ] = 0;
                        lvlClass.setManI(manI + 1);
                        feedback.setText("You Win!");
                        endTimer();
                        break;
                    default:
                        myLevel[manI][manJ] = 0;
                        myLevel[manI + 1][manJ] = 2;
                        lvlClass.setManI(manI + 1);
                }

                lvlClass.setLevel(myLevel);
            }
        });
        up = (Button)findViewById(R.id.btnUp);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                myLevel = lvlClass.getLevel();
                manI = lvlClass.getManI();
                manJ = lvlClass.getManJ();

                switch (myLevel[manI][manJ - 1]) {
                    case 1:
                        break;
                    case 3:
                        myLevel[manI][manJ] = 0;
                        lvlClass.setManJ(manJ - 1);
                        feedback.setText("You Win!");
                        endTimer();
                        break;
                    default:
                        myLevel[manI][manJ] = 0;
                        myLevel[manI][manJ - 1] = 2;
                        lvlClass.setManJ(manJ - 1);
                }

                lvlClass.setLevel(myLevel);
            }
        });
        down = (Button)findViewById(R.id.btnDown);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                myLevel = lvlClass.getLevel();
                manI = lvlClass.getManI();
                manJ = lvlClass.getManJ();

                switch (myLevel[manI][manJ + 1]) {
                    case 1:
                        break;
                    case 3:
                        myLevel[manI][manJ] = 0;
                        lvlClass.setManJ(manJ + 1);
                        feedback.setText("You Win!");
                        endTimer();
                        break;
                    default:
                        myLevel[manI][manJ] = 0;
                        myLevel[manI][manJ + 1] = 2;
                        lvlClass.setManJ(manJ + 1);
                }

                lvlClass.setLevel(myLevel);
            }
        });
    }

    public void endTimer(){
        timeSwap += timeInMillies;
        myHandler.removeCallbacks(updateTimerMethod);
        add.setVisibility(View.VISIBLE);
        Name.setVisibility(View.VISIBLE);
    }

    private Runnable updateTimerMethod = new Runnable() {

        public void run() {
            timeInMillies = SystemClock.uptimeMillis() - startTime;
            finalTime = timeSwap + timeInMillies;

            int seconds = (int) (finalTime / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            int milliseconds = (int) (finalTime % 1000);
            sTime = "" + minutes + ":"
                    + String.format("%02d", seconds) + ":"
                    + String.format("%03d", milliseconds);
            time.setText(sTime);
            myHandler.postDelayed(this, 0);
        }

    };


    @Override
    public void finish() {
        Intent leaderboards = new Intent(this, Leaderboards.class);
        startActivityForResult(leaderboards, REQUEST_CODE);
        super.finish();
    }

}
