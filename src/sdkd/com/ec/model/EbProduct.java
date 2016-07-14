package sdkd.com.ec.model;

/**
 * Created by Administrator on 2016/7/6.
 */
public class EbProduct {
    /*


     */
    private int epId;
    private String epName;
    private String epDescription;
    private  double epPrice;
    private  int epStock;
    private int  epcId;
    private int epDiscount;
    private int epHotsale;
    private Integer epViews;

    public EbProduct(){}

    public Integer getEpViews() {
        return epViews;
    }

    public void setEpViews(Integer epViews) {
        this.epViews = epViews;
    }

    public EbProduct(String epName, String epDescription, Double epPrice, Integer epStock, Integer epcId, Integer epcChildId, String epFileName, Integer epDiscount) {
                 this.epName = epName;
                 this.epDescription = epDescription;
                 this.epPrice = epPrice;
                 this.epStock = epStock;
                 this.epcId = epcId;
                 this.epcChildId = epcChildId;
                 this.epFileName = epFileName;
                 this.epDiscount = epDiscount;
            }


    public int getEpDiscount() {
        return epDiscount;
    }

    public void setEpDiscount(int epDiscount) {
        this.epDiscount = epDiscount;
    }

    public int getEpHotsale() {
        return epHotsale;
    }

    public void setEpHotsale(int epHotsale) {
        this.epHotsale = epHotsale;
    }



    public int getEpId() {
        return epId;
    }

    public void setEpId(int epId) {
        this.epId = epId;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getEpDescription() {
        return epDescription;
    }

    public void setEpDescription(String epDescription) {
        this.epDescription = epDescription;
    }

    public double getEpPrice() {
        return epPrice;
    }

    public void setEpPrice(double epPrice) {
        this.epPrice = epPrice;
    }

    public int getEpStock() {
        return epStock;
    }

    public void setEpStock(int epStock) {
        this.epStock = epStock;
    }

    public int getEpcId() {
        return epcId;
    }

    public void setEpcId(int epcId) {
        this.epcId = epcId;
    }

    public int getEpcChildId() {
        return epcChildId;
    }

    public void setEpcChildId(int epcChildId) {
        this.epcChildId = epcChildId;
    }

    public String getEpFileName() {
        return epFileName;
    }

    public void setEpFileName(String epFileName) {
        this.epFileName = epFileName;
    }

    private int epcChildId;
    private String epFileName;

    @Override
         public String toString() {
                return "EbProduct{" +
                                 "epId=" + epId +
                                 ", epName='" + epName + '\'' +
                                 ", epDescription='" + epDescription + '\'' +
                                 ", epPrice=" + epPrice +
                                 ", epStock=" + epStock +
                                 ", epcId=" + epcId +
                                 ", epcChildId=" + epcChildId +
                                 ", epFileName='" + epFileName + '\'' +
                                 ", epDiscount=" + epDiscount +
                                 ", epViews=" + epViews +
                                 '}';
             }



}
