package com.nyso.sudian.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.nyso.sudian.model.api.Subject;
import com.nyso.sudian.model.api.SysVer;
import com.nyso.sudian.model.api.UserLablePopup;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;

public class JsonParseUtil {
    private static Gson gson3 = new GsonBuilder()
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            })
            .registerTypeAdapter(int.class, new JsonDeserializer<Integer>() {
                @Override
                public Integer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    if (BBCUtil.isEmpty(jsonElement.getAsString())) {
                        return 0;
                    }
                    return jsonElement.getAsInt();
                }
            })
            .registerTypeAdapter(double.class, new JsonDeserializer<Double>() {
                @Override
                public Double deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    if (BBCUtil.isEmpty(jsonElement.getAsString())) {
                        return 0d;
                    }
                    return jsonElement.getAsDouble();
                }
            })
            .registerTypeAdapter(long.class, new JsonDeserializer<Long>() {
                @Override
                public Long deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    if (BBCUtil.isEmpty(jsonElement.getAsString())) {
                        return 0l;
                    }
                    return jsonElement.getAsLong();
                }
            })
            .create();
    /**
     * 解析版本信息
     *
     * @throws Exception
     */
    public static SysVer parseVersion(String jsonStr)
            throws Exception {
        return gson3.fromJson(jsonStr, SysVer.class);

    }

    /**
     * 解析首页弹框信息
     *
     * @throws Exception
     */
    public static UserLablePopup getUserLablePopup(String jsonStr) {
        try {
            return gson3.fromJson(jsonStr, UserLablePopup.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 解析服务器返回的message字段信息
     *
     * @throws Exception
     */
    public static String getStringValue(String jsonStr, String key) {
        String value = null;
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            value = jsonObj.getString(key);
            if("null".equals(value)){
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            value = null;
        }
        return value;
    }
    /**
     * 解析返回信息是否成功
     */
    public static boolean isSuccessResponse(String jsonStr)
            throws Exception {
        boolean isSuccess = false;
        JSONObject jsonObj = new JSONObject(jsonStr);
        boolean success = jsonObj.getBoolean("success");
        if (success) {
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * 解析服务器返回的msg字段信息
     *
     * @throws Exception
     */
    public static String getMsgValue(String jsonStr) throws Exception {
        JSONObject jsonObj = new JSONObject(jsonStr);
        return jsonObj.getString("message");
    }

    public static <T>T parseObject(String json,Class<T> clazz) {
        return gson3.fromJson(json,clazz);
    }

    /**
     * 解析subject对象信息
     *
     * @throws Exception
     */
    public static Subject parseSubject(String jsonStr)
            throws Exception {
        return gson3.fromJson(jsonStr, Subject.class);
    }

}
