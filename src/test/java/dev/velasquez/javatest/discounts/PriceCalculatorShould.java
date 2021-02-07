package dev.velasquez.javatest.discounts;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class PriceCalculatorShould {

    @Test
    public void totalZeroWhenThereArePrice() {
        PriceCalculator priceCalculator = new PriceCalculator();
        assertThat(priceCalculator.getTotal(), is(0.0));
    }

    @Test
    public void totalIsTheSumOfPrices() {
        PriceCalculator calculator = new PriceCalculator();
        calculator.addPrice(10.2);
        calculator.addPrice(15.5);
        assertThat(calculator.getTotal(), is(25.7));
    }

    @Test
    public void applyDiscountToPrices() {
        PriceCalculator calculator = new PriceCalculator();
        calculator.addPrice(42.8);
        calculator.addPrice(17.2);
        calculator.setDiscount(10);
        assertThat(calculator.getTotal(), is(54.0));
    }
}