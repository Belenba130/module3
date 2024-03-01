package Repository;

import MySQL.MySQL;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryImp<T, K> implements IRepository<T, K> {


    @Override
    public List<T> getAll(Class<T> entityClass) {
        List<T> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQL.open();
            pst = con.prepareStatement("SELECT * FROM " + entityClass.getAnnotation(Table.class).name());
            rs = pst.executeQuery();
            while (rs.next()) {
                T entity = entityClass.getDeclaredConstructor().newInstance();
                Field[] fields = entityClass.getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                    f.set(entity, rs.getObject(f.getAnnotation(Column.class).name()));
                }
                result.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            MySQL.close(con);
        }
        return result;
    }

    @Override
    public T getById(Class<T> entityClass, K id) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQL.open();
            pst = con.prepareStatement("SELECT * FROM " + entityClass.getAnnotation(Table.class).name() + " WHERE " + entityClass.getDeclaredFields()[0].getAnnotation(Column.class).name() + " = ?");
            while (rs.next()) {
                T entity = entityClass.getDeclaredConstructor().newInstance();
                Field[] fields = entityClass.getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                    f.set(entity, rs.getObject(f.getAnnotation(Column.class).name()));
                }
                return entity;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            MySQL.close(con);
        }
        return null;
    }

    @Override
    public T update(T entity) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQL.open();
            Field[] fields = entity.getClass().getDeclaredFields();
            String tblName = entity.getClass().getAnnotation(Table.class).name();
            String columnName = Arrays.stream(fields).filter(f -> f.getAnnotation(Column.class) != null).map(f -> f.getAnnotation(Column.class).name()).collect(Collectors.joining(","));
            String key = Arrays.stream(fields).filter(f -> f.getAnnotation(Column.class) != null).map(f -> f.getAnnotation(Column.class).name()).collect(Collectors.joining(","));
            String sql = MessageFormat.format("UPDATE %s SET %s WHERE %s = ?", tblName, columnName, key);
            pst = con.prepareStatement(sql);
            pst.setObject(1, entity);
            return entity;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySQL.close(con);
        }
        return null;
    }

    @Override
    public T add(T entity) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            String columns = Arrays.stream(fields).map(f -> f.getAnnotation(Column.class).name()).collect(Collectors.joining(","));
            String values = Arrays.stream(fields).map(f -> "?").collect(Collectors.joining(","));
            con = MySQL.open();
            String sql = MessageFormat.format("INSERT INTO {0}({1}) VALUES ({2})", entity.getClass().getAnnotation(Table.class).name(), columns, values);
            System.out.println(sql);
            int index = 1;
            pst = con.prepareStatement(sql);
            for (Field f : fields) {
                f.setAccessible(true);
                pst.setObject(index++, f.get(entity));
            }
            int result = pst.executeUpdate();
            return entity;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySQL.close(con);
        }
        return null;
    }

    @Override
    public boolean delete(K key, Class<T> entityClass) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = MySQL.open();
            Field[] fields = entityClass.getDeclaredFields();
            String tableName = entityClass.getAnnotation(Table.class).name();
            String keyColumn = Arrays.stream(fields)
                    .filter(f -> f.getAnnotation(Id.class) != null)
                    .map(f -> f.getAnnotation(Column.class).name())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No primary key found"));
            String sql = "DELETE FROM " + tableName + " WHERE " + keyColumn + " = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, key);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySQL.close(conn);
        }
        return false;
    }

}
