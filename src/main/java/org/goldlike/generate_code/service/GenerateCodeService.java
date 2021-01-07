package org.goldlike.generate_code.service;

import com.google.common.base.CaseFormat;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.goldlike.generate_code.constants.GenerateConstants;
import org.goldlike.generate_code.model.clazz.ColumnClass;
import org.goldlike.generate_code.model.clazz.EntityClass;
import org.goldlike.generate_code.model.ConfigGenerateCode;
import org.goldlike.generate_code.model.ResultResp;
import org.goldlike.generate_code.utils.DBUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jin.Nie
 * @since 2021-01-04
 */
@Service
public class GenerateCodeService {


    Configuration cfg = null;

    {
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setTemplateLoader(new ClassTemplateLoader(GenerateCodeService.class, "/templates"));
        cfg.setDefaultEncoding("UTF-8");
    }

    /**
     * 生成代码
     *
     * @param configGenerateCodeList
     * @param realPath               生成代码的根路径
     * @return
     */
    public ResultResp generateCode(List<ConfigGenerateCode> configGenerateCodeList, String realPath) {
        try {
            Template modelTemplate = cfg.getTemplate("Entity.java.ftl");
            Template mapperJavaTemplate = cfg.getTemplate("Mapper.java.ftl");
            Template mapperXmlTemplate = cfg.getTemplate("Mapper.xml.ftl");
            Template serviceTemplate = cfg.getTemplate("Service.java.ftl");
            Template serviceImplTemplate = cfg.getTemplate("ServiceImpl.java.ftl");
            Template controllerTemplate = cfg.getTemplate("Controller.java.ftl");
            Connection connection = DBUtils.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            for (ConfigGenerateCode config : configGenerateCodeList) {
                ResultSet table = metaData.getTables(connection.getCatalog(), null, config.getTableName(), null);
                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, config.getTableName(), null);
                ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, config.getTableName());
                String primaryName = "";
                while (primaryKeys.next()) {
                    primaryName = primaryKeys.getString("COLUMN_NAME");
                }
                List<ColumnClass> columnClassList = new ArrayList<>();
                while (columns.next()) {
                    String column_name = columns.getString("COLUMN_NAME");
                    String type_name = columns.getString("TYPE_NAME");
                    String column_remarks = columns.getString("REMARKS");
                    ColumnClass columnClass = new ColumnClass();
                    columnClass.setRemark(column_remarks);
                    columnClass.setColumnName(column_name);
                    columnClass.setType(type_name);
                    columnClass.setPropertyName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, column_name));
                    if (primaryName.equals(column_name)) {
                        columnClass.setPrimary(true);
                    }
                    // 类型转换
                    columnClass.autoConcertType();
                    columnClassList.add(columnClass);
                }

                EntityClass entityClass = new EntityClass();
                entityClass.setPackageName(config.getPackageName());
//                char[] ch = config.getEntityClassName().toCharArray();
//                if (ch[0] >= 'a' && ch[0] <= 'z') {
//                    ch[0] = (char) (ch[0] - 32);
//                }
                entityClass.setClazzName(config.getEntityClassName());
                entityClass.setColumns(columnClassList);
                entityClass.setImportClass(handlerEntityImport(config, columnClassList));
                while (table.next()) {
                    entityClass.setRemark(table.getString("REMARKS"));
                }
                config.setEntityClass(entityClass);

                String path = realPath + "/" + entityClass.getPackageName().replace(".", "/");

                generate(modelTemplate, config, path + "/entity/");
                generate(mapperJavaTemplate, config, path + "/mapper/");
                generate(mapperXmlTemplate, config, path + "/mapper/xml/");
                generate(serviceTemplate, config, path + "/service/");
                generate(serviceImplTemplate, config, path + "/service/impl");
                generate(controllerTemplate, config, path + "/controller/");


            }
            return ResultResp.ok("代码已生成,\uD83D\uDE01", realPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultResp.error("代码生成失败！\uD83D\uDE30 \uD83D\uDE30 \uD83D\uDE30");
    }

    /**
     * 生成文件
     */
    private void generate(Template template, ConfigGenerateCode generateCodeDTO, String path) throws IOException, TemplateException {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = path + "/" +
                          generateCodeDTO.getEntityClassName() +
                          template.getName().replace(".ftl", "").replace("Entity", "");
        System.out.println(fileName);
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter out = new OutputStreamWriter(fos);
        template.process(generateCodeDTO, out);
        fos.close();
        out.close();
    }


    /**
     * 处理import
     */
    private Set<String> handlerEntityImport(ConfigGenerateCode configGenerateCode, List<ColumnClass> columnClassList) {
        // 注解
        List<String> tableAnnoNames = configGenerateCode.getEntityAnnotations();
        if (tableAnnoNames == null || tableAnnoNames.size() == 0) {
            return null;
        }
        Set<String> importList = new LinkedHashSet<>();
        List<String> entityMethodList = configGenerateCode.getEntityMethods();
        for (String annotationName : tableAnnoNames) {
            if (annotationName.equals(GenerateConstants.EntityAnnos.SWAGGER2)) {
                importList.addAll(Arrays.asList(GenerateConstants.EntityAnnos.IMPORT_SWAGGER2));
            }
            if (annotationName.equals(GenerateConstants.EntityAnnos.MYBATIS_PLUS)) {
                importList.addAll(Arrays.asList(GenerateConstants.EntityAnnos.IMPORT_MYBATIS_PLUS));
            }
            if (annotationName.equals(GenerateConstants.EntityAnnos.LOMBOK)) {
                entityMethodList.stream().forEach(entityMethod -> {
                    String importName = GenerateConstants.EntityAnnos.IMPORT_LOMBOK.get(entityMethod);
                    if (importName!= null) {
                        importList.add(importName);
                    }
                });
            }

            // 其他注解在此补充

        }

        // 属性及其他
        List<String> columnTypeList = columnClassList.stream().map(ColumnClass::getType).collect(Collectors.toList());
        columnTypeList.stream().forEach(type -> {
            if (type.equals("DATETIME") || type.equals("DATE")) {
                importList.add("java.util.Date");
            } else if (type.equals("DECIMAL")) {
                importList.add("java.math.BigDecimal");
            }
        });

        return importList;
    }


}
