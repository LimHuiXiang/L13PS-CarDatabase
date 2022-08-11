package com.example.cardatabase;

public class Car {
    private String year;
    private String ccrating;
    private String number;

    public Car(String year, String ccrating, String number) {
        this.year = year;
        this.ccrating = ccrating;
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCcrating() {
        return ccrating;
    }

    public void setCcrating(String ccrating) {
        this.ccrating = ccrating;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CAR" + "\n" +
                "CC_rating: '" + ccrating + "\n" +
                "Number: " + number + "\n" +
                "Year:" + year;
    }
}
