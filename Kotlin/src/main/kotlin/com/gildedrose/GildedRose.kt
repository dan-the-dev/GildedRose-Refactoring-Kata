package com.gildedrose

class GildedRose(var items: Array<Item>) {
    val AGED_BRIE = "Aged Brie"
    val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
    val SULFURAS = "Sulfuras, Hand of Ragnaros"

    fun updateQuality() {
        items.map {
            handleQuality(it)
            handleSellInDays(it)

            if (it.sellInDaysAreNegative()) {
                handleNegativeDaysQuality(it)
            }
        }
    }

    private fun handleQuality(it: Item) {
        if (isSpecialItem(it)) {
            increaseSpecialItemsQuality(it)
        } else {
            it.decreaseQuality()
        }
    }

    private fun handleSellInDays(it: Item) {
        if (!it.nameIsEqualsTo(SULFURAS)) {
            it.decreaseSellIn()
        }
    }

    private fun handleNegativeDaysQuality(item: Item) {
        if (item.nameIsEqualsTo(AGED_BRIE)) {
            item.increaseQuality()
            return
        }
        if (item.nameIsEqualsTo(BACKSTAGE_PASS)) {
            item.qualityToLower()
            return
        }
        if (item.nameIsEqualsTo(SULFURAS)) {
            return
        }

        item.decreaseQuality()
    }

    private fun increaseSpecialItemsQuality(item: Item) {
        item.increaseQuality()

        if (item.nameIsEqualsTo(BACKSTAGE_PASS)) {
            handleBackstagePassLatesDaysQualityIncrease(item)
        }
    }

    private fun handleBackstagePassLatesDaysQualityIncrease(item: Item) {
        if (item.sellInDaysAreLowerThan(11)) {
            item.increaseQuality()

            if (item.sellInDaysAreLowerThan(6)) {
                item.increaseQuality()
            }
        }
    }

    private fun isSpecialItem(item: Item) =
            item.nameIsEqualsTo(AGED_BRIE) || item.nameIsEqualsTo(BACKSTAGE_PASS) || item.nameIsEqualsTo(SULFURAS)
}


