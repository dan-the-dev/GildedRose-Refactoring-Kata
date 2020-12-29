package com.gildedrose

open class Item(var name: String, var sellIn: Int, var quality: Int) {
    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }

    fun nameIsEqualsTo(name: String): Boolean {
        return this.name === name;
    }

    fun sellInDaysAreNegative(): Boolean {
        return this.sellIn < 0;
    }

    fun sellInDaysAreLowerThan(number: Int): Boolean {
        return this.sellIn < number;
    }

    fun increaseQuality() {
        if (this.quality < 50) {
            this.quality++;
        }
    }

    fun decreaseQuality() {
        if (this.quality > 0) {
            this.quality--;
        }
    }

    fun qualityToLower() {
        this.quality = 0
    }

    fun decreaseSellIn() {
        this.sellIn--;
    }
}