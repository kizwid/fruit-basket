package sandkev;

import org.javamoney.moneta.Money;

/**
 * Created by kevin on 16/04/2016.
 */
public class FruitItem {
    private final FruitType fruitType;
    private final Money price;

    public FruitItem(FruitType fruitType, Money price) {
        this.fruitType = fruitType;
        this.price = price;
    }

    public FruitType getType() {
        return fruitType;
    }

    public Money getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitItem fruitItem = (FruitItem) o;

        if (fruitType != fruitItem.fruitType) return false;
        return !(price != null ? !price.equals(fruitItem.price) : fruitItem.price != null);

    }

    @Override
    public int hashCode() {
        int result = fruitType != null ? fruitType.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FruitItem{" +
                "fruitType=" + fruitType +
                ", price=" + price +
                '}';
    }
}
