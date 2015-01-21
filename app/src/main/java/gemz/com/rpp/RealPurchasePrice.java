package gemz.com.rpp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

import gemz.com.rpp.Data.ProfileDB;
import gemz.com.rpp.Data.ProfileData;


public class RealPurchasePrice extends Activity {

    //ProfileService mProfileService;
    //boolean mBound = false;
    ProfileDB pdb;
    private int profileID = -1;
    ArrayList<ProfileData> mProfileArray = new ArrayList<ProfileData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_purchase_price);
        //Intent intent = new Intent(this, ProfileService.class);
        //startService(intent);
        //bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Gson gson = new Gson();
        String strTemp = getIntent().getStringExtra("profileData");

        if (strTemp != null && strTemp.length() > 0) {

            ProfileData pdTemp = gson.fromJson(strTemp, ProfileData.class);
            profileID = pdTemp.getProfileID();
        }

    }



    protected void onStart(){
        super.onStart();
        pdb = new ProfileDB(this);

        loadProfiles();
    }

    protected void onStop(){
        super.onStop();

        pdb.close();
        pdb = null;

        /*
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.real_purchase_price, menu);
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

    public boolean loadProfiles()
    {

        mProfileArray = pdb.loadProfiles();

        //String[] radioButtons = pdb.getProfileNames();
        RadioGroup rg = (RadioGroup)findViewById(R.id.profile_group);

        ProfileData temp;
        if (mProfileArray != null) {
            RadioButton[] rbz = new RadioButton[mProfileArray.size()];
            for (int i = 0; i < mProfileArray.size(); i++)
            {
                temp = mProfileArray.get(i);
                rbz[i] = new RadioButton(this);
                rbz[i].setText(temp.getName());
                rbz[i].setId(temp.getProfileID());
                if (profileID == temp.getProfileID())
                {
                    rbz[i].setChecked(true);
                }

                rg.addView(rbz[i]);
            }

        }



        RadioButton rb1 = new RadioButton(this);
        rb1.setText("New Profile");

        rg.addView(rb1);
        rb1.setId(-1);



        return true;
    }


    /** Defines callbacks for service binding, passed to bindService() */

    /*
    private ServiceConnection mConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ProfileService.LocalBinder binder = (ProfileService.LocalBinder) service;
            mProfileService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
     */


    public void select_profile(View view)
    {
        Gson gson = new Gson();
        Intent i = new Intent(this, ProfileCreation.class);
        RadioGroup rg = (RadioGroup)findViewById(R.id.profile_group);

        int selected = rg.getCheckedRadioButtonId();

        ProfileData tempProfile = new ProfileData();
        tempProfile.setProfileID(selected);
        if (selected > 0)
        {
            tempProfile = mProfileArray.get(selected - 1);
        }
        pdb.close();

        if (tempProfile != null)
        i.putExtra("profileData", gson.toJson(tempProfile));
        //i.putExtra("profileID", gson.toJson(selected));
        startActivity(i);
    }
}
