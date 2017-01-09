package com.micai.itext.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/9.
 */
public class UserPO  implements Serializable{

    private int id;

    private String userName;    // 用户名

    private String passWord;    // 密码

    private String email;   // 邮箱

    private String phone;   // 手机号

    private Date createDate; // 创建时间

    private Date updateDate; // 更新时间

    private String version; // 版本号

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
