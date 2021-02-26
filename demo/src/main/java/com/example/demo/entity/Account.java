package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;

    private String accountName;

    private String password;

    private String salt;

    public String getAccountName(){
        return accountName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
