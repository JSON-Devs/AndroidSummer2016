package jasongagnon.fragmentlab;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OrderFragment.OnFragmentInteractionListener, CandyOrderFragment.OnListFragmentInteractionListener{

    public static ArrayList<Order> CandyOrders = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //CandyOrders.add(new Order("Jason", "Gagnon", "Milk Chocolate", 5, true));
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

    @Override
    public void onFragmentInteraction(Order order) {
        OrderFragment orderFragment = (OrderFragment) getSupportFragmentManager().findFragmentById(R.id.orderFragment);
        orderFragment.LoadListAgain(order);
    }

    @Override
    public void onListFragmentInteraction(Order order) {

        OrderFragment orderFragment = (OrderFragment) getSupportFragmentManager().findFragmentById(R.id.orderFragment);
        orderFragment.LoadFragmentData(order);
    }
}
