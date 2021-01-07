package ${packageName}.service.impl;

import ${packageName}.mapper.${mapperClassName};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ${author}
 * @since ${.now?string("yyyy-MM-dd")}
 */

@Service
public class ${serviceClassName}Impl implements ${serviceClassName}{

    @Autowired
    ${mapperClassName} ${mapperClassName?uncap_first};

}