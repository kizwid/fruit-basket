package sandkev;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.MonetaryCurrencies;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin on 16/04/2016.
 */
public class FruitBasket {
    public static final CurrencyUnit GBP = MonetaryCurrencies.getCurrency("GBP");//for convenience
    private final Map<FruitItem, Integer> quantityByItem;
    public FruitBasket() {
        this.quantityByItem = new HashMap<FruitItem, Integer>();
    }
    public void add(FruitItem item, int quantity){
        Integer count = quantityByItem.get(item);
        if(count==null){
            quantityByItem.put(item, count = new Integer(0));
        }
        quantityByItem.put(item, count+=quantity);
    }

    public void remove(FruitItem item, int quantity){
        Integer count = quantityByItem.get(item);
        if(count==null){
            throw new IllegalArgumentException("Item does not exist in basket: " + item);
        }else if(count<quantity){
            System.err.println("there were only " + count + " of " + item + " in the basket. have removed them all");
            quantityByItem.remove(item);
        }else {
            quantityByItem.put(item, count-=quantity);
            System.out.println(quantity + " items of " + item + " removed from basket. " + count + " still remain");
        }
    }

    public Money totalUnitCost(){
        Money total = Money.of(0.0, GBP);
        for (Map.Entry<FruitItem, Integer> entry : quantityByItem.entrySet()) {
            Money amount = entry.getKey().getPrice().multiply(entry.getValue());
            total = total.add(amount);
        }
        return total;
    }

    @Override
    public String toString() {
        return "FruitBasket{" +
                "quantityByItem=" + quantityByItem +
                ", total=" + totalUnitCost() +
                '}';
    }
}
