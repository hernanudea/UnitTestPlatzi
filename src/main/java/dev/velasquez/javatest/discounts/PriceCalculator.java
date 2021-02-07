package dev.velasquez.javatest.discounts;

import java.util.ArrayList;
import java.util.List;

public class PriceCalculator {

    private List<Double> prices = new ArrayList<Double>();
    private double discount = 0;

    public double getTotal() {
        double result = prices.stream().mapToDouble(price -> price).sum();
        return result * ((100 - discount) / 100);
    }

    public void addPrice(double price) {
        prices.add(price);
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
