package com.desafio.um.entities;

public class OrderImpl implements Order {
    private Integer code;
    private Double basic;
    private Double discount;

    public OrderImpl(Integer code, Double basic, Double discount) {
        this.code = code;
        this.basic = basic;
        this.discount = discount;
    }

    public Integer getCode() {
        return code;
    }

    public Double getBasic() {
        return basic;
    }

    public Double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "code=" + code +
                ", basic=" + basic +
                ", discount=" + discount +
                '}';
    }
}
