package com.ceiec.twmp.tmp.authorize.dao;

import com.ceiec.twmp.tmp.authorize.vo.UserInfoVO;
import com.ceiec.twmp.tmp.authorize_interface.vo.UserTableInfoVO;
import com.ceiec.twmp.tmp.utils.tools.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CreateDate：2018/5/15 <br/>
 * Author：wenliang <br/>
 * Description: logon DAO
 **/
public class LogonDAO {

    /** datasource connection */
    private Connection connection;

    /**
     * override construction function
     *
     * @param dataSource datasource
     */
    public LogonDAO(DriverManagerDataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("unable to open datasource connection", e);
        }
    }

    /**
     * get request login user information
     *
     * @param userName request login user name
     * @param tableInfo user table information
     * @return system user information
     */
    public List<UserInfoVO> getLoginUserInfo(String userName, UserTableInfoVO tableInfo) {
        //query from database
        String sql = SQLProvider.getUserSelectSQL(userName, tableInfo);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //execute sql
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            //parse matched records and transfer to proper object
            if (resultSet != null) {
                List<UserInfoVO> matchedUsers = new ArrayList<>();
                while (resultSet.next()) {
                    UserInfoVO userInfo = extractResult(resultSet, UserInfoVO.class);
                    matchedUsers.add(userInfo);
                }
                return matchedUsers;
            }
            return null; //match no records, return null
        } catch (SQLException e) {
            throw new RuntimeException("unhandled sql exception", e);
        } finally {
            closeResultSetQuietly(resultSet);
            closeStatementQuietly(statement);
        }
    }

    /**
     * empty user login fail record
     *
     * @param userName user name
     * @param tableInfo user table information
     */
    public void emptyWrongRecords(String userName, UserTableInfoVO tableInfo) {
        String sql = SQLProvider.emptyWrongSQL(userName, tableInfo);
        executeUpdateSQL(sql);
    }

    /**
     * update user login fail record
     *
     * @param userName user name
     * @param tableInfo user table information
     */
    public void updateWrongRecord(String userName, UserTableInfoVO tableInfo) {
        String sql = SQLProvider.updateWrongSQL(userName, tableInfo);
        executeUpdateSQL(sql);
    }

    /**
     * close datasource connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("unable to close datasource connection", e);
        }
    }

    /**
     * extract result set to give class type
     *
     * @param resultSet result set
     * @param clazz given class type
     * @param <T> type template
     * @return extract result
     */
    private <T> T extractResult(ResultSet resultSet, Class<T> clazz) {
        try {
            T instance = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (int fieldIndex = 0; fieldIndex < fields.length; fieldIndex++) {
                Field field = fields[fieldIndex];
                field.setAccessible(true);
                if ("class java.lang.String".equals(field.getGenericType().toString()) || "String".equals(field.getGenericType().toString())) {
                    field.set(instance, resultSet.getString(fieldIndex + 1));
                    continue;
                }
                if ("class java.lang.Integer".equals(field.getGenericType().toString()) || "int".equals(field.getGenericType().toString())) {
                    field.set(instance, resultSet.getInt(fieldIndex + 1));
                    continue;
                }
                if ("class java.util.Date".equals(field.getGenericType().toString())) {
                    field.set(instance, resultSet.getDate(fieldIndex + 1));
                }
                if ("class java.lang.Long".equals(field.getGenericType().toString()) || "long".equals(field.getGenericType().toString())) {
                    field.set(instance, resultSet.getLong(fieldIndex + 1));
                    continue;
                }
                if ("class java.lang.tinyint".equals(field.getGenericType().toString()) || "long".equals(field.getGenericType().toString())) {
                    field.set(instance, resultSet.getLong(fieldIndex + 1));
                    continue;
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("can not extract result set");
        }
    }

    /**
     * execute update sql
     *
     * @param sql sql statement
     */
    private void executeUpdateSQL(String sql) {
        PreparedStatement statement = null;
        try {
            //execute sql
            statement = connection.prepareStatement(sql);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("unhandled sql exception", e);
        } finally {
            closeStatementQuietly(statement);
        }
    }

    /**
     * close result set quietly
     *
     * @param resultSet result set
     */
    private void closeResultSetQuietly(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            //ignore
        }
    }

    /**
     * close statement quietly
     *
     * @param statement prepared statement
     */
    private void closeStatementQuietly(PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            //ignore
        }
    }
}

/**
 * inner class, provide sql statements
 */
class SQLProvider {

    /**
     * provide user select sql
     *
     * @param userName user name
     * @param tableInfo user table information
     * @return sql statement
     */
    static String getUserSelectSQL(String userName, UserTableInfoVO tableInfo) {
        //get return fields
        List<String> fields = new ArrayList<>();
        fields.add(tableInfo.getUserID());
        fields.add(tableInfo.getUserName());
        fields.add(tableInfo.getPassword());
        if (StringUtils.isNotBlank(tableInfo.getDeleted())) {
            fields.add(tableInfo.getDeleted());
        }
        return new SQL() {
            {
                SELECT(fields.toArray(new String[fields.size()]));
                FROM(tableInfo.getUserTableName());
                WHERE(tableInfo.getUserName() + " = '" + userName + "'");
                if (StringUtils.isNotBlank(tableInfo.getDeleted())) {
                    WHERE(tableInfo.getDeleted() + " = 1");
                }
            }
        }.toString();
    }

    /**
     * provide user wrong record empty sql
     *
     * @param userName user name
     * @param tableInfo table information
     * @return sql statement
     */
    static String emptyWrongSQL(String userName, UserTableInfoVO tableInfo) {
        return new SQL() {
            {
                UPDATE(tableInfo.getUserTableName());
                SET(tableInfo.getLoginFailCount() + " = null", tableInfo.getLastLoginFailTime() + " = null");
                WHERE(tableInfo.getUserName() + "='" + userName);
            }
        }.toString();
    }

    /**
     * provide user wrong record update sql
     *
     * @param userName user name
     * @param tableInfo table information
     * @return sql statement
     */
    static String updateWrongSQL(String userName, UserTableInfoVO tableInfo) {
        return new SQL() {
            {
                UPDATE(tableInfo.getUserTableName());
                String setStr1 = tableInfo.getLoginFailCount() + " = if(" + tableInfo.getLoginFailCount() + " is null, 1, " + tableInfo.getLoginFailCount() + " + 1)";
                String setStr2 = tableInfo.getLastLoginFailTime() + " = str_to_date('" + DateUtils.format(new Date()) + "','%Y-%m-%d %H:%i:%s')";
                SET(setStr1, setStr2);
                WHERE(tableInfo.getUserName() + "='" + userName);
            }
        }.toString();
    }
}

