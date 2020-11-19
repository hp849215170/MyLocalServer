package m.hp.opt;

import m.hp.dao.ConnectSQLite;
import m.hp.entity.UserDataEntity;

import java.sql.SQLException;
import java.util.List;

public class CSOption {


    public static void insert(UserDataEntity user) {

        ConnectSQLite connectSQLite = null;
        try {
            connectSQLite = new ConnectSQLite();
            connectSQLite.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<UserDataEntity> findAll() {
        try {
            ConnectSQLite connectSQLite = new ConnectSQLite();
            return connectSQLite.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteAll() {
        try {
            ConnectSQLite connectSQLite = new ConnectSQLite();
            connectSQLite.deleteAll();
            connectSQLite.userDataDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
