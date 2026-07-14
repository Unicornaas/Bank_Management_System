package edu.fjut.mybatis.entity;

public class UserVip {
    private Integer id;
    private String levelName;
    private String levelCode;
    private Double minBalance;
    private Double discountRate;
    private String color;
    private String icon;
    private String benefits;
    private Integer sortOrder;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLevelName() { return levelName; }
    public void setLevelName(String levelName) { this.levelName = levelName; }

    public String getLevelCode() { return levelCode; }
    public void setLevelCode(String levelCode) { this.levelCode = levelCode; }

    public Double getMinBalance() { return minBalance; }
    public void setMinBalance(Double minBalance) { this.minBalance = minBalance; }

    public Double getDiscountRate() { return discountRate; }
    public void setDiscountRate(Double discountRate) { this.discountRate = discountRate; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getBenefits() { return benefits; }
    public void setBenefits(String benefits) { this.benefits = benefits; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
