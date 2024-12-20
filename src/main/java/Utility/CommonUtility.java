package Utility;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.apache.commons.lang.RandomStringUtils;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;

/**
 * This Utility contains all other utilities  which will be common across multiple POM files and multiple projects in the organization.
 * Methods - readPropertyfile(), writePropertyFile(), dbValidation(), findCollectionFromDb(),
 * findRowBasedOnColumn(), closeConnection(), sortWithcolumnName(), generateRandomNumber(), generateRandomString(),
 * getCaptchaText(), captureScreenshot(), runAutoITScript()
 * @author Vanshika Chauhan, Vidhushi
 * @version 1.0
 */
public class CommonUtility {
    private static Random random;  // SecureRandom is preferred to Random

    /**
     * readPropertyfile()- This method reads file path from the properties file
     * @param propertyFilePath
     * @return propertyFilePath
     * @author Vanshika Chauhan
     * @version 1.0
     */
    public static Properties readPropertyFile(String propertyFilePath) {

        Properties p = null;
        FileReader reader = null;

        try {
            reader = new FileReader(propertyFilePath);
            p = new Properties();
            p.load(reader);

        } catch (Exception  e) {
            System.out.println(e.getMessage());
        }
        return p;
    }



    /**
     * writePropertyFile() - This method writes propertyKey, propertyValue and propertyFilePath to the properties file.
     * @param propertyKey
     * @param propertyValue
     * @param propertyFilePath
     * @return database
     * @author - Vanshika Chauhan
     * @version 1.0
     */
    public static void writePropertyFile(String propertyKey, String propertyValue, String propertyFilePath) {
        File f = new File(propertyFilePath);
        PropertiesConfiguration config = new PropertiesConfiguration();
        PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
        try {
            layout.load(new InputStreamReader(new FileInputStream(f)));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        config.setProperty(propertyKey, propertyValue);
        try {
                layout.save(new FileWriter(propertyFilePath, false));
            } catch (org.apache.commons.configuration.ConfigurationException e) {
                e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * generateRandomNumber() - This method generates random number based on the length range provided.
     * @param length
     * @return random number from the range.
     * @author - Vanshika Chauhan
     * @version 1.0
     */
    public static int generateRandomNumber(int length) {
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length); // bound is exclusive
        random = new Random();
        return random.nextInt(max - min) + min;

    }

    /**
     * generateRandomString() - This method generates random String based on the length range provided.
     *
     * @param length
     * @return
     * @author - Vanshika Chauhan
     * @version 1.0
     */
    public static String generateRandomString(int length) {
        System.out.println(RandomStringUtils.randomAlphabetic(length));
        return null;
    }

    /**
     * This method captures the imageElement which displays captcha then after capturing
     * the screenshot of the imageelement.With the help of Tessract interface, the
     * doOCR method of tessract interface provides character recognition support for common
     * image formats and then text is replaced with all the spaces it has(only the digits or the
     * characters will be left in the captcha text)
     *
     * @return
     * @author:Vidushi
     */


    /**
     * runAutoITScript() - This method helps in running the AutiITScript from the provided  autoitExeFilePath.
     * @param autoitExeFilePath
     * @author - Vanshika Chauhan
     * @version 1.0
     */
    public static void runAutoITScript(String autoitExeFilePath){
        try {
            Runtime.getRuntime().exec(autoitExeFilePath + " " + "D:\\Projects\\ParagonAutomationFramework\\ParagonAutomationFramework\\extentReportsOutput\\htmlReports\\10-05-2022 12-40-57-4057.html" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
