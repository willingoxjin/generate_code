package ${packageName}.mapper;
<#if entityAnnotations??>
    <#list entityAnnotations as anno >
        <#if anno == 'mybatis-plus'>
            <#assign mybatisPlus = true>
        </#if>
    </#list>
</#if>

<#if mybatisPlus??>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
</#if>
import ${packageName}.entity.${entityClass.clazzName};
import java.util.List;

/**
 *
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */
public interface ${mapperClassName} <#if mybatisPlus??>extends BaseMapper<${entityClassName}></#if> {



}