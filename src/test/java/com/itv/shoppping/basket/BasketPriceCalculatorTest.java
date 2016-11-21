package com.itv.shoppping.basket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import rx.observables.BlockingObservable;

public class BasketPriceCalculatorTest {

	@Test
	public void testActualPriceOfEmptyItemsInBasket() {
		List<String> basket = Arrays.asList();
		BasketPriceCalculator calc = new BasketPriceCalculator(basket);
		assertNotNull(BlockingObservable.from(calc.totalActualPrice()));
	}

}