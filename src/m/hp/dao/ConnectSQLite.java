package m.hp.dao;

import m.hp.entity.UserDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectSQLite {

    public UserDataDao userDataDao;


    public ConnectSQLite() throws SQLException, ClassNotFoundException {
        String dbName = "users.db";
        String tableName = "user";
        userDataDao = new UserDataDao(dbName, tableName);
    }

    public List<UserDataEntity> findAll() throws SQLException {
        ResultSet resultSet = userDataDao.selectAll();
        List<UserDataEntity> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                UserDataEntity findUser = new UserDataEntity();
                findUser.setId(resultSet.getLong("uid"));
                findUser.setUserName(resultSet.getString("username"));
                findUser.setCarNumber(resultSet.getString("carnumber"));
                findUser.setLastDate(resultSet.getString("lastdate"));
                findUser.setBuyTime(resultSet.getString("buytime"));
                findUser.setPhone(resultSet.getString("phone"));
                findUser.setCarSerialNumber(resultSet.getString("carserialnumber"));
                findUser.setSyPrice(resultSet.getDouble("syprice"));
                findUser.setJqPrice(resultSet.getDouble("jqprice"));
                findUser.setJcPrice(resultSet.getDouble("jcprice"));
                findUser.setSyRebate(resultSet.getDouble("syrebate"));
                findUser.setJqRebate(resultSet.getDouble("jqrebate"));
                findUser.setJcRebate(resultSet.getDouble("jcrebate"));
                findUser.setCashBack(resultSet.getDouble("cashback"));
                findUser.setType(resultSet.getString("usertype"));
                findUser.setRemarks(resultSet.getString("remarks"));
                userList.add(findUser);
            }
            userDataDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return userList;
    }

    public void insert(UserDataEntity user) throws SQLException {
        userDataDao.insert(user);

    }

    public int update(UserDataEntity users) throws SQLException {
        return userDataDao.update(getUsers(users));
    }

    private UserDataEntity getUsers(UserDataEntity users) {
        UserDataEntity user = new UserDataEntity();
        user.setId(users.getId());
        user.setUserName(users.getUserName());
        user.setCarNumber(users.getCarNumber());
        user.setLastDate(users.getLastDate());
        user.setBuyTime(users.getBuyTime());
        user.setCarSerialNumber(users.getCarSerialNumber());
        user.setSyPrice(users.getSyPrice());
        user.setPhone(users.getPhone());
        user.setJcPrice(users.getJcPrice());
        user.setJqPrice(users.getJqPrice());
        user.setSyRebate(users.getSyRebate());
        user.setJqRebate(users.getJqRebate());
        user.setJcRebate(users.getJcRebate());
        user.setCashBack(users.getCashBack());
        user.setType(users.getType());
        user.setRemarks(users.getRemarks());
        return user;
    }

    public int delete(String name) throws SQLException {
        return userDataDao.delete(name);
    }

    public int deleteAll() throws SQLException {
        return userDataDao.deleteAll();
    }
}
