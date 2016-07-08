package sdkd.com.ec.model;

/**
 * Created by Administrator on 2016/7/7.
 */
public class EbPCategory {
    private  Integer epcId;
    private String epcName;
    private Integer epcParentId;

    public Integer getEpcId() {
        return epcId;
    }

    public void setEpcId(Integer epcId) {
        this.epcId = epcId;
    }

    public String getEpcName() {
        return epcName;
    }

    public void setEpcName(String epcName) {
        this.epcName = epcName;
    }

    public Integer getEpcParentId() {
        return epcParentId;
    }

    public void setEpcParentId(Integer epcParentId) {
        this.epcParentId = epcParentId;
    }
}
