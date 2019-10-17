package com.example.heat_index;

public class Weather {
    private double temp;
    private double humdity;
    private double heatIndex;
    private boolean isFahrenheit;

    /**
     *
     * @return returns the value of heatIndex
     */
    public double getHeatIndex(){
        return heatIndex;
    }

    /**
     * Constructs an instance of Weather and calculates the heatIndex
     * @param temp the outside temperature as given by the user
     * @param humidity the relative humidity as given by the user
     * @param isFahrenheit determines whether or not the user used Fahrenheit for the Temperature
     */
    public Weather(double temp, double humidity, boolean isFahrenheit){
        this.humdity = humidity;
        this.temp = temp;
        this.isFahrenheit = isFahrenheit;

        calculate(temp, humidity);
    }


    /**
     * Method that calculates the heat-index in Celsius
     * @param temp actual temperature in Celsius or Fahrenheit
     * @param humidity humidity in percent
     * @return returns the heat-index in °C or °F depending on input
     */
    private void calculate(double temp, double humidity){
        if(!isFahrenheit) {
            //Check zu min und max Temperatur hinzufügen
            heatIndex = -8.784695 +
                    1.61139411 * temp +
                    2.338549 * humidity -
                    0.14611605 * temp * humidity -
                    0.012308094 * Math.pow(temp,2) -
                    0.016424828 * Math.pow(humidity,2) +
                    0.002211732 * Math.pow(temp,2) * humidity +
                    0.00072546 * temp * Math.pow(humidity,2) -
                    0.000003582 * Math.pow(temp,2) * Math.pow(humidity,2);

        } else {
            //check zu min und max temperatur hinzufügen
            heatIndex = -42.379 +
                    2.04901523 * temp +
                    10.14333127 * humidity -
                    0.22475541 * temp * humidity -
                    0.00683783 * Math.pow(temp,2) -
                    0.05481717 * Math.pow(humidity,2) +
                    0.00122874 * Math.pow(temp,2) * humidity +
                    0.00085282 * temp * Math.pow(humidity,2) -
                    0.00000199 * Math.pow(temp,2) * Math.pow(humidity,2);
        }
    }


}