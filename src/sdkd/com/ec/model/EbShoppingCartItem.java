package sdkd.com.ec.model;

/**
 * Created by Administrator on 2016/7/8.
 */
public class EbShoppingCartItem {
    private EbProduct product;
    private Long quantity;
    private Double cost;

    public EbShoppingCartItem(EbProduct product, long quantity){
        this.product = product;
        this.quantity = quantity;
        this.cost = product.getEpPrice() * quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
        this.cost = product.getEpPrice() * quantity;
    }

    public Double getCost() {
        return cost;
    }

    public EbProduct getProduct() {
        return product;
    }
}
