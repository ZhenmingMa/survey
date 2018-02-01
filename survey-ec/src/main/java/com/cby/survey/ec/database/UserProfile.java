package com.cby.survey.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by baiyanfang on 2018/1/30.
 */

@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private String id;
    private String token;
    private long phone;
    private Long time;
    private Long birthday;
    private String location;
    private String occupation;
    private String income;
    private String hobby;
    private String sex;

    @Generated(hash = 67361653)
    public UserProfile(String id, String token, long phone, Long time, Long birthday, String location, String occupation, String income, String hobby, String sex) {
        this.id = id;
        this.token = token;
        this.phone = phone;
        this.time = time;
        this.birthday = birthday;
        this.location = location;
        this.occupation = occupation;
        this.income = income;
        this.hobby = hobby;
        this.sex = sex;
    }

    @Generated(hash = 968487393)
    public UserProfile() {
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public long getPhone() {
        return phone;
    }


    public Long getTime() {
        return time;
    }

    public Long getBirthday() {
        return birthday;
    }

    public String getLocation() {
        return location;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getIncome() {
        return income;
    }

    public String getHobby() {
        return hobby;
    }

    public String getSex() {
        return sex;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
