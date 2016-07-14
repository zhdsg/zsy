package sdkd.com.ec.model;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbProduct;
import sdkd.com.ec.model.EbShoppingCartItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class EbShoppingCart extends BaseDao {
    private List<EbShoppingCartItem> items = new ArrayList<>();

    public void addItem(EbProduct product, long quantity){
        items.add(new EbShoppingCartItem(product, quantity));
    }

    public void removeItem(int index){
        items.remove(index);
    }

    public void modifyQuantity(int index, long quantity){
        items.get(index).setQuantity(quantity);
    }

    public double getTotalCost(){
        double sum = 0;
        for(EbShoppingCartItem item : items){
            sum += item.getCost();
        }
        return sum;
    }

    public List<EbShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<EbShoppingCartItem> items) {
        this.items = items;
    }
}
