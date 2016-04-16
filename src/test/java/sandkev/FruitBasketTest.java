package sandkev;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static sandkev.FruitBasket.GBP;
import static sandkev.FruitType.*;

/**
 * Created by kevin on 16/04/2016.
 *
 * Created in response to the following email
 *
 ---------------------------------------
 Hi Kevin,

 Can you please complete the task below:

 Write a program that takes a basket of items and outputs its total cost.

 The basket can contain one or more of the following items: Bananas, Oranges, Apples, Lemons, Peaches

 If you can please let me know when you would be able to return this to me.
 ---------------------------------------
 *
 *
 */
public class FruitBasketTest {

    private FruitBasket basket;
    private FruitItem bananna;
    private FruitItem orange;
    private FruitItem apple;
    private FruitItem peach;

    @Before
    public void setUp() throws Exception {
        basket = new FruitBasket();
        bananna = createItem(Bananas, Money.of(0.50, GBP));
        orange = createItem(Oranges, Money.of(0.51, GBP));
        apple = createItem(Apples, Money.of(0.53, GBP));
        peach = createItem(Peaches, Money.of(0.55, GBP));
    }

    private FruitItem createItem(FruitType fruitType, Money price) {
        return new FruitItem(fruitType, price);
    }

    @Test
    public void canAddAndRemoveSingleItems(){
        basket.add(bananna, 1);
        assertEquals(Money.of(0.50, GBP), basket.totalUnitCost());

        basket.add(bananna, 9);
        assertEquals(Money.of(5.0, GBP), basket.totalUnitCost());

        basket.remove(bananna, 5);
        assertEquals(Money.of(2.50, GBP), basket.totalUnitCost());
    }

    @Test
    public void canAddAndRemoveMultipleItems(){
        basket.add(bananna, 10);
        basket.add(apple, 10);
        basket.add(orange, 10);
        basket.add(peach, 10);
        assertEquals(Money.of(20.9, GBP), basket.totalUnitCost());

        basket.remove(bananna, 5);
        assertEquals(Money.of(18.4, GBP), basket.totalUnitCost());
        basket.remove(apple, 5);
        assertEquals(Money.of(15.75, GBP), basket.totalUnitCost());
        basket.remove(orange, 5);
        assertEquals(Money.of(13.2, GBP), basket.totalUnitCost());

        System.out.println("-------");
        System.out.println(basket);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantRemoveItemsThatAreNotInBasket(){
        basket.remove(bananna, 100);
    }

    @Test
    public void wontRemoveMoreItemsThanThereAre(){
        basket.add(bananna, 10);
        basket.remove(bananna, 100);
        assertEquals(Money.of(0.0, GBP), basket.totalUnitCost());
    }

}