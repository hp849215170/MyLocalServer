package m.hp.dao;

import m.hp.entity.UserDataEntity;

import java.sql.*;

public class UserDataDao {
    private Connection connection;
    private Statement statement;
    private String tableName;

    public UserDataDao(String dbName, String tableName) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        statement = connection.createStatement();
        this.tableName = tableName;
        createTable();

    }


    private void createTable() throws SQLException {
        statement.executeUpdate("create table if not exists " + tableName + "(uid long,carnumber varchar(64),username varchar (64),lastdate varchar (64),buytime varchar (64)" +
                ",carserialnumber varchar (64),phone varchar (64),syprice double ,jqprice double ,jcprice double ,syrebate double ,jqrebate double " +
                ",jcrebate double ,cashback double ,usertype varchar (64),remarks varchar (64))");
    }

    public int insert(UserDataEntity user) throws SQLException {

        //"(uid,carnumber,username,lastdate,buytime,carserialnumber,phone,syprice,jqprice,jcprice,syrebate,jqrebate,jcrebate,cashback,usertype,remarks) "
        String sql = "insert into "
                + tableName
                + " values( " + user.getId() + "," + "\"" + user.getCarNumber() + "\"" + "," + "\"" + user.getUserName() + "\"" + ", " + "\"" + user.getLastDate() + "\"" + ", " +
                "\"" + user.getBuyTime() + "\"" + ", " + "\"" + user.getCarSerialNumber() + "\"" + "," + "\"" + user.getPhone() + "\"" + "," + user.getSyPrice() + "," +
                user.getJqPrice() + "," + user.getJcPrice() + "," + user.getSyRebate() + "," + user.getJqRebate() + "," + user.getJcRebate() + "," + user.getCashBack() + "," +
                "\"" + user.getType() + "\"" + "," + "\"" + user.getRemarks() + "\"" + ")";
        // System.out.println(sql);

        return statement.executeUpdate(sql);
    }

    public ResultSet selectAll() throws SQLException {
        String sql = "select * from " + tableName;
        return statement.executeQuery(sql);
    }

    public ResultSet selectByUserName(String userName) throws SQLException {
        String sql = "select * from " + tableName + " where username = " + userName;
        return statement.executeQuery(sql);
    }

    public int update(UserDataEntity user) throws SQLException {
        String sql = "update " + tableName + " set carnumber=" + user.getCarNumber() + ",username =" + user.getUserName() + ",lastdate=" + user.getLastDate() + ",buytime=" + user.getBuyTime()
                + ",carserialnumber=" + user.getCarSerialNumber() + ",phone=" + user.getPhone() + ",syprice=" + user.getSyPrice() + ",jqprice=" + user.getJqPrice() + ",jcprice=" + user.getJcPrice()
                + ",syrebate=" + user.getSyRebate() + ",jqrebate=" + user.getJqRebate() + ",jcrebate=" + user.getJcRebate() + ",cashback=" + user.getCashBack() + ",usertype=" + user.getType()
                + ",remarks=" + user.getRemarks() + " where id=" + user.getId();
        return statement.executeUpdate(sql);
    }

    public int delete(String userName) throws SQLException {
        String sql = "delete * from " + tableName + " where username=" + userName;
        return statement.executeUpdate(sql);
    }

    public int deleteAll() throws SQLException {
        String sql = "delete from " + tableName;
        return statement.executeUpdate(sql);
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
