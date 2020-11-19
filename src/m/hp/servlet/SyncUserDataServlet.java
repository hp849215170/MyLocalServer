package m.hp.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import m.hp.entity.UserDataEntity;
import m.hp.entity.UserJsonRoot;
import m.hp.opt.CSOption;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SyncUserDataServlet", urlPatterns = "/SyncUserDataServlet")
public class SyncUserDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sendRequest(request, response);
    }

    protected void sendRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();//向客户端发送数据

        String remoteAddr = request.getRemoteAddr();

        System.out.println(remoteAddr + "发来的消息");

        if (remoteAddr.equals("127.0.0.1")) {
            CSOption.deleteAll();
        }

        //获取浏览器发来的消息
        String browerOption = request.getParameter("browerOption");
        response.setCharacterEncoding("UTF-8");
        System.out.println("浏览器发来的消息browerOption----" + browerOption);

        //获取客户端发来的消息
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        System.out.println("客户端发来的消息----" + s);
        if (s == null) {
            return;
        }
        JSONObject parseObject = JSON.parseObject(s);
        String option = parseObject.getString("option");

        List<UserDataEntity> userList = null;
        if (option.equals("insert")) {
            List<UserDataEntity> allUserData = CSOption.findAll();
            userList = new ArrayList<>();
            boolean theSame = false;
            JSONArray jsonArray = parseObject.getJSONArray("userList");
            for (int i = 0; i < jsonArray.size(); i++) {
                Integer id = jsonArray.getJSONObject(i).getInteger("id");
                String buyTime = jsonArray.getJSONObject(i).getString("buyTime");
                String carNumber = jsonArray.getJSONObject(i).getString("carNumber");
                String carSerialNumber = jsonArray.getJSONObject(i).getString("carSerialNumber");
                String lastDate = jsonArray.getJSONObject(i).getString("lastDate");
                String type = jsonArray.getJSONObject(i).getString("type");
                String userName = jsonArray.getJSONObject(i).getString("userName");
                String phone = jsonArray.getJSONObject(i).getString("phone");
                String remark = jsonArray.getJSONObject(i).getString("remarks");
                Double cashBack = jsonArray.getJSONObject(i).getDouble("cashBack");
                Double jcPrice = jsonArray.getJSONObject(i).getDouble("jcPrice");
                Double jcRebate = jsonArray.getJSONObject(i).getDouble("jcRebate");
                Double jqPrice = jsonArray.getJSONObject(i).getDouble("jqPrice");
                Double jqRebate = jsonArray.getJSONObject(i).getDouble("jqRebate");
                Double syPrice = jsonArray.getJSONObject(i).getDouble("syPrice");
                Double syRebate = jsonArray.getJSONObject(i).getDouble("syRebate");

                for (int j = 0; j < allUserData.size(); j++) {
                    if (carSerialNumber.equals(allUserData.get(j).getCarSerialNumber())) {
                        theSame = true;
                        break;
                    } else {
                        System.out.println("车架号：" + carSerialNumber + "重复");
                        theSame = false;
                    }
                }
                if (!theSame) {
                    UserDataEntity user = new UserDataEntity(id, carNumber, userName, lastDate, buyTime, carSerialNumber, phone, syPrice, jqPrice, jcPrice
                            , syRebate, jqRebate, jcRebate, cashBack, type, remark);
                    userList.add(user);
                }
            }

            int count = 0;
            for (int i = 0; i < userList.size(); i++) {
                CSOption.insert(userList.get(i));
                count++;
            }
            if (count == 0) {
                //writer.println(URLEncoder.encode("云端数据已经是最新了", "utf-8"));
                writer.println("云端数据已经是最新了");
            } else {
                writer.println("上传成功" + count + "条数据");
                // writer.println(URLEncoder.encode("上传成功" + i + "条数据", "utf-8"));
            }
            //System.out.println("添加了" + i + "条数据");
        } else if (option.equals("findAll")) {
            List<UserDataEntity> allUserData = CSOption.findAll();
            UserJsonRoot userJsonRoot = new UserJsonRoot();
            userJsonRoot.setOption("findAll");
            userJsonRoot.setUserList(allUserData);
            String allUsesJson = JSONArray.toJSONString(userJsonRoot);
            //发送数据
            writer.println(allUsesJson);
            // writer.println(URLEncoder.encode(allUsesJson,"utf-8"));
        }
    }
}
