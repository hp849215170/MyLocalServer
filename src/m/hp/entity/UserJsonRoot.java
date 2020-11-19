package m.hp.entity;

import java.util.ArrayList;
import java.util.List;

public class UserJsonRoot {
    String option;
    List<UserDataEntity> userList = new ArrayList<>();

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<UserDataEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDataEntity> userList) {
        this.userList = userList;
    }
}
