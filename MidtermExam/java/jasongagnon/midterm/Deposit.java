package jasongagnon.midterm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import jasongagnon.midterm.R;

public class Deposit extends AppCompatActivity {

    public static float balance = 0;

    TextView Type, Trans, Balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Bundle data = getIntent().getExtras();
        String type = data.getString("type");
        int bal = data.getInt("bal");

        if(type.equalsIgnoreCase("withdrawl")){
            balance = balance - bal;
        }
        else if(type.equalsIgnoreCase("deposit")){
            balance = balance + bal;
        }

        Type = (TextView)findViewById(R.id.txtTypeTrans);
        Trans = (TextView)findViewById(R.id.txtTrans);
        Balance = (TextView)findViewById(R.id.txtBalance);

        Balance.setText(String.valueOf(balance));
        Type.setText(type);
        Trans.setText(String.valueOf(bal));
    }

    public void backToMain(View view){
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("returnbal", balance);
        setResult(RESULT_OK,intent);
        super.finish();
    }

}
