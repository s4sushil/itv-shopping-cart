package com.itv.shoppping.basket;

import java.util.List;

import com.itv.shoppping.model.ItemEnum;

import rx.Observable;

public class BasketPriceCalculator {

	private Observable<String> basketObservable;
	private Observable<Integer> totalPriceObservable;

	public BasketPriceCalculator(List<String> basketItems) {
		basketObservable = Observable.from(basketItems);
		totalPriceObservable = Observable.merge(getApplePrice(), getOrangePrice()).reduce(0, Integer::sum);
	}

	public Observable<? extends Object> totalActualPrice() {
		return totalPriceObservable;
	}

	private Observable<Integer> getApplePrice() {
		return basketObservable.filter(com.itv.shoppping.model.ItemEnum.APPLE.name()::equals).map((eachOffer) -> ItemEnum.APPLE.getPrice());
	}

	private Observable<Integer> getOrangePrice() {
		return basketObservable.filter(ItemEnum.ORANGE.name()::equals).map((eachOffer) -> ItemEnum.ORANGE.getPrice());
	}

	private Observable<Integer> getPromotionalApplePrice() {
		return basketObservable.filter(ItemEnum.APPLE.name()::equals).buffer(2).map((eachOffer) -> ItemEnum.APPLE.getPrice());
	}
}
