package gemz.com.rpp.CalculationLibrary;

/**
 * Created by Eamun on 7/28/2014.
 */
public class FuelCost {

    private final double costPerGal = 4.01;

    public FuelCost()
    {}


    public double getFuelCost(double annualMiles, double mpg)
    {
        return annualMiles * 4.01 / mpg;
    }

}
