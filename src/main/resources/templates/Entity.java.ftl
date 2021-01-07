package ${packageName}.entity;

<#if entityClass.importClass??>
    <#list entityClass.importClass as item>
import ${item};
    </#list>
</#if>


/**
 *
<#if entityClass.remark??>
 * 数据库表备注：${entityClass.remark}
</#if>
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */

<#if entityAnnotations??>
    <#list entityAnnotations as anno>
        <#if anno == "lombok">
            <#assign lombok = true>
            <#if entityMethods??>
                <#list entityMethods as method>
                    <#if method == 'Constructor'>
@AllArgsConstructor
@NoArgConstructor
                    <#else>
@${method}
                    </#if>
                </#list>
            </#if>
        </#if>
        <#if anno == "swagger2">
@ApiModel(value = "${entityClass.clazzName}对象", description = "数据库表：${tableName}")
        </#if>
        <#if anno == "mybatis-plus">
@TableName(value = "${tableName}")
        </#if>
    </#list>
</#if>
public class ${entityClass.clazzName} {

    <#if entityClass.columns??>
        <#list entityClass.columns as column>
            <#include "common/Column.ftl">
    private ${column.propertyType} ${column.propertyName?uncap_first};
        </#list>
    </#if>

    <#if entityMethods?? && !lombok?? && entityClass.columns??>
        <#list entityMethods as method>
            <#if method == 'ToString'>
    @Override
    public String toString() {
        return "${entityClass.clazzName}{" +
        <#list entityClass.columns as column>
            ", ${column.propertyName?uncap_first}='" + ${column.propertyName?uncap_first} + '\'' +
        </#list>
        '}';
    }
            </#if>

            <#if method == 'Constructor'>
    public ${entityClass.clazzName}() {}

    public ${entityClass.clazzName}(<#list entityClass.columns as column>${column.propertyType} ${column.propertyName?uncap_first}<#sep>, </#list>) {
        <#list entityClass.columns as column>
        this.${column.propertyName?uncap_first} = ${column.propertyName?uncap_first};
        </#list>
    }
            </#if>
            <#if method == 'Getter'>
                <#list entityClass.columns as column>
    public ${column.propertyType} get${column.propertyName}(){
        return ${column.propertyName?uncap_first};
    }

                </#list>
            </#if>
            <#if method == 'Setter'>
                <#list entityClass.columns as column>
    public void set${column.propertyName}(${column.propertyType} ${column.propertyName?uncap_first}){
        this.${column.propertyName?uncap_first} = ${column.propertyName?uncap_first};
    }

                </#list>
            </#if>
        </#list>
    </#if>
}