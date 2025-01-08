package BackendAPI;


import java.io.FileNotFoundException;

import com.glassPages.Constants.Enums;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkingWithBackend extends TestUtils {



	@Test(testName = "login", description = "Verifying the user login")
	public void login() throws FileNotFoundException {
		createAndExecuteQuery(Enums.Testing_glass_DB_Names.WORK_REQUESTS.getValue(),Enums.SQL_DB_ColumnNames.USERNAME.toString(),getValueFromAPIDataJson("login_data[0].username"));
		Assert.assertEquals(getValueFromAPIDataJson("login_data[0].username"), getColValuesFromDB("username"));

	}
	@Test(testName = "register", description = "Verifying the user login")
	public void register() throws FileNotFoundException {
		sendRequest("register","register_data");

	}


//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("username", "ansh.admin");
//		map.put("password", "password123");
//
//		given().contentType(ContentType.JSON).body(map)
//		.when().post("http://localhost:8081/api/auth/login")
//		.then().statusCode(200).log().all();
//
//
//
//
//
//		// registering the user
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("username", "Nitish1");
//		map1.put("email", "nitish111@gmail.com");
//		map1.put("password", "Nitish@111");
//		map1.put("role", "ADMIN");
//		map1.put("firstName", "Nitish1");
//		map1.put("lastName", "Sharma1");
//
//
//
//		given().contentType(ContentType.JSON).body(getValueFromAPIDataJson("data[0]"))
//		.when().post("http://localhost:8081/api/auth/register")
//		.then().assertThat().statusCode(200).log().all();
//
//
//		// Taking data from json file
//
//		File f = new File("C:\\Users\\nitish.s.wissen\\Glass-Application-Automation\\Glass_Application_Automation_Framework\\src\\test\\java\\BackendAPI\\Data.json");
//		FileReader fr = new FileReader(f);
//		JSONTokener jt = new JSONTokener(fr);
//		JSONObject jo = new JSONObject(jt);
//
//		given().contentType(ContentType.JSON).body(jo.toString())
//				.when().post("http://localhost:8081/api/auth/register")
//				.then().statusCode(200).log().all();
//
//
//
//
//		// setpassword
//
//		HashMap hm = new HashMap();
//		hm.put("username","Nitish1");
//		hm.put("password","")














}

