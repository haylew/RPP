package gemz.com.rpp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import gemz.com.rpp.Data.ProfileData;
import gemz.com.rpp.Data.VehicleDB;
import gemz.com.rpp.Data.VehicleData;
//import gemz.com.rpp.R;

public class VehicleSelect extends Activity {

    private VehicleDB vDB;
    //private int posYear, posMake, posModel, posTrim;
    //private int profileID;
    private ProfileData profileData;
    private VehicleData vehicleData = new VehicleData();

    //ArrayList<String> listYears = new ArrayList<String>();
    //ArrayList<String> listMakes = new ArrayList<String>();
    //ArrayList<String> listModels = new ArrayList<String>();
    //ArrayList<String> listTrims = new ArrayList<String>();

    ArrayAdapter<String> adapterYear;
    ArrayAdapter<String> adapterMake;
    ArrayAdapter<String> adapterModel;
    ArrayAdapter<String> adapterTrim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_select);
        Gson gson = new Gson();

        String tempProfile = getIntent().getStringExtra("profileData");

        profileData = gson.fromJson(tempProfile, ProfileData.class );

        setListeners();
    }


    private void setListeners()
    {
        final ListView ly = (ListView)findViewById(R.id.listYear);
        ly.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //posYear = position;
                for(int a = 0; a < parent.getChildCount(); a++)
                {
                    parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                    TextView otherItem = (TextView)parent.getChildAt(a);
                    otherItem.setTextColor(Color.BLACK);
                }

                view.setBackgroundColor(Color.BLUE);

                if (adapterMake != null)
                    adapterMake.clear();
                if (adapterModel != null)
                    adapterModel.clear();
                if (adapterTrim != null)
                    adapterTrim.clear();

                TextView tv = (TextView)parent.getChildAt(position);
                tv.setTextColor(Color.WHITE);

                TextView modelText = (TextView)findViewById(R.id.textModel);
                modelText.setVisibility(View.INVISIBLE);

                TextView trimText = (TextView)findViewById(R.id.textTrim);
                trimText.setVisibility(View.INVISIBLE);

                Object oYear = ly.getItemAtPosition(position);

                Button selectButton = (Button)findViewById(R.id.select);
                selectButton.setVisibility(View.INVISIBLE);
                selectButton.setEnabled(false);

                String stYear = (String)oYear;
                vehicleData.setYear(Integer.parseInt(stYear));
                loadMakes(Integer.parseInt(stYear));
            }
        });

        final ListView lma = (ListView)findViewById(R.id.listMake);
        lma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //posMake = position;

                for(int a = 0; a < parent.getChildCount(); a++)
                {
                    parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                    TextView otherItem = (TextView)parent.getChildAt(a);
                    otherItem.setTextColor(Color.BLACK);
                }

                view.setBackgroundColor(Color.BLUE);

                if (adapterModel != null)
                    adapterModel.clear();
                if (adapterTrim != null)
                    adapterTrim.clear();

                TextView tv = (TextView)parent.getChildAt(position);
                tv.setTextColor(Color.WHITE);

                Object oMake = lma.getItemAtPosition(position);
                String stMake = (String)oMake;

                vehicleData.setMake(stMake);

                loadModels(vehicleData.getYear(), stMake);

                Button selectButton = (Button)findViewById(R.id.select);
                selectButton.setVisibility(View.INVISIBLE);
                selectButton.setEnabled(false);

                TextView trimText = (TextView)findViewById(R.id.textTrim);
                trimText.setVisibility(View.INVISIBLE);

            }
        });

        final ListView lmo = (ListView)findViewById(R.id.listModel);
        lmo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //posModel = position;
                for(int a = 0; a < parent.getChildCount(); a++)
                {
                    parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                    TextView otherItem = (TextView)parent.getChildAt(a);
                    otherItem.setTextColor(Color.BLACK);
                }


                if (adapterTrim != null)
                    adapterTrim.clear();

                view.setBackgroundColor(Color.BLUE);
                TextView tv = (TextView)parent.getChildAt(position);
                tv.setTextColor(Color.WHITE);




                Object oModel = lmo.getItemAtPosition(position);
                String stModel = (String)oModel;

                vehicleData.setModel(stModel);

                loadTrims(vehicleData.getYear(), vehicleData.getMake(), stModel);

                Button selectButton = (Button)findViewById(R.id.select);
                selectButton.setVisibility(View.INVISIBLE);
                selectButton.setEnabled(false);

            }
        });

        final ListView lt = (ListView)findViewById(R.id.listTrim);
        lt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                for(int a = 0; a < parent.getChildCount(); a++)
                {
                    parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                    TextView otherItem = (TextView)parent.getChildAt(a);
                    otherItem.setTextColor(Color.BLACK);
                }

                view.setBackgroundColor(Color.BLUE);
                TextView tv = (TextView)parent.getChildAt(position);
                tv.setTextColor(Color.WHITE);


                Object oTrim = lt.getItemAtPosition(position);
                String stTrim = (String)oTrim;

                vehicleData.setTrim(stTrim);

                Button selectButton = (Button)findViewById(R.id.select);
                selectButton.setVisibility(View.VISIBLE);
                selectButton.setEnabled(true);

            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vehicle_select, menu);
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

    protected void onStart(){
        super.onStart();

        vDB = new VehicleDB(this);

        loadYears();


    }

    protected void onStop(){
        super.onStop();

        vDB.close();
        vDB = null;


    }

    private void loadYears()
    {
        ListView lv = (ListView)findViewById(R.id.listYear);
        if (adapterYear == null) {
            adapterYear = new ArrayAdapter<String>(this, R.layout.vehicle_items);

        }
        lv.setAdapter(adapterYear);

        ArrayList<String> temp = vDB.loadYears();

        for (int i = 0; i < temp.size(); i ++)
        {
            adapterYear.add(temp.get(i));
        }

    }

    private void loadMakes(int year)
    {
        ListView lv = (ListView)findViewById(R.id.listMake);
        if (adapterMake == null) {
            adapterMake = new ArrayAdapter<String>(this, R.layout.vehicle_items);
        }
        lv.setAdapter(adapterMake);

        ArrayList<String> temp = vDB.loadMakes(year);

        for (int i = 0; i < temp.size(); i++)
        {
            adapterMake.add(temp.get(i));
        }

        TextView makes = (TextView)findViewById(R.id.textMake);
        makes.setVisibility(View.VISIBLE);


    }

    private void loadModels(int year, String model)
    {

        ListView lv = (ListView)findViewById(R.id.listModel);
        if (adapterModel == null) {
            adapterModel = new ArrayAdapter<String>(this, R.layout.vehicle_items);
        }
        lv.setAdapter(adapterModel);

        ArrayList<String> temp = vDB.loadModels(year, model);

        for (int i = 0; i < temp.size(); i++)
        {
            adapterModel.add(temp.get(i));
        }

        TextView models = (TextView)findViewById(R.id.textModel);
        models.setVisibility(View.VISIBLE);

    }

    private void loadTrims(int year, String make, String model)
    {

        ListView lv = (ListView)findViewById(R.id.listTrim);
        if (adapterTrim == null)
        {
            adapterTrim = new ArrayAdapter<String>(this, R.layout.vehicle_items);
        }

        ArrayList<String> temp = vDB.loadTrims(year, make, model);

        for (int i = 0; i < temp.size(); i++)
        {
            adapterTrim.add(temp.get(i));
        }

        lv.setAdapter(adapterTrim);

        TextView trims = (TextView)findViewById(R.id.textTrim);
        trims.setVisibility(View.VISIBLE);


    }

    public void select_vehicle(View view)
    {
        Gson gson = new Gson();
        VehicleData sendingVehicle = vDB.getVehicle(vehicleData.getYear(), vehicleData.getMake(),
                vehicleData.getModel(), vehicleData.getTrim());

        Intent i = new Intent(this, ResultDisplay.class);

        i.putExtra("profileData", gson.toJson(profileData));
        i.putExtra("vehicleData", gson.toJson(sendingVehicle));


        startActivity(i);

    }


}
