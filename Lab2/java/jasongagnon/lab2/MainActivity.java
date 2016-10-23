package jasongagnon.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText FName, LName, NoOfBars;
    private RadioButton ShippingExp, ShippingNor;
    private Spinner Type;
    private TextView Feedback;
    private ArrayList<Object> candyOrders = new ArrayList<Object>();
    public static int REQUEST_CODE = 10;
    private String fName, lName, type;
    private int noOfBars;
    private boolean isExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_form);

        FName = (EditText)findViewById(R.id.txtFName);
        LName = (EditText)findViewById(R.id.txtLName);
        NoOfBars = (EditText)findViewById(R.id.txtNoOfBars);

        Feedback = (TextView)findViewById(R.id.txtFeedback);

        ShippingExp = (RadioButton)findViewById(R.id.rbEx);
        ShippingNor = (RadioButton)findViewById(R.id.rbN);

        Type = (Spinner)findViewById(R.id.spinner);

        Button saveOrder = (Button)findViewById(R.id.btnSave);
        saveOrder.setOnClickListener(saveBtn);
    }

    private void createOrder(){

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

    }

    private View.OnClickListener saveBtn = new View.OnClickListener() {
        public void onClick(View v) {
            //goToResults();
            //createOrder();

            //candyOrders.add(candy);

            //Feedback.setText("Order Added, there are now " + candyOrders.size() + " orders");

        }
    };

    public void goToResults(View view){
        createOrder();

        Intent i =  new Intent(this, jasongagnon.lab2.results.class);
        i.putExtra("order", new  Order(fName, lName, type, noOfBars, isExp));
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("returnkey")){
                int result = data.getExtras().getInt("returnkey");
                if (result >= 0) {
                    //Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
                    Feedback.setText("Total Orders: " + result);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
