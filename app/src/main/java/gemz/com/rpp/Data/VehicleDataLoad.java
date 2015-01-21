package gemz.com.rpp.Data;

/**
 * Created by Eamun on 7/30/2014.
 * DataLoader service to initialize some makes, models, etc
 */
public class VehicleDataLoad {

    //YEARS
    private static final int year2014 = 2014;
    private static final int year2013 = 2013;

    //MAKES
    private static String MAKE_FORD = "Ford";
    private static String MAKE_TOYOTA = "Toyota";
    private static String MAKE_HONDA = "Honda";


    public static VehicleData[] getFords()
    {
        VehicleData[] fords = new VehicleData[4];

        fords[0] = new VehicleData(year2014, MAKE_FORD, "Fiesta", "FWD", 24000, "AA", 31, "AB");
        fords[1] = new VehicleData(year2014, MAKE_FORD, "Fiesta", "ST FWD", 24000, "AA", 29, "AB");
        fords[2] = new VehicleData(year2014, MAKE_FORD, "Fusion", "FWD", 24000, "BA", 28, "AB");
        fords[3] = new VehicleData(year2014, MAKE_FORD, "Fusion", "Hybrid FWD", 24000, "BA", 42, "BB");
        //fords[4] = new VehicleData(year2014, MAKE_FORD, "Fiesta", "Trim A", 24000, "AA", 34.2, "AB");

        return fords;

    }

    public static VehicleData[] getToyotas()
    {
        VehicleData[] toyotas = new VehicleData[4];
        toyotas[0] = new VehicleData(year2014, MAKE_TOYOTA, "Corolla L", "6-Speed Manual", 16800, "AA", 31, "AB");
        toyotas[1] = new VehicleData(year2014, MAKE_TOYOTA, "Corolla L", "4-Speed Automatic", 17400, "AA", 31, "AB");
        //toyotas[2] = new VehicleData(year2014, MAKE_TOYOTA, "Corolla LE", "CVT", 18300, "AA", 32, "AB");
        //toyotas[3] = new VehicleData(year2014, MAKE_TOYOTA, "Corolla LE", "CVT Plus", 18700, "AA", 32, "AB");
        //toyotas[4] = new VehicleData(year2014, MAKE_TOYOTA, "Corolla LE", "CVT Premium", 19400, "AA", 32, "AB");
        toyotas[2] = new VehicleData(year2014, MAKE_TOYOTA, "Corolla S", "CVT", 19000, "AA", 32, "AB");
        toyotas[3] = new VehicleData(year2014, MAKE_TOYOTA, "Corolla S", "6-Speed Manual", 21300, "AA", 31, "AB");

        return toyotas;

    }


    public static VehicleData[] getHondas()
    {
        VehicleData[] hondas = new VehicleData[2];



        return hondas;
    }



}
