package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 10));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

    @Test
    public void testUpdateEndOfDay_Decreasing_SellIn() {
        GildedRose store = new GildedRose();

        store.addItem(new Item("Omena", 5, 10));

        store.updateEndOfDay();
        List<Item> items = store.getItems();
        Item item = items.remove(0);

        assertEquals("SellIn decreasing incorrectly", 4, item.getSellIn());
    }

    @Test
    public void testUpdateEndOfDay_Decreasing_Quality() {
        GildedRose store = new GildedRose();

        store.addItem(new Item("Omena", 5, 10));

        store.updateEndOfDay();
        List<Item> items = store.getItems();
        Item item = items.remove(0);

        assertEquals("Quality decreasing incorrectly", 9, item.getQuality());
    }

    @Test
    public void testUpdateEndOfDay_Decreasing_Quality_After_SellIn() {
        GildedRose store = new GildedRose();

        store.addItem(new Item("Omena", 5, 10));

        for (int i = 0; i < 6; i++) {
            store.updateEndOfDay();
        }
        List<Item> items = store.getItems();
        Item item = items.remove(0);

        assertEquals("Quality decrasing incorrectly after SellIn", 3, item.getQuality());
    }

    @Test
    public void testUpdateEndOfDay_Quality_Is_Never_Negative() {
        GildedRose store = new GildedRose();

        store.addItem(new Item("Omena", 5, 10));

        for (int i = 0; i < 99; i++) {
            store.updateEndOfDay();
        }
        List<Item> items = store.getItems();
        Item item = items.remove(0);

        assertEquals("Quality is negative, this is not allowed", 0, item.getQuality());
    }

    @Test
    public void testUpdateEndOfDay_Quality_Is_Never_More_Than_50() {
        GildedRose store = new GildedRose();

        store.addItem(new Item("Golden Omena", 5, 999));

        store.updateEndOfDay();
        List<Item> items = store.getItems();
        Item item = items.remove(0);

        assertEquals("Quality is over 50, this is not allowed", 50, item.getQuality());
    }

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals("AgedBrie Quality incorrect", 11, itemBrie.getQuality());
	}

    @Test
    public void testUpdateEndOfDay_Sulfuras() {
        GildedRose store = new GildedRose();

        store.addItem(new Item("Sulfuras, Hand of Ragnaros", 5, 80));

        for (int i = 0; i < 5; i++) {
            store.updateEndOfDay();
        }
        List<Item> items = store.getItems();
        Item item = items.remove(0);

        assertEquals("Sulfuras quality is decreasing, this is not allowed", 80, item.getQuality());
    }

    @Test
    public void testUpdateEndOfDay_Backstage_Passes() {
        GildedRose store = new GildedRose();

        store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));

        store.updateEndOfDay();
        List<Item> items = store.getItems();
        Item item = items.get(0);

        assertEquals("Backstage quality increase is incorrect", 21, item.getQuality());

        for (int i = 0; i < 5; i++) {
            store.updateEndOfDay();
        }
        items = store.getItems();
        item = items.get(0);

        assertEquals("Backstage quality increase is incorrect", 27, item.getQuality());

        for (int i = 0; i < 5; i++) {
            store.updateEndOfDay();
        }
        items = store.getItems();
        item = items.get(0);

        assertEquals("Backstage quality increase is incorrect", 38, item.getQuality());

        for (int i = 0; i < 99; i++) {
            store.updateEndOfDay();
        }
        items = store.getItems();
        item = items.remove(0);

        assertEquals("Backstage quality after SellIn is incorrect", 0, item.getQuality());
    }
    
	@Test
	public void testUpdateEndOfDay() {
		fail("Test not implemented");
	}
}
