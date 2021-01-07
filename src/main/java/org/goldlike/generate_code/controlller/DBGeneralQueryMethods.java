package org.goldlike.generate_code.controlller;

import org.goldlike.generate_code.constants.GenerateConstants;
import org.goldlike.generate_code.model.ResultResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jin.Nie
 * @since 2021-01-04
 */
@RestController
@RequestMapping("generateCode")
public class DBGeneralQueryMethods {



    /**
     * 获取生成的数据库方法
     *
     * @return
     */
    @GetMapping("/getGeneralOptions")
    public ResultResp getGeneralQueryMethods() {
        Map resultMap = new HashMap();
        resultMap.put("apiMethods", GenerateConstants.ApiMethods.getApiMethods());
        resultMap.put("entityAnnotations", GenerateConstants.EntityAnnos.getEntityAnnos());
        resultMap.put("entityMethods", GenerateConstants.EntityMethods.getEntityMethods());
        return ResultResp.ok("success", resultMap);
    }



}
