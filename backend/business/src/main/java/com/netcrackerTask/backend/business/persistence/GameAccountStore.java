package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.DatabaseUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameAccountStore {
    public GameAccountStore() {

    }


    private String getTableName(String className) {
        switch (className) {
            case "Account":
                return "game_account";
            case "Game":
                return "game";
            default:
                return null;
        }

    }

    private String getWhereOption(String tablename, long id) {
        StringBuilder sb = new StringBuilder(" where ");
        switch (tablename) {
            case "game_account":
                sb.append("game_id = ").append(id);
                return sb.toString();
            case "purchase":
                sb.append("user_id = ").append(id);
            default:
                return null;
        }
    }

    private <T> List<T> constructElements(ResultSet resultSet, Class<T> clazz) {
        List<T> elements = new ArrayList<T>();
        Field[] fields = clazz.getDeclaredFields();
        Class[] params = {};
        try {
            while (resultSet.next()) {
                T t = clazz.getConstructor(params).newInstance();

                for (Field field : fields) {
                    field.setAccessible(true);
                    if (resultSet.getObject(field.getName()) instanceof Integer)
                        try {
                            field.set(t, resultSet.getLong(field.getName()));
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            field.set(t, resultSet.getInt(field.getName()));
                        }

                    else if (resultSet.getObject(field.getName()) instanceof String)
                        field.set(t, resultSet.getString(field.getName()));
                    else if (resultSet.getObject(field.getName()) instanceof Boolean)
                        field.set(t, resultSet.getBoolean(field.getName()));

                }

                elements.add(t);
            }


        } catch (SQLException | IllegalArgumentException | InstantiationException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        return elements;

    }

    private <T> T constructElement(ResultSet resultSet, Class<T> clazz){
        Field[] fields = clazz.getDeclaredFields();
        Class[] params = {};

        try {
            T t = clazz.getConstructor(params).newInstance();
            while (resultSet.next()) {


                for (Field field : fields) {
                    field.setAccessible(true);
                    if (resultSet.getObject(field.getName()) instanceof Integer)
                        try {
                            field.set(t, resultSet.getLong(field.getName()));
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            field.set(t, resultSet.getInt(field.getName()));
                        }

                    else if (resultSet.getObject(field.getName()) instanceof String)
                        field.set(t, resultSet.getString(field.getName()));
                    else if (resultSet.getObject(field.getName()) instanceof Boolean)
                        field.set(t, resultSet.getBoolean(field.getName()));

                }


            }
            return t;

        } catch (SQLException | IllegalArgumentException | InstantiationException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public <T> List<T> getAll(Class<T> clazz) {
        try (Statement statement = DatabaseUtils.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder("SELECT * FROM ");
            sb.append(getTableName(clazz.getSimpleName()));
            ResultSet resultSet = statement.executeQuery(sb.toString());
            return constructElements(resultSet, clazz);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> getAllById(Class<T> clazz, long id) {
        try (Statement statement = DatabaseUtils.getConnection().createStatement()) {

            StringBuilder sb = new StringBuilder("SELECT * FROM ");
            String tablename = getTableName(clazz.getSimpleName());
            sb.append(tablename);
            if (tablename != null) {
                if(id!=0){
                    sb.append(getWhereOption(tablename, id));

                }


                ResultSet resultSet = statement.executeQuery(sb.toString());
                return constructElements(resultSet, clazz);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getGameNameById(long id) {
        try (Statement statement = DatabaseUtils.getConnection().createStatement()) {

            StringBuilder sb = new StringBuilder("select name from game where id = ");
            String query = sb.append(id).toString();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public <T> T getById(long id, Class<T> clazz)  {
        try (Statement statement = DatabaseUtils.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder("SELECT * FROM ");
            String tablename = getTableName(clazz.getSimpleName());
            sb.append(tablename);
            if (tablename != null) {
                sb.append(getWhereOption(tablename, id));

                ResultSet resultSet = statement.executeQuery(sb.toString());
                return constructElement(resultSet, clazz);
            } else return null;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Long> getPurchaseIdForUser(long id) {
        try (Statement statement = DatabaseUtils.getConnection().createStatement()) {

            List<Long> res = new ArrayList<>();
            StringBuilder sb = new StringBuilder("select game_account_id from purchase where id = ");
            sb.append(id);



            ResultSet resultSet = statement.executeQuery(sb.toString());
            int i = 0;
            while (resultSet.next()){
                res.add(resultSet.getLong(i++));
            }
            return res;

        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
