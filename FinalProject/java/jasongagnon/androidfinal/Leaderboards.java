package jasongagnon.androidfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Leaderboards extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    ListView Leaders;
    List<Leader> leaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        Leaders = (ListView)findViewById(R.id.lvLeaderboards);

        leaders = db.getAllLeaders();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        // adding entries in List
        for (int counter = 0 ; counter < leaders.size();counter++) {
            adapter.add(
                    leaders.get(counter).getName() + " - " +
                    leaders.get(counter).getTime());
        }

        Leaders.setAdapter(adapter);
    }

}
