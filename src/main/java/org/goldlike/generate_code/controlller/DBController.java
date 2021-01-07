package org.goldlike.generate_code.controlller;

import org.goldlike.generate_code.model.db.DB;
import org.goldlike.generate_code.model.ResultResp;
import org.goldlike.generate_code.model.db.Table;
import org.goldlike.generate_code.utils.DBUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;

import java.util.List;
import java.util.Map;

/**
 * 数据库逆向生成Java代码
 *
 * <p>步骤：
 * 1.连接数据库-返回数据库表信息
 * 2.选择需要生成代码的表
 * 3.配置生成的文件名、实体模型注解、相关的Query方法等
 * 4.生成代码
 *
 * @author Jin.Nie
 * @since 2020-12-25
 */
@RestController
@RequestMapping("generateCode")
public class DBController {

    /**
     * 连接数据库
     *
     * @param db
     * @return
     */
    @PostMapping("connect")
    public ResultResp connect(@RequestBody DB db) {
        try {
            Connection connection = DBUtils.connection;
            if (DBUtils.connection == null) {
                connection = DBUtils.initDB(db);
            }
            if (connection != null) {
                List<Table> tables = DBUtils.getDBTables(connection);
                return ResultResp.ok("数据库连接成功！", tables);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage().equals("") ? "数据库连接失败！" : e.getMessage();
            return ResultResp.error(msg);
        }
        return ResultResp.error("数据库连接失败！");
    }

    /**
     * 断开连接
     */
    @PostMapping("disConnect")
    public ResultResp disConnect() {
        DBUtils.disConnect();
        return ResultResp.ok("数据路已断开连接！");
    }

    /**
     * 连接状态
     * 若存在连接直接返回
     */
    @GetMapping("connStatus")
    public ResultResp connStatus() {
        try {
            Connection connection = DBUtils.connection;
            if (connection != null) {
                List<Table> tables = DBUtils.getDBTables(connection);
                return ResultResp.ok("数据库已连接！", tables);
            }
            return ResultResp.ok("数据库未连接！");
        } catch (Exception e) {
            return ResultResp.error(e.getMessage());
        }

    }


}
