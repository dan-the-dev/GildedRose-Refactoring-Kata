package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun testSellInDaysAndQualityDecreaseBy1AfterADay() {
        val items = arrayOf<Item>(Item("foo", 10, 50))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 1)

        assertEquals("foo", app.items[0].name)
        assertEquals(9, app.items[0].sellIn)
        assertEquals(49, app.items[0].quality)
    }

    @Test
    fun testSellInDaysAndQualityDecreaseBy3After3Days() {
        val items = arrayOf<Item>(Item("foo", 10, 50))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 3)

        assertEquals("foo", app.items[0].name)
        assertEquals(7, app.items[0].sellIn)
        assertEquals(47, app.items[0].quality)
    }

    @Test
    fun testSellInDaysAndQualityDecreaseBy50After50Days() {
        val items = arrayOf<Item>(Item("foo", 50, 50))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 50)

        assertEquals("foo", app.items[0].name)
        assertEquals(0, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testQualityDontGoNegativeWith10Days() {
        val items = arrayOf<Item>(Item("foo", 10, 5))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 10)

        assertEquals("foo", app.items[0].name)
        assertEquals(0, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testQualityDontGoNegativeWith50Days() {
        val items = arrayOf<Item>(Item("foo", 50, 10))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 40)

        assertEquals("foo", app.items[0].name)
        assertEquals(10, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testQualityDecreaseTwiceFasterWhenSellInDaysAreGone() {
        val items = arrayOf<Item>(Item("foo", 10, 50))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 20)

        assertEquals("foo", app.items[0].name)
        assertEquals(-10, app.items[0].sellIn)
        assertEquals(20, app.items[0].quality)
    }

    @Test
    fun testQualityDecreaseTwiceFasterWhenSellInDaysAreGoneButStillNotLowerThan0() {
        val items = arrayOf<Item>(Item("foo", 10, 20))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 30)

        assertEquals("foo", app.items[0].name)
        assertEquals(-20, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testAgedBrieIncreaseQualityGettingOlder() {
        val items = arrayOf<Item>(Item("Aged Brie", 10, 5))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 5)

        assertEquals("Aged Brie", app.items[0].name)
        assertEquals(5, app.items[0].sellIn)
        assertEquals(10, app.items[0].quality)
    }

    @Test
    fun testAgedBrieIncreaseQualityGettingOlderButNeverMoreThan50() {
        val items = arrayOf<Item>(Item("Aged Brie", 10, 5))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 90)

        assertEquals("Aged Brie", app.items[0].name)
        assertEquals(-80, app.items[0].sellIn)
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun testSulfurasIsLegendarySoDontHaveToBeSoldAndQualityNeverDecrease() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 10, 50))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 100)

        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name)
        assertEquals(10, app.items[0].sellIn)
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun testBackstagePassesIncreaseQualityGettingOlder() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 15, 10))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 5)

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(10, app.items[0].sellIn)
        assertEquals(15, app.items[0].quality)
    }

    @Test
    fun testBackstagePassesIncreaseQualityBy2When10To5DaysToTheConcert() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 15, 10))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 8)

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(7, app.items[0].sellIn)
        assertEquals(21, app.items[0].quality)
    }

    @Test
    fun testBackstagePassesIncreaseQualityBy3When5DaysOrLessToTheConcert() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 15, 10))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 15)

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(0, app.items[0].sellIn)
        assertEquals(40, app.items[0].quality)
    }

    @Test
    fun testBackstagePassesQualityDropsTo0AfterTheConcert() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 15, 10))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 16)

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun testConjuredQualityDropsTwiceEachDay() {
        val items = arrayOf<Item>(Item("Conjured", 15, 20))
        val app = GildedRose(items)

        this.updateQualityNTimes(app, 5)

        assertEquals("Conjured", app.items[0].name)
        assertEquals(10, app.items[0].sellIn)
        assertEquals(10, app.items[0].quality)
    }

    private fun updateQualityNTimes(app: GildedRose, times: Int) {
        repeat(times) {
            app.updateQuality()
        }
    }

}


