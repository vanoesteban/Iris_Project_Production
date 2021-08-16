package Controllers;

import java.io.File;


public class Global_Vars {
    //browser defaults
    public static final String BROWSER = "edge";
    public static final String PLATFORM = "Windows 10";
    public static final String ENVIRONMENT = "Production";
    public static String DEF_BROWSER = null;
    public static String DEF_PLATFORM = null;
    public static String DEF_ENVIRONMENT = null;

    // GLOBAL VARIABLES
    public static String SUITE_NAME = null;
    public static final String TARGET_URL = "https://www.amazon.com";
    public static final String PAGE_TITLE = "Amazon.com. Spend less. Smile more.";
    public static final String EMPLOYEESS_API = "http://dummy.restapiexample.com/api/v1/employees";
    public static final String EMPLOYEES_API_1 = "http://dummy.restapiexample.com/api/v1/employee/1";
    public static final String propFile = "src/main/resources/selenium.properties";
    public static final String SE_PROPS = new File(propFile).getAbsolutePath();
    public static final String Employee_FILE_PATH = Global_Vars.FILES_PATH + "Employees.json";
    public static final String EF_PROPS = new File(Employee_FILE_PATH).getAbsolutePath();

    //DATABASE VARIABLES
    public static final String HOST = "jdbc:mysql://localhost:3036/emp";
    public static final String username = "root";
    public static final String password = "root";
    public static final String query_example = "Select * From Table;";

    // FOLDERS
    public static final String TEST_OUTPUT_PATH = "src/Outputs/";
    public static final String FILES_PATH = TEST_OUTPUT_PATH + "Files/";
    public static final String LOGFILE_PATH = TEST_OUTPUT_PATH + "Logs/";
    public static final String REPORT_PATH = TEST_OUTPUT_PATH + "Reports/";

    //TIMEOUTS
    public static final int TIMEOUT_WAIT = 3;
    public static final int TIMEOUT_ELEMENT = 5;

}
