package org.goldlike.generate_code.model.clazz;

import java.util.List;
import java.util.Set;

/**
 * @author Jin.Nie
 * @since 2020-12-25
 */
public class EntityClass {

    /**
     * 包名
     */
    private String packageName;

    /**
     * import
     */
    private Set<String> importClass;

    /**
     * 类名
     */
    private String clazzName;

    /**
     * 类注释
     */
    private String remark;

    /**
     * 列
     */
    private List<ColumnClass> columns;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Set<String> getImportClass() {
        return importClass;
    }

    public void setImportClass(Set<String> importClass) {
        this.importClass = importClass;
    }

    public List<ColumnClass> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnClass> columns) {
        this.columns = columns;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
