package ${packageName}.controller;
<#if entityAnnotations??>
    <#list entityAnnotations as anno >
        <#if anno == 'swagger2'>
            <#assign swagger2 = true>
        </#if>
    </#list>
</#if>

import ${packageName}.service.${serviceClassName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
<#if swagger2??>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>

/**
 *
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */

@RestController
@RequestMapper("/${entityClass.clazzName?uncap_first}")
<#if swagger2??>
@Api(value = "", tags = "")
</#if>
public class ${controllerClassName}{

    @Autowired
    private ${serviceClassName} ${serviceClassName?uncap_first};

    /*
    @GetMapping("/${entityClass.clazzName?lower_case}s")
    public List<${entityClass.clazzName}> getAll${entityClass.clazzName}s(){
        return ${entityClass.clazzName?uncap_first}.getAll${entityClass.clazzName}s();
    }
    */
}