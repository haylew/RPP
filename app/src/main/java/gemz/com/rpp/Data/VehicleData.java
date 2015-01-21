package gemz.com.rpp.Data;

/**
 * Created by Eamun on 7/21/2014.
 */
public class VehicleData {

    //region InternalData
    private String make;
    private String model;
    private int year;
    private String trim;
    private double msrp;
    private int vehicleID;
    private String insuranceFactor;
    private double mpg;
    private String maintenanceFactor;
    //endregion

    //region GettersAndSetters
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getInsuranceFactor() {
        return insuranceFactor;
    }

    public void setInsuranceFactor(String insuranceFactor) {
        this.insuranceFactor = insuranceFactor;
    }

    public double getMpg() {
        return mpg;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    public String getMaintenanceFactor() {
        return maintenanceFactor;
    }

    public void setMaintenanceFactor(String maintenanceFactor) {
        this.maintenanceFactor = maintenanceFactor;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }
    //endregion

    //region Constructors
    public VehicleData()
    {}

    public VehicleData(int vehicleID, int year, String make, String model, String trim, double msrp, String insuranceFactor, double mpg, String maintenanceFactor)
    {
        this.vehicleID = vehicleID;
        this.year = year;
        this.make = make;
        this.model = model;
        this.trim = trim;
        this.msrp = msrp;
        this.insuranceFactor = insuranceFactor;
        this.mpg = mpg;
        this.maintenanceFactor = maintenanceFactor;
    }


    public VehicleData(int year, String make, String model, String trim, double msrp, String insuranceFactor, double mpg, String maintenanceFactor)
    {
        this.vehicleID = -1;
        this.year = year;
        this.make = make;
        this.model = model;
        this.trim = trim;
        this.msrp = msrp;
        this.insuranceFactor = insuranceFactor;
        this.mpg = mpg;
        this.maintenanceFactor = maintenanceFactor;
    }

    //endregion

}
