package org.goldlike.generate_code.controlller;

import org.goldlike.generate_code.model.ConfigGenerateCode;
import org.goldlike.generate_code.model.ResultResp;
import org.goldlike.generate_code.service.GenerateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Jin.Nie
 * @since 2021-01-04
 */
@RestController
@RequestMapping("generateCode")
public class GenerateCodeController {

    @Autowired
    private GenerateCodeService generateCodeService;

    @PostMapping("/generateCode")
    public ResultResp generateCode(@RequestBody List<ConfigGenerateCode> configGenerateCodeList, HttpServletRequest req) {
        return generateCodeService.generateCode(configGenerateCodeList, req.getServletContext().getRealPath("/"));
    }

}
