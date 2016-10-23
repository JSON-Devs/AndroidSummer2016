package jasongagnon.androidlab1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText FName, LName, NoOfBars;
    private RadioButton ShippingExp, ShippingNor;
    private Spinner Type;
    private TextView Feedback;
    private ArrayList<Object> candyOrders = new ArrayList<Object>();


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

            Feedback.setText("Order Added, there are now " + candyOrders.size() + " orders");

        }
    };


}
