package com.itv.shoppping.basket;

import java.util.List;

import com.itv.shoppping.model.ItemEnum;

import rx.Observable;

public class BasketPriceCalculator {

	private Observable<String> basketObservable;
	private Observable<Integer> totalPriceObservable;
	private Observable<Integer> totalPromotionalPriceObservable;

	public BasketPriceCalculator(List<String> basketItems) {
		basketObservable = Observable.from(basketItems);
		totalPriceObservable = Observable.merge(getItemAPrice(), getItemBPrice(), getItemCPrice(), getItemDPrice())
				.reduce(0, Integer::sum);

		totalPromotionalPriceObservable = Observable
				.merge(getPromotionalItemAPrice(), getPromotionalItemBPrice(), getItemCPrice(), getItemDPrice())
				.reduce(0, Integer::sum);

	}

	public Observable<? extends Object> totalActualPrice() {
		return totalPriceObservable;
	}

	public Observable<? extends Object> totalPromotionalPrice() {
		return totalPromotionalPriceObservable;
	}

	private Observable<Integer> getItemAPrice() {
		return basketObservable.filter(com.itv.shoppping.model.ItemEnum.ITEM_A.name()::equals)
				.map((eachOffer) -> ItemEnum.ITEM_A.getPrice());
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

	/**
	 * 2 for 45 offer
	 * @return price
	 */
	private Observable<Integer> getPromotionalItemBPrice() {
		return basketObservable.filter(ItemEnum.ITEM_B.name()::equals).buffer(2)
				.map((eachOffer) -> ItemEnum.ITEM_B.getPrice() + 15);
	}

	/**
	 * 3 for 130 offer
	 * @return price
	 */
	private Observable<Integer> getPromotionalItemAPrice() {
		return basketObservable.filter(ItemEnum.ITEM_A.name()::equals).buffer(3)
				.map((eachOffer) -> eachOffer.size() == 3 ? ItemEnum.ITEM_A.getPrice() * 2 + 30
						: ItemEnum.ITEM_A.getPrice() * eachOffer.size());
	}

}
