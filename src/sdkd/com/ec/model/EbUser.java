package sdkd.com.ec.model;

import javax.xml.crypto.Data;

/**
 * Created by Administrator on 2016/7/6.
 */
public class EbUser {
    /**
     * eu_user_id
     eu_user_name
     eu_password
     eu_sex
     eu_birthday
     eu_identity_code
     eu_email
     eu_mobile
     eu_address
     eu_status

     */
    Integer euUserid;
    String euUsername;
    String euPassword;
    String euSex;
    Data euBirthday;
    String euIdentitycode;
    String euEmail;
    String euAddress;
    String euStatus;

    public Integer getEuUserid() {
        return euUserid;
    }

    public void setEuUserid(Integer euUserid) {
        this.euUserid = euUserid;
    }

    public String getEuUsername() {
        return euUsername;
    }

    public void setEuUsername(String euUsername) {
        this.euUsername = euUsername;
    }

    public String getEuPassword() {
        return euPassword;
    }

    public void setEuPassword(String euPassword) {
        this.euPassword = euPassword;
    }

    public String getEuSex() {
        return euSex;
    }

    public void setEuSex(String euSex) {
        this.euSex = euSex;
    }

    public Data getEuBirthday() {
        return euBirthday;
    }

    public void setEuBirthday(Data euBirthday) {
        this.euBirthday = euBirthday;
    }

    public String getEuIdentitycode() {
        return euIdentitycode;
    }

    public void setEuIdentitycode(String euIdentitycode) {
        this.euIdentitycode = euIdentitycode;
    }

    public String getEuEmail() {
        return euEmail;
    }

    public void setEuEmail(String euEmail) {
        this.euEmail = euEmail;
    }

    public String getEuAddress() {
        return euAddress;
    }

    public void setEuAddress(String euAddress) {
        this.euAddress = euAddress;
    }

    public String getEuStatus() {
        return euStatus;
    }

    public void setEuStatus(String euStatus) {
        this.euStatus = euStatus;
    }
}
