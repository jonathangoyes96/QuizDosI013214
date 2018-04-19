package com.optic.quizdosi013214.parsers;

import com.optic.quizdosi013214.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19/04/2018.
 */

public class JsonUsers {
    public static List<User> getDataJson(String s) throws JSONException {

        JSONArray jsonArray = new JSONArray(s);
        List<User> userList = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);

            User user = new User();
            user.setName(item.getString("name"));
            user.setUsername(item.getString("username"));
            user.setEmail(item.getString("email"));

            JSONObject addressObject = item.getJSONObject("address");
            user.setStreet(addressObject.getString("street"));

            userList.add(user);

        }

        return userList;
    }

}
