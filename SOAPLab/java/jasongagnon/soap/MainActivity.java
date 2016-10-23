package jasongagnon.soap;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    Button GetWeather;
    EditText Zip;
    TextView result;

    private static final String SOAP_ACTION = "http://ws.cdyne.com/WeatherWS/GetCityWeatherByZIP";
    private static final String METHOD_NAME = "GetCityWeatherByZIP";
    private static final String NAMESPACE = "http://ws.cdyne.com/WeatherWS/";
    private static final String URL = "http://wsf.cdyne.com/WeatherWS/Weather.asmx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Zip = (EditText) findViewById(R.id.txtZip);
        result = (TextView) findViewById(R.id.result);
        GetWeather = (Button) findViewById(R.id.GetWeather);
        GetWeather.setOnClickListener(GetWeatherClickListener);
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

    private class WebOperation  extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String resultOfCall = "test";
            try {

                String requestedZip;
                //String requestedCountry;

                requestedZip = params[0];
                //requestedCountry=  params[1];
                // This won't work because you need to pull components from the main thread
                //resultOfCall = GetWeatherInfo(City.getText().toString(), Country.getText().toString());
                resultOfCall = GetWeatherInfo(requestedZip);
            } catch (IOException e) {
                Log.e("Allied Error", "Foo didn't work: " + e.getMessage());
                Log.e("Allied Error2", "Full stack track:" + Log.getStackTraceString(e));
            } catch (XmlPullParserException e) {
                Log.e("Allied Error", "Foo didn't work: " + e.getMessage());
                Log.e("Allied Error2", "Full stack track:" + Log.getStackTraceString(e));
            }
            //    result.setText(resultOfCall);

            return resultOfCall;
        }
        protected void onPostExecute(String ResultOfCall) {
            result.setText(ResultOfCall);
        }
    }

    private View.OnClickListener GetWeatherClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            // Use AsyncTask execute Method To Prevent ANR Problem
            String requestedZip;
            //String requestedCountry;
            requestedZip = Zip.getText().toString();
            //requestedCountry = Country.getText().toString();
            new WebOperation().execute(requestedZip);

        }
    };

    private String GetWeatherInfo(String Zip) throws IOException, XmlPullParserException{


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);




        PropertyInfo ZipProp = new PropertyInfo();
        ZipProp.type = PropertyInfo.STRING_CLASS;
        ZipProp.name = "ZIP";
        ZipProp.setNamespace(NAMESPACE);
        ZipProp.setValue(Zip);
        request.addProperty(ZipProp);

        //PropertyInfo CountryProp = new PropertyInfo();
        //CountryProp.type = PropertyInfo.STRING_CLASS;
        //CountryProp.name = "CountryName";
        //CountryProp.setNamespace(NAMESPACE);
        //CountryProp.setValue(Country);
        //request.addProperty(CountryProp);

        // this works too
//		request.addProperty("CityName",City);
//		request.addProperty("CountryName", Country);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht;
        System.out.println(envelope.bodyOut.toString());
        ht = new HttpTransportSE(URL);

        ht.debug = true;
        ht.call(SOAP_ACTION, envelope);

        SoapObject response = (SoapObject)envelope.getResponse();
        //final  SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
        SoapPrimitive temp = (SoapPrimitive)response.getProperty(7);
        SoapPrimitive city = (SoapPrimitive)response.getProperty(3);
        String str = "Temp: " + temp + "\nCity: " + city;

        return str;
    }
}
