package org.goldlike.generate_code.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jin.Nie
 * @since 2021-01-04
 */
public class GenerateConstants {

    /**
     * 实体注解
     */
    public static class EntityAnnos {
        public static final String LOMBOK = "lombok";
        public static final String SWAGGER2 = "swagger2";
        public static final String MYBATIS_PLUS = "mybatis-plus";

        public static String[] getEntityAnnos() {
            return new String[] {
                    LOMBOK,
                    SWAGGER2,
                    MYBATIS_PLUS
            };
        }

        public static final String[] IMPORT_SWAGGER2 = {
                "io.swagger.annotations.ApiModel",
                "io.swagger.annotations.ApiModelProperty"
        };
        public static final String[] IMPORT_MYBATIS_PLUS = {
                "com.baomidou.mybatisplus.annotation.IdType",
                "com.baomidou.mybatisplus.annotation.TableId",
        };
        public static final Map<String, String> IMPORT_LOMBOK = new HashMap<String, String>(){{
            put("toString", "lombok.ToString");
            put("Getter", "lombok.Getter");
            put("Setter", "lombok.Setter");
            put("toString", "lombok.ToString");
            put("Constructor", "lombok.AllArgsConstructor;\nimport lombok.NoArgsConstructor");
        }};
    }

    /**
     * 实体方法
     */
    public static class EntityMethods {
        public static final String TO_STRING = "ToString";
        public static final String CONSTRUCTOR = "Constructor";
        public static final String GETTER = "Getter";
        public static final String SETTER = "Setter";

        public static String[] getEntityMethods() {
            return new String[] {
                    TO_STRING,
                    CONSTRUCTOR,
                    GETTER,
                    SETTER
            };
        }

    }

    /**
     * 相关的生成的方法
     */
    public static class ApiMethods {
        public static final String INSERT = "insert";
        public static final String UPDATE_BY_ID = "updateById";
        // 带delFlag属性
        public static final String GET_BY_ID = "getById";
        // 带delFlag属性
        public static final String GET_LIST = "getList";
        // 带delFlag属性
        public static final String GET_PAGE_LIST = "getPageList";
        // 逻辑删除
        public static final String LOGIC_DEL_BY_ID = "logicDelById";

        public static String[] getApiMethods() {
            return new String[] {
                    INSERT,
                    UPDATE_BY_ID,
                    GET_BY_ID,
                    GET_LIST,
                    GET_PAGE_LIST,
                    LOGIC_DEL_BY_ID
            };
        }

    }

}
