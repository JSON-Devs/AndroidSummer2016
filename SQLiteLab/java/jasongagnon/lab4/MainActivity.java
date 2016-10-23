package jasongagnon.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    private EditText FName, LName, NoOfBars, Price, IDSearch;
    private RadioButton ShippingExp, ShippingNor;
    private Spinner Type;
    private TextView Feedback;
    private Button ButtonSave;
    //private ArrayList<Object> candyOrders = new ArrayList<Object>();
    private List<String> candyList = new ArrayList<String>();
    private ArrayAdapter<String> dataAdapter;
    public static int REQUEST_CODE = 10;
    private String fName, lName, type;
    private int noOfBars;
    private boolean isExp;
    private float price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FName = (EditText)findViewById(R.id.txtFName);
        LName = (EditText)findViewById(R.id.txtLName);
        NoOfBars = (EditText)findViewById(R.id.txtNoOfBars);
        Price = (EditText)findViewById(R.id.txtPrice);
        IDSearch = (EditText)findViewById(R.id.txtOrderID);

        Feedback = (TextView)findViewById(R.id.txtFeedback);

        ShippingExp = (RadioButton)findViewById(R.id.rbEx);
        ShippingNor = (RadioButton)findViewById(R.id.rbN);

        Type = (Spinner)findViewById(R.id.spinner);

        ButtonSave = (Button)findViewById(R.id.btnSave);

        Feedback.setText("Total Orders: " + db.getOrdersCount());

        candyList.add("Milk Chocolate");
        candyList.add("Dark Chocolate");
        candyList.add("White Chocolate");


        dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,candyList);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        Type.setAdapter(dataAdapter);

        //Button saveOrder = (Button)findViewById(R.id.btnSave);
        //saveOrder.setOnClickListener(saveBtn);


    }

    private void createOrder(){

        fName = String.valueOf(FName.getText().toString());
        lName = String.valueOf(LName.getText().toString());
        noOfBars = Integer.valueOf(NoOfBars.getText().toString());
        type = Type.getSelectedItem().toString();
        price = Float.valueOf(Price.getText().toString());

        if (ShippingExp.isChecked()){
            isExp = true;
        }
        else {
            isExp = false;
        }

    }

    public void idSearch(View view){
        Order idOrder = db.idSearch(Integer.valueOf(IDSearch.getText().toString()));
        FName.setText(idOrder.getfName());
        LName.setText(idOrder.getlName());
        String myType = idOrder.getType();
        int index = dataAdapter.getPosition(myType);
        Type.setSelection(index);
        NoOfBars.setText(String.valueOf(idOrder.getNoOfBars()));
        Price.setText(String.valueOf(idOrder.getPrice()));
        if(idOrder.isShippingExpedited()){
            ShippingExp.setChecked(true);
        }
        else{
            ShippingNor.setChecked(true);
        }
    }

    public void saveOrder(View view){
        createOrder();
        Order order = new  Order(fName, lName, type, noOfBars, isExp, price);
        //db.addOrder(order);
        Intent i =  new Intent(this, results.class);
        i.putExtra("order", order);
        startActivityForResult(i, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Feedback.setText("Total Orders: " + db.getOrdersCount());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
