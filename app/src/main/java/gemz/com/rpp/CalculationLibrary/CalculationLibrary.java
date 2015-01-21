package gemz.com.rpp.CalculationLibrary;

import gemz.com.rpp.Data.ProfileData;
import gemz.com.rpp.Data.VehicleData;

/**
 * Created by Eamun on 7/28/2014.
 */
public class CalculationLibrary {

    private FuelCost mFC = new FuelCost();
    private InsuranceCost mIC = new InsuranceCost();
    private MaintenanceCost mMC = new MaintenanceCost();
    private ProfileData mProfileData;
    private VehicleData mVehicleData;
    private int numYears = 1;

    //private final double gasCost = 4.01;


    public CalculationLibrary(){}

    public CalculationLibrary(ProfileData pd, VehicleData vd)
    {
        mProfileData = pd;
        mVehicleData = vd;
    }

    public void setYears( int years)
    {
        numYears = years;
    }

    public double getFinalCost()
    {

        return (getMSRP() + getFuelCost() + getInsuranceCost() + getMaintenanceCost());
    }

    public double getMSRP()
    {
        return mVehicleData.getMsrp();

    }

    public double getFuelCost()
    {
        return mFC.getFuelCost(mProfileData.getAnnMiles(), mVehicleData.getMpg()) * numYears;
    }

    public double getInsuranceCost()
    {
        return mIC.getInsuranceCost(mVehicleData.getInsuranceFactor(),mProfileData.getAge()) * numYears;
    }

    public double getMaintenanceCost()
    {

        return mMC.getMaintenanceCost(mProfileData.getAnnMiles(), mVehicleData.getMaintenanceFactor()) * numYears;
    }





    


}
