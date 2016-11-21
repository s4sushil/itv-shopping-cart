package com.itv.shoppping.basket;

import java.util.List;

import com.itv.shoppping.model.ItemEnum;

import rx.Observable;

public class BasketPriceCalculator {

	private Observable<String> basketObservable;
	private Observable<Integer> totalPriceObservable;

	public BasketPriceCalculator(List<String> basketItems) {
		basketObservable = Observable.from(basketItems);
		totalPriceObservable = Observable.merge(getItemAPrice(), getItemBPrice(), getItemCPrice(), getItemDPrice()).reduce(0, Integer::sum);
	}

	public Observable<? extends Object> totalActualPrice() {
		return totalPriceObservable;
	}

	private Observable<Integer> getItemAPrice() {
		return basketObservable.filter(com.itv.shoppping.model.ItemEnum.ITEM_A.name()::equals).map((eachOffer) -> ItemEnum.ITEM_A.getPrice());
	}

	private Observable<Integer> getItemBPrice() {
		return basketObservable.filter(ItemEnum.ITEM_B.name()::equals).map((eachOffer) -> ItemEnum.ITEM_B.getPrice());
	}

	private Observable<Integer> getItemCPrice() {
		return basketObservable.filter(ItemEnum.ITEM_C.name()::equals).map((eachOffer) -> ItemEnum.ITEM_C.getPrice());
	}
	
	private Observable<Integer> getItemDPrice() {
		return basketObservable.filter(ItemEnum.ITEM_D.name()::equals).map((eachOffer) -> ItemEnum.ITEM_D.getPrice());
	}
}
