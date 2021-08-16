package Controllers;

import io.restassured.RestAssured;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.*;

public class DataController {

    public DataController(){}


    public void Get_Api_Append_Json() throws IOException {
        File objFile = new File(Global_Vars.EF_PROPS);
        //given().get(API_URL).then().statusCode(200).body("data.employee_name[0]", equalTo("Tiger Nixon"));
        String api_response = RestAssured.get(Global_Vars.EMPLOYEESS_API).getBody().asString();

        try {
            if (objFile.createNewFile()) {
                System.out.println("***File Created***");
                FileWriter myWriter = new FileWriter(Global_Vars.EF_PROPS);
                myWriter.write(api_response);
                myWriter.close();
                System.out.println("***You Wrote to the File***");
            } else {
                System.out.println("File Already Exist");
            }
        }catch (IOException e){
        System.out.println("Something bad happen: ");
        e.printStackTrace();
        }
    }
    /**
     * fetchData method to retrieve test data for specified method
     *
     * @throws Exception
     * @return
     */
    @DataProvider(name = "fetchData_JSON")
    public Object[] fetchData(Method method) throws Exception {

        FileReader reader = new FileReader(Global_Vars.EF_PROPS);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        JSONObject dataobj = (JSONObject) obj;
        JSONArray dataArray = (JSONArray) dataobj.get("data");


        String arrayResult[]= new String[dataArray.size()];
        String FinalResult[] = new String[1];

        for (int i = 0; i<dataArray.size(); i++){
            JSONObject blockData = (JSONObject) dataArray.get(i);
            arrayResult[i] = (String) blockData.get("employee_name");
        }

        switch(method.getName()){
            case "tc002_navigate_echo_support":
                FinalResult[0] = arrayResult[0];

        }

        return  FinalResult;
    }

    public void connect_to_database() throws SQLException, ClassNotFoundException {
        //string
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(Global_Vars.HOST, Global_Vars.username, Global_Vars.password);
        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery(Global_Vars.query_example);
        System.out.println("Database: " + result);
        connection.close();
    }


}
