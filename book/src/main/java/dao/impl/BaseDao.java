package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Rookie
 * @Date 2021/6/20
 */
public abstract class BaseDao {
    QueryRunner queryRunner = new QueryRunner();
    public int update(String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return -1;
    }
    public <T> T queryForOne(Class<T> type, String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
    public <T> List<T> queryForList(Class<T> type, String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
    public Object queryForSingleValue(String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
}
