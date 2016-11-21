package com.itv.shoppping.basket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.itv.shoppping.model.ItemEnum;

import rx.observables.BlockingObservable;

public class BasketPriceCalculatorTest {

	@Test
	public void testActualPriceOfEmptyItemsInBasket() {
		List<String> basket = Arrays.asList();
		BasketPriceCalculator calc = new BasketPriceCalculator(basket);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(0));
	}

	@Test
	public void testActualPriceOfBasketFullOfItemA() {
		List<String> basketOfApples = Arrays.asList(ItemEnum.ITEM_A.name(), ItemEnum.ITEM_A.name());
		BasketPriceCalculator calc = new BasketPriceCalculator(basketOfApples);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(100));
	}

	@Test
	public void testActualPriceOfBasketFullOfItemB() {
		List<String> basketOfOranges = Arrays.asList(ItemEnum.ITEM_B.name(), ItemEnum.ITEM_B.name());
		BasketPriceCalculator calc = new BasketPriceCalculator(basketOfOranges);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(60));
	}

	@Test
	public void testActualPriceOfBasketOfMultipleItems() {
		List<String> basket = Arrays.asList(ItemEnum.ITEM_A.name(), ItemEnum.ITEM_D.name(), ItemEnum.ITEM_C.name(),
				ItemEnum.ITEM_B.name());
		BasketPriceCalculator calc = new BasketPriceCalculator(basket);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(115));
	}

	@Test
	public void testActualPriceOfBasketOfMultipleMixedItems() {
		List<String> basket = Arrays.asList(ItemEnum.ITEM_A.name(), ItemEnum.ITEM_D.name(), ItemEnum.ITEM_C.name(),
				ItemEnum.ITEM_D.name(), ItemEnum.ITEM_B.name());
		BasketPriceCalculator calc = new BasketPriceCalculator(basket);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(130));
	}

	@Test
	public void testPromotionalPriceOfBasketFullOfItemA() {
		List<String> basketOfApples = Arrays.asList(ItemEnum.ITEM_A.name(), ItemEnum.ITEM_A.name(),
				ItemEnum.ITEM_A.name());
		BasketPriceCalculator calc = new BasketPriceCalculator(basketOfApples);
		assertThat(BlockingObservable.from(calc.totalPromotionalPrice()).last(), is(130));
	}

	@Test
	public void testPromotionalPriceOfBasketFullOfItemB() {
		List<String> basketOfOranges = Arrays.asList(ItemEnum.ITEM_B.name(), ItemEnum.ITEM_B.name(),
				ItemEnum.ITEM_B.name());
		BasketPriceCalculator calc = new BasketPriceCalculator(basketOfOranges);
		assertThat(BlockingObservable.from(calc.totalPromotionalPrice()).last(), is(90));
	}
	
	@Test
	public void testPromotionalPriceOfBasketFullOfItems() {
		List<String> basketOfOranges = Arrays.asList(ItemEnum.ITEM_B.name(), ItemEnum.ITEM_B.name(),
				ItemEnum.ITEM_B.name(), ItemEnum.ITEM_A.name(), ItemEnum.ITEM_A.name(),
				ItemEnum.ITEM_A.name(), ItemEnum.ITEM_C.name(), ItemEnum.ITEM_D.name(),
				ItemEnum.ITEM_D.name());
		BasketPriceCalculator calc = new BasketPriceCalculator(basketOfOranges);
		assertThat(BlockingObservable.from(calc.totalPromotionalPrice()).last(), is(270));
	}
}