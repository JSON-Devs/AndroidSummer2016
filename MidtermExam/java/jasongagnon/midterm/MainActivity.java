package jasongagnon.midterm;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Number;
    private RadioButton Withdrawl, Deposit, Inquery;
    private TextView Balance;
    public static int REQUEST_CODE = 10;
    private int bal;
    private String transType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Number = (EditText)findViewById(R.id.txtNumber);

        Withdrawl = (RadioButton)findViewById(R.id.rbWith);
        Deposit = (RadioButton)findViewById(R.id.rbDep);
        Inquery = (RadioButton)findViewById(R.id.rbInq);

        Balance = (TextView)findViewById(R.id.tvFeedback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public String transTypeCheck(){
        bal = Integer.valueOf(Number.getText().toString()).intValue();
        if(Withdrawl.isChecked()){
            return "Withdrawl";
        }
        else if(Deposit.isChecked()){
            return "Deposit";
        }
        else {
            bal = 0;
            return "Inquiry";
        }
    }

    public void test(){
        transType = transTypeCheck();
        Intent i =  new Intent(this, jasongagnon.midterm.Deposit.class);
        i.putExtra("type", transType);
        i.putExtra("bal", bal);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_submit) {
            test();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("returnbal")){
                float result = data.getExtras().getFloat("returnbal");
                String bala = String.valueOf(result);
                Toast.makeText(this, bala, Toast.LENGTH_SHORT).show();
                Balance.setText("Current Balance = " + result);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
