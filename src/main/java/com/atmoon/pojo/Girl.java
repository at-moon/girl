package com.atmoon.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
public class Girl implements Serializable {

    private static final long serialVersionUID = -7402143330657397684L;

    public Girl() {
    }

    public Girl(String city, @Min(value = 18, message = "禁止未成年入内") Integer age) {
        this.city = city;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;

    @Min(value = 18,message = "禁止未成年入内")
    private Integer age;

    private double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
