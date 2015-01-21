package gemz.com.rpp.Data;

/**
 * Created by Eamun on 7/21/2014.
 */
public class ProfileData {

    //region InternalData
    private String name;
    private int profileID;
    private int age;
    private int zipcode;
    private int annMiles;

   //endregion

    //region GettersAndSetters
    public String getName() {
        return name;
    }

    public int getProfileID() {
        return profileID;
    }

    public int getAge() {
        return age;
    }

    public int getZipcode() {
        return zipcode;
    }

    public int getAnnMiles() {
        return annMiles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public void setAnnMiles(int annMiles) {
        this.annMiles = annMiles;
    }

    //endregion

    //region Constructors
    public ProfileData(String name, int profileID, int age, int zipcode, int annMiles)
    {
        this.name = name;
        this.profileID = profileID;
        this.age = age;
        this.zipcode = zipcode;
        this.annMiles = annMiles;

    }

    public ProfileData(){}

    //endregion
}
