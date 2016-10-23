package jasongagnon.androidfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Play, Options, Leaderboards;
    public static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Play = (Button)findViewById(R.id.btnPlay);
        Options = (Button)findViewById(R.id.btnOptions);
        Leaderboards = (Button)findViewById(R.id.btnLeaderboards);

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToIntent(1);
            }
        });

        Options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToIntent(2);
            }
        });

        Leaderboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToIntent(3);
            }
        });

    }

    public void GoToIntent(int intentNo){
        try{
            switch(intentNo) {
                case 1:
                    Intent maze = new Intent(this, Maze.class);
                    startActivityForResult(maze, REQUEST_CODE);
                    break;
                case 2:
                    Intent options = new Intent(this, Options.class);
                    startActivityForResult(options, REQUEST_CODE);
                    break;
                case 3:
                    Intent leaderboards = new Intent(this, Leaderboards.class);
                    startActivityForResult(leaderboards, REQUEST_CODE);
                    break;
                default:
                    throw new Exception("Error loading intent");
            }
        }
        catch (Exception e){
            Log.e("intent", e + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
