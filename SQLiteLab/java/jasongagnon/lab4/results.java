package jasongagnon.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class results extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    ListView Results;
    List<Order> allOrders;
    EditText PriceToSearch;
    ArrayAdapter<Order> itemsAdapter;
    //static ArrayList<Order> CandyOrders = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle data = getIntent().getExtras();
        Order myOrder = data.getParcelable("order");
        Results = (ListView)findViewById(R.id.lvResults);

        db.addOrder(myOrder);
        allOrders = db.getAllOrders();
        //CandyOrders.add(myData);
        itemsAdapter = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, allOrders);
        Results.setAdapter(itemsAdapter);
        PriceToSearch = (EditText)findViewById(R.id.txtPriceSearch);

    }

    public void priceSearch(View view){
        allOrders = db.priceSearch(Float.valueOf(PriceToSearch.getText().toString()));
        itemsAdapter = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, allOrders);
        Results.setAdapter(itemsAdapter);
    }

    public void backToMain(View view){
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        //int count = CandyOrders.size();
        //intent.putExtra("returnkey", count);
        setResult(RESULT_OK,intent);
        super.finish();
    }

}
