package com.htp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {

    private Long id;
    private Long user_id;
    private Long adress_id;
    private Timestamp time;
    private boolean is_done;

    public Order() {
    }

    public Order(Long user_id, Long adress_id){
        this.user_id = user_id;
        this.adress_id = adress_id;
    }

    public Order(Long id, Long user_id, Long adress_id, Timestamp time, boolean is_done){
        this.id = id;
        this.user_id = user_id;
        this.adress_id = adress_id;
        this.time = time;
        this.is_done = is_done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getAdress_id() {
        return adress_id;
    }

    public void setAdress_id(Long adress_id) {
        this.adress_id = adress_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public boolean getIs_done() {
        return is_done;
    }

    public void setIs_done(boolean is_done) {
        this.is_done = is_done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(is_done, order.is_done) &&
                Objects.equals(id, order.id) &&
                Objects.equals(user_id, order.user_id) &&
                Objects.equals(adress_id, order.adress_id) &&
                Objects.equals(time, order.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, adress_id, time, is_done);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
