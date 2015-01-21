package gemz.com.rpp.CalculationLibrary;

/**
 * Created by Eamun on 7/28/2014.
 */
public class MaintenanceCost {

    public MaintenanceCost()
    {}


    public double getMaintenanceCost(int annualMiles, String carFactor)
    {

        //double dblcarFactor = getCarFactor(carFactor);
        //double result = ((double)annualMiles / 12000);

        return ((double)annualMiles / 12000) * 400 * getCarFactor(carFactor);


    }




    private static double getCarFactor(String manClass)
    {
        String temp = manClass.toLowerCase();
        switch (temp.charAt(0)) {
            case 'a': return 1.0;
            case 'b': return 1.05;
            case 'c': return 1.1;
            case 'd': return 1.15;
            case 'e': return 1.20;
            default:    return 1.25;

        }


    }
}
