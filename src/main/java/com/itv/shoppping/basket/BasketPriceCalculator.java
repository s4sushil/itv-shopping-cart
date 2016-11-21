package com.itv.shoppping.basket;

import java.util.List;

import rx.Observable;

public class BasketPriceCalculator {

	private Observable<Double> totalPriceObservable;
	
	public BasketPriceCalculator(List<String> basket) {
		// TODO Auto-generated constructor stub
	}

	public Observable<? extends Object> totalActualPrice() {
		return totalPriceObservable;
	}

}
