package com.optic.quizdosi013214.parsers;

import com.optic.quizdosi013214.models.Photo;
import com.optic.quizdosi013214.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19/04/2018.
 */

public class JsonPhotos {

    public static List<Photo> getDataJson(String s) throws JSONException {

        JSONArray jsonArray = new JSONArray(s);
        List<Photo> photoList = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);

            Photo photo = new Photo();
            photo.setTitle(item.getString("title"));
            photo.setThumbnailUrl(item.getString("thumbnailUrl"));


            photoList.add(photo);

        }

        return photoList;
    }
}
