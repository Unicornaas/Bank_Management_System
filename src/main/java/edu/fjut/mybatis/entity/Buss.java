package edu.fjut.mybatis.entity;

import java.util.Date;

public class Buss {
    private Integer id;
    private Integer uid;
    private String type;
    private Double amount;
    private Date createTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUid() { return uid; }
    public void setUid(Integer uid) { this.uid = uid; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "Buss{id=" + id + ", uid=" + uid + ", type='" + type +
                "', amount=" + amount + ", createTime=" + createTime + "}";
    }
}
