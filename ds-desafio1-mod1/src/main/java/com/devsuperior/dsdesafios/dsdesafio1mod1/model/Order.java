package com.devsuperior.dsdesafios.dsdesafio1mod1.model;

import java.util.Objects;

public class Order {

    public Order() {
    }

    public Order(Integer code, Double basic, Double discount) {
        this.code = code;
        this.basic = basic;
        this.discount = discount;
    }

    private Integer code;
    private Double basic;
    private Double discount;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Order order)) return false;

        return Objects.equals(code, order.code);
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

}
