package org.goldlike.generate_code.model.clazz;

import java.util.List;

/**
 * 列对象
 *
 * @author Jin.Nie
 * @since 2020-12-25
 */
public class ColumnClass {

    /**
     * 属性名
     */
    private String propertyName;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 类型
     */
    private String type;

    /**
     * 属性类型
     */
    private String propertyType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否为主键
     */
    private Boolean isPrimary;


    /**
     * 根据type判断propertyType
     *
     * @return
     */
    public void autoConcertType() {
        String type = this.type;
        if (type.equals("VARCHAR") || type.equals("TEXT") || type.equals("CHAR")) {
            this.propertyType = "String";
        } else if (type.equals("DATETIME") || type.equals("DATE")) {
            this.propertyType = "Date";
        } else if (type.equals("INT")) {
            this.propertyType = "Integer";
        } else if (type.equals("BIGINT")) {
            this.propertyType = "Long";
        } else if (type.equals("DOUBLE")) {
            this.propertyType = "Double";
        } else if (type.equals("BIT")) {
            this.propertyType = "Boolean";
        } else if (type.equals("DECIMAL")) {
            this.propertyType = "BigDecimal";
        } else {
            this.propertyType = "null";
        }

    }

    @Override
    public String toString() {
        return "ColumnClass{" +
                "propertyName='" + propertyName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
