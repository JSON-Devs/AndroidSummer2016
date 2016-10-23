package jasongagnon.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import junit.framework.Test;

import java.util.ArrayList;

public class results extends AppCompatActivity {

    ListView Results;
    static ArrayList<Order> CandyOrders = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle data = getIntent().getExtras();
        Order myData = data.getParcelable("order");
        Results = (ListView)findViewById(R.id.lvResults);

        CandyOrders.add(myData);
        ArrayAdapter<Order> itemsAdapter =
                new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, CandyOrders);
        ListView listView = (ListView) findViewById(R.id.lvResults);
        listView.setAdapter(itemsAdapter);


    }

    public void backToMain(View view){
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        int count = CandyOrders.size();
        intent.putExtra("returnkey", count);
        setResult(RESULT_OK,intent);
        super.finish();
    }

}
