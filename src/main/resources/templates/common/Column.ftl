    /**
     * ${column.remark}
     */
    <#-- 注解 -->
    <#if entityAnnotations??>
        <#if column.isPrimary?? && column.isPrimary==true>
            123
            <#assign isPrimary = true>
        </#if>
        <#list entityAnnotations as anno >
            <#--<#if anno == "mybatis-plus" && isPrimary?? && isPrimary = true>
    @TableId(value = "${column.columnName}", type = IdType.AUTO)
            </#if>-->
            <#if anno == "swagger2">
    @ApiModelProperty(value = "${column.remark}")
            </#if>
        </#list>
    </#if>