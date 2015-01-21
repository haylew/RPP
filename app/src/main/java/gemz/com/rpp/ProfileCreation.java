package gemz.com.rpp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;

import gemz.com.rpp.Data.ProfileDB;
import gemz.com.rpp.Data.ProfileData;

public class ProfileCreation extends Activity {

    ProfileData mProfileData;

    int mProfileID;
    ProfileDB pDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);
        Gson gson = new Gson();
        mProfileData = new ProfileData();
        String tempProfileData = getIntent().getStringExtra("profileData");

        mProfileData = gson.fromJson(tempProfileData, ProfileData.class);
    }

    public void onStart()
    {
        super.onStart();

        pDB = new ProfileDB(this);

        if (mProfileData.getProfileID() != -1)
        {
            setFields();
        }

    }

    public void onStop()
    {
        super.onStop();
        pDB.close();
    }

    public void setFields()
    {
        //ArrayList<ProfileData> al = pDB.getProfileData(mProfileData.getProfileID());

        //boolean found = false;

        /*
        for (int i = 0; i < al.size(); i++)

        {
            ProfileData temp = al.get(i);
            if (temp.getProfileID() == mProfileData.getProfileID())
            {
                mProfileData = temp;
                found = true;
            }
        }
        */

        //ProfileData tempData = pDB.getProfileData(mProfileData.getProfileID());
        if (mProfileData.getProfileID() > 0)
        {
            //mProfileData = tempData;

            EditText nameText = (EditText)findViewById(R.id.nameInput);
            nameText.setText(mProfileData.getName());

            EditText ageText = (EditText)findViewById(R.id.ageInput);
            ageText.setText(String.valueOf(mProfileData.getAge()));

            EditText zipText = (EditText)findViewById(R.id.zipcodeInput);
            zipText.setText(String.valueOf(mProfileData.getZipcode()));

            EditText milesText = (EditText)findViewById(R.id.annMileInput);
            milesText.setText(String.valueOf(mProfileData.getAnnMiles()));

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_creation, menu);
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

    public void create_profile(View view)
    {
        Gson gson = new Gson();
        Intent i = new Intent(this, VehicleSelect.class);

       // int i2;

        EditText nameText = (EditText)findViewById(R.id.nameInput);
        mProfileData.setName(nameText.getText().toString());

        EditText ageText = (EditText)findViewById(R.id.ageInput);
        //i2 = Integer.parseInt(ageText.getText().toString());
        mProfileData.setAge(Integer.parseInt(ageText.getText().toString()));

        EditText zipText = (EditText)findViewById(R.id.zipcodeInput);
        mProfileData.setZipcode(Integer.parseInt(zipText.getText().toString()));

        EditText milesText = (EditText)findViewById(R.id.annMileInput);
        mProfileData.setAnnMiles(Integer.parseInt(milesText.getText().toString()));

        if (mProfileData.getProfileID() == -1 )
        {
            mProfileID = pDB.insertProfile(mProfileData);
            if (mProfileID > 0)
            {
                mProfileData.setProfileID(mProfileID);
            }
        }

        String sendProfile = gson.toJson(mProfileData);
        i.putExtra("profileData", sendProfile);

        startActivity(i);


    }


}
