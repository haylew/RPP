package gemz.com.rpp.CalculationLibrary;

/**
 * Created by Eamun on 7/28/2014.
 */
public class InsuranceCost {

    public InsuranceCost()
    {

    }

    public double getInsuranceCost(String insuranceClass, int age)
    {
        double agefactor = getAgeFactor(age);

        return (agefactor * 1000 * getCarFactor(insuranceClass));
    }


    private double getAgeFactor(int age)
    {
        if (age < 25)
        {
            return 2.339 * 2;
        }
        else if (age >= 25 && age < 30)
        {
            return 1.2 * 2;
        }
        else if (age >= 30 && age < 39)
        {
            return 1.147 * 2;
        }
        else if (age >= 40 && age < 50)
        {
            return 1.082 * 2;
        }
        else if (age >= 50 && age < 60)
        {
            return 1.032 * 2;
        }
        else if (age >= 60)
        {
            return 1.022 * 2;
        }
        else
        {
            return 0.0;
        }
    }

    private static double getCarFactor(String insClass)
    {
        String temp = insClass.toLowerCase();
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
