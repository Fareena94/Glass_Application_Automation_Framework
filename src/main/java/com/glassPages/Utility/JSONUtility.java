package com.glassPages.Utility;


import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.Iterator;

/**
 * This Utility is used to fetch and manipulate  the data(arrays/objects etc.,)  from .json files.
 * Methods - getValueFromJson(),fetchJSONValueFromKey(), jsonSchemaValidation() , getValueFromJSON()
 * @author - Vanshika Chauhan
 * @version 1.0
 */
public class JSONUtility {

    public static String getValueFromJson(String filePath, String key) {
        String jsonTxt = null;
        File f = new File(filePath);
        if (f.exists()) {
            InputStream is = null;
            try {
                is = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                jsonTxt = IOUtils.toString(is, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //  System.out.println(jsonTxt);
        }
       return fetchJSONValueFromKey(jsonTxt, key);
    }


    private static String fetchJSONValueFromKey(String jsonReq, String key) {

        JSONObject json = new JSONObject(jsonReq);
        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;
        String val = "";
        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if (json.get(nextKeys) instanceof JSONObject) {
                        return fetchJSONValueFromKey(json.getJSONObject(nextKeys).toString(), key);
                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonArray = json.getJSONArray(nextKeys);
                        int i = 0;
                        if (i < jsonArray.length()) do {
                            String jsonArrayString = jsonArray.get(i).toString();
                            json = new JSONObject(jsonArrayString);
                            return fetchJSONValueFromKey(json.toString(), key);
                        } while (i < jsonArray.length());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            val = json.get(key).toString();
        }
        System.out.println("Value is " + val);

        return val;
    }

    /**
     * This function will be used for API validations.
     * It takes jsonSchema file path and String response as parameter .
     * Use online site "https://jsonformatter.org/json-to-jsonschema" to generate schema of your response json.
     * This will validate all the parameters and data type of param-values as mentioned in the shcema.
     * In case the validation fails it throws an Exception.
     * @param jsonSchemaPath
     * @param response
     */
    public static void jsonSchemaValidation(String jsonSchemaPath, String response) {
        JSONTokener schemaData, jsonData;
        JSONObject jsonSchema, json;
        File schemaFile;
        Schema schema;
        schemaFile = new File(jsonSchemaPath);
        try {
            schemaData = new JSONTokener(new FileInputStream(schemaFile));
        } catch (Exception e) {
            throw new RuntimeException("Json schema file not found, please check!");
        }
        jsonSchema = new JSONObject(schemaData);
        jsonData = new JSONTokener(response);
        json = new JSONObject(jsonData);
        schema = SchemaLoader.load(jsonSchema);
        schema.validate(json);
    }

    /**
     * This function will fetch the json value from the key provided in regex present the json data file provided.
     * @param jsonFilePath
     * @param regEx
     * @return  json data by reading the file
     */


    public static Object getValueFromJSON(String jsonFilePath, String regEx) throws FileNotFoundException {
        File schemaFile = new File(jsonFilePath);
        JSONTokener schemaData = new JSONTokener(new FileInputStream(schemaFile));
        JSONObject json = new JSONObject(schemaData);
        return JsonPath.read(json.toString(), regEx);
    }
}

