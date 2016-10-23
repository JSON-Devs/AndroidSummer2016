package jasongagnon.androidlab1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText FName, LName, NoOfBars;
    private RadioButton ShippingExp, ShippingNor;
    private Spinner Type;
    private TextView Feedback;
    private ArrayList<Order> candyOrders = new ArrayList<Order>();
    private List<String> candyList = new ArrayList<String>();
    private ArrayAdapter<String> dataAdapter;
    private Order one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FName = (EditText)findViewById(R.id.txtFName);
        LName = (EditText)findViewById(R.id.txtLName);
        NoOfBars = (EditText)findViewById(R.id.txtNoOfBars);

        Feedback = (TextView)findViewById(R.id.txtFeedback);

        ShippingExp = (RadioButton)findViewById(R.id.rbEx);
        ShippingNor = (RadioButton)findViewById(R.id.rbN);

        Type = (Spinner)findViewById(R.id.spinner);

        Button saveOrder = (Button)findViewById(R.id.btnSave);
        saveOrder.setOnClickListener(saveBtn);

        //List<String> candyList = new ArrayList<String>();
        candyList.add("Milk Chocolate");
        candyList.add("Dark Chocolate");
        candyList.add("White Chocolate");


        dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,candyList);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        Type.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private View.OnClickListener saveBtn = new View.OnClickListener() {
        public void onClick(View v) {
            String fName, lName, type;
            int noOfBars;
            boolean isExp;

            fName = String.valueOf(FName.getText().toString());
            lName = String.valueOf(LName.getText().toString());
            noOfBars = Integer.valueOf(NoOfBars.getText().toString()).intValue();
            type = Type.getSelectedItem().toString();

            if (ShippingExp.isChecked()){
                isExp = true;
            }
            else {
                isExp = false;
            }

            Order candy = new Order();
            candy.setfName(fName);
            candy.setlName(lName);
            candy.setNoOfBars(noOfBars);
            candy.setType(type);
            candy.setShippingExpedited(isExp);

            candyOrders.add(candy);

            Feedback.setText("Order Added - " + candyOrders.size() + " orders");

        }
    };

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
        else if (id == R.id.action_new){
            FName.setText("");
            LName.setText("");
            Type.setSelection(0);
            NoOfBars.setText("");
            ShippingExp.setChecked(false);
            ShippingNor.setChecked(false);
        }
        else if(id == R.id.action_double){
            int noBars = Integer.valueOf(NoOfBars.getText().toString());
            noBars = noBars * 2;
            String noBarsOf = String.valueOf(noBars);
            NoOfBars.setText(noBarsOf);
        }
        else if(id == R.id.action_newTextOnly){
            FName.setText("");
            LName.setText("");
            NoOfBars.setText("");
        }
        else if(id == R.id.action_getFirst){
            one = candyOrders.get(0);
            FName.setText(one.getfName());
            LName.setText(one.getlName());
            String myType = one.getType();
            int index = dataAdapter.getPosition(myType);
            Type.setSelection(index);
            NoOfBars.setText(String.valueOf(one.getNoOfBars()));

            if(one.isShippingExpedited()) {
                ShippingExp.setChecked(true);
            }
            else{
                ShippingNor.setChecked(true);
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
