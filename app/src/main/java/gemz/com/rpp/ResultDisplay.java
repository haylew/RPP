package gemz.com.rpp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import gemz.com.rpp.CalculationLibrary.CalculationLibrary;
import gemz.com.rpp.Data.ProfileData;
import gemz.com.rpp.Data.VehicleData;

public class ResultDisplay extends Activity {

    CalculationLibrary cl;
    private int year = 1;
    //private final DecimalFormat df = new DecimalFormat("#.##");
    private final NumberFormat nf = NumberFormat.getCurrencyInstance();
    ProfileData pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);

        Gson gson = new Gson();


        String strProfile = getIntent().getStringExtra("profileData");
        String strVehicle = getIntent().getStringExtra("vehicleData");
        //getIntent().getParcelableArrayExtra()
        //VehicleData vd = (VehicleData)gson.fromJson("profileData", VehicleData.class);
        pd = gson.fromJson(strProfile, ProfileData.class);
        cl = new CalculationLibrary(pd, gson.fromJson(strVehicle, VehicleData.class));

        fillTable();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioYearGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbChecked = (RadioButton)findViewById(checkedId);
                switch (rbChecked.getText().charAt(0))
                {
                    case '1':
                        year = 1;
                        fillTable();
                        break;
                    case '3':
                        year = 3;
                        fillTable();
                        break;
                    case '5':
                        year = 5;
                        fillTable();
                        break;
                }

            }
        });

    }

    private void fillTable()
    {
        TextView textMSRP = (TextView)findViewById(R.id.textMSRP);
        TextView textInsurance = (TextView)findViewById(R.id.textInsurance);
        TextView textFuel = (TextView)findViewById(R.id.textFuel);
        TextView textMaintenance = (TextView)findViewById(R.id.textMaintenance);
        TextView textTCO = (TextView)findViewById(R.id.textTotalCost);

        cl.setYears(year);


        textMSRP.setText(nf.format(cl.getMSRP()));
        textInsurance.setText(nf.format(cl.getInsuranceCost()));
        textFuel.setText(nf.format(cl.getFuelCost()));
        textMaintenance.setText(nf.format(cl.getMaintenanceCost()));
        textTCO.setText(nf.format(cl.getFinalCost()));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void select_BackToProfile(View view)
    {
        Gson gson = new Gson();

        Intent i = new Intent(this, RealPurchasePrice.class);

        i.putExtra("profileData", gson.toJson(pd));

        startActivity(i);


    }

    public void select_BackToVehicle(View view)
    {
        Gson gson = new Gson();

        Intent i = new Intent(this, VehicleSelect.class);

        i.putExtra("profileData", gson.toJson(pd));

        startActivity(i);
    }
}
