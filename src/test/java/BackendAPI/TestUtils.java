package BackendAPI;

import com.glassPages.Constants.DatabaseEnums;
import com.glassPages.Constants.Enums;
import com.glassPages.Constants.FilePaths;
import com.glassPages.Constants.SQL_Queries;
import com.glassPages.POM.LoginPage;
import com.glassPages.POM.Requestor1.CreateNewWorkReq;
import com.glassPages.Utility.*;
import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;


public class TestUtils extends ExtentSparkReport{

    public ResultSet resultSet;
    PostgresDatabaseUtility postgresDatabaseUtilityInstance;
    SQLDatabaseUtility sqlDatabaseUtility;
    Map<Object, Object> queryParam;

    @BeforeSuite(alwaysRun = true)
    public void extents() {
        ExtentSparkReport.initialise();
        postgresDatabaseUtilityInstance = PostgresDatabaseUtility.getInstance();
        postgresDatabaseUtilityInstance.setUpPostgresDBConnection();

    }

    public String getValueFromAPIDataJson(String regex) throws FileNotFoundException {
        return JSONUtility.getValueFromJSON(FilePaths.API_REQUEST_DATA, "$."+ regex).toString();
    }


    public void sendRequest(String endpoints,String requestObj) throws FileNotFoundException {
        try {
            File f = new File(FilePaths.API_REQUEST_DATA);
            FileReader fr = new FileReader(f);
            JSONTokener jt = new JSONTokener(fr);
            JSONObject jo = new JSONObject(jt);
            JSONArray loginArray = jo.getJSONArray(requestObj);


            for (int i = 0; i < loginArray.length(); i++) {
                JSONObject loginObject = loginArray.getJSONObject(i);

                given().contentType(ContentType.JSON).body(loginObject.toString())
                        .when().post("http://localhost:8081/api/auth/" + endpoints)
                        .then().statusCode(200).log().all();
            }
        } catch (
                IOException e) {
            extentLogger.info(e.getMessage());
        }
    }
        public String getColValuesFromDB(String column_Name) {
           String val= postgresDatabaseUtilityInstance.fetchValueFromDbCol(column_Name);
           System.out.println("Value of db for column_Name "+ column_Name+ " is " + val);
           return postgresDatabaseUtilityInstance.fetchValueFromDbCol(column_Name);
    }
    public static Map<Object, Object> createQueryToFetchLatestTxn(String tableName, String columnName1, String columnValue ){
        Map<Object, Object> map = new HashMap();
        map.put(DatabaseEnums.TableFields.TABLE_NAME,tableName);
        map.put(DatabaseEnums.TableFields.COLUMN_NAME, columnName1);
        map.put(DatabaseEnums.TableFields.VALUE, columnValue);
        return map;}

    public ResultSet createAndExecuteQuery(String tableName, String columnName, String columnValue) {
        Map<Object, Object> queryParam;
        queryParam = createQueryToFetchLatestTxn(tableName, columnName, columnValue);
        return postgresDatabaseUtilityInstance.executeSelectQuery(SQL_Queries.SELECT_QUERY_WITH_WHERE_CLAUSE, queryParam);}

}
