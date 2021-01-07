package org.goldlike.generate_code.model;

import org.goldlike.generate_code.constants.GenerateConstants;
import org.goldlike.generate_code.model.clazz.EntityClass;

import java.util.List;

/**
 * @author Jin.Nie
 * @since 2020-12-26
 */
public class ConfigGenerateCode {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 实体名
     */
    private String entityClassName;

    /**
     * 作者
     */
    private String author;

    /**
     * 实体使用的注解
     */
    private List<String> entityAnnotations;

    /**
     * 实体使用的方法
     */
    private List<String> entityMethods;

    /**
     * Mapper
     */
    private String mapperClassName;

    /**
     * Service
     */
    private String serviceClassName;

    /**
     * Controller
     */
    private String controllerClassName;

    /**
     * 相关接口方法
     * 具体见 {@link GenerateConstants}
     */
    private List<String> apiMethods;

    /**
     * 实体对象
     * 非前端传入
     */
    private EntityClass entityClass;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public List<String> getEntityAnnotations() {
        return entityAnnotations;
    }

    public void setEntityAnnotations(List<String> entityAnnotations) {
        this.entityAnnotations = entityAnnotations;
    }

    public List<String> getEntityMethods() {
        return entityMethods;
    }

    public void setEntityMethods(List<String> entityMethods) {
        this.entityMethods = entityMethods;
    }

    public String getMapperClassName() {
        return mapperClassName;
    }

    public void setMapperClassName(String mapperClassName) {
        this.mapperClassName = mapperClassName;
    }

    public String getControllerClassName() {
        return controllerClassName;
    }

    public void setControllerClassName(String controllerClassName) {
        this.controllerClassName = controllerClassName;
    }

    public List<String> getApiMethods() {
        return apiMethods;
    }

    public void setApiMethods(List<String> apiMethods) {
        this.apiMethods = apiMethods;
    }

    public EntityClass getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(EntityClass entityClass) {
        this.entityClass = entityClass;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
