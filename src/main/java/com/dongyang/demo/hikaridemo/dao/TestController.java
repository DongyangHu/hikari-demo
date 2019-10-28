package com.dongyang.demo.hikaridemo.dao;

import com.dongyang.demo.hikaridemo.aspect.ChooseDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import static com.dongyang.demo.hikaridemo.enums.DataSourceEnum.TEST_DB_1;
import static com.dongyang.demo.hikaridemo.enums.DataSourceEnum.TEST_DB_2;

/**
 * @author dongyang.hu
 * @date 2019/10/24 20:05
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("test1")
    @ChooseDataSource(TEST_DB_1)
    public String test1() {
        return select();
    }

    @RequestMapping("test2")
    @ChooseDataSource(TEST_DB_2)
    public String test2() {
        return select();
    }

    private String select() {
        String result = "empty";
        String sql = "SELECT name FROM test";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(1);
            }
        } catch (Exception e) {
            log.error("select error.", e);
            release(connection, statement, resultSet);
        } finally {
            release(connection, statement, resultSet);
        }
        return result;
    }

    private void release(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (Objects.nonNull(connection)) {
                connection.close();
            }
            if (Objects.nonNull(statement)) {
                statement.close();
            }
            if (Objects.nonNull(resultSet)) {
                resultSet.close();
            }
        } catch (Exception e) {
            log.error("release error.", e);
        }
    }
}
