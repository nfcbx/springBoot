package toolTest.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        // 对象的所有字段全部列入
//        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 取现默认转换timestamps形式
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 忽略空bean转json的错误，允许序列化空的POJO类，否则会抛出异常
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 设置统一的日期格式
//        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));

        // 忽略在json字符串中存在，但是在java对象中不存在对应属性的情况，防止错误。
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 美化输出
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 允许没有引号的字段名（非标准）
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);

        // 允许单引号（非标准）
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);

        // 按字典表排序
        objectMapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);

        // todo
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        // todo
        objectMapper.findAndRegisterModules();


        LOGGER.info("");
    }


    public static <T> String toJSONString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            LOGGER.error("Jackson Parse Object to String error : {}", e.getMessage(), e);
            return null;
        }
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) json : objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            LOGGER.error("Jackson Parse String to Object error : {}", e.getMessage(), e);
            return null;
        }
    }

    public static <T> T parseObjectByType(String json, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(json) || typeReference == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            LOGGER.error("Jackson Parse String to Object error : {}", e.getMessage(), e);
            return null;
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (StringUtils.isBlank(text) || clazz == null) {
            return null;
        }
        try {
            return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
//            return objectMapper.readValue(text, new TypeReference<List<T>>() {});
        } catch (IOException e) {
            LOGGER.error("Jackson Parse String to List error : {}", e.getMessage(), e);
            return null;
        }
    }


    public static void main(String[] args) {
        HashMap<Object, Object> data1 = Maps.newHashMap();
        data1.put(1, 2);
        data1.put(11, 22);


        HashMap<Object, Object> data2 = Maps.newHashMap();
        data2.put("a", "ceshi");
        data2.put("b", new Date());

        ArrayList<HashMap<Object, Object>> list = Lists.newArrayList(data1, data2);

        String json = JsonUtils.toJSONString(data1);

        System.out.println(json);


        String jsons = JsonUtils.toJSONString(list);

        System.out.println(jsons);

        Map map = JsonUtils.parseObject(json, Map.class);

        System.out.println(map);

        List<Map> maps = JsonUtils.parseArray(jsons, Map.class);
        System.out.println(maps);

        Map map1 = maps.get(1);
        System.out.println(map1);
        System.out.println(map1.get("b"));
        System.out.println(map1.get("b") instanceof Date);


        UserVO userVO = new UserVO();
        userVO.setId(100);
        userVO.setName("zhao");
        userVO.setAge(22);
        userVO.setBirthday(new Date());
        userVO.setSuccess(false);

        jsons = JsonUtils.toJSONString(userVO);

        System.out.println(jsons);

        UserVO userVO1 = JsonUtils.parseObject(jsons, UserVO.class);

        System.out.println(userVO1);
        System.out.println(JSON.toJSONString(userVO1));


        UserVO userVO2 = new UserVO();
        userVO2.setId(99999);
        userVO2.setName("shuxue");
        userVO2.setAge(66);
        userVO2.setBirthday(new Date());
        userVO2.setSuccess(true);


        ArrayList<UserVO> userVOS = Lists.newArrayList(userVO, userVO1, userVO2);

        userVO2.setUsers(JSONArray.parseArray(JSON.toJSONString(userVOS), UserVO.class));

        System.out.println("fastjson 打印");
        System.out.println(JSON.toJSONString(userVOS));

        jsons = JsonUtils.toJSONString(userVOS);

        System.out.println(jsons);

        List<UserVO> userVOList = JsonUtils.parseArray(jsons, UserVO.class);

        System.out.println(JSON.toJSONString(userVOList));


        String str = "{\"aaaa\":false,\"bbbbb\":false,\"userAgent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36\",\"num\":\"\",\"fp\":\"\",\"supportId\":\"0\"}";

        try {
            HashMap<String, Object> hashMap = objectMapper.readValue(str, new TypeReference<HashMap<String, Object>>() {
            });
            System.out.println(hashMap);
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                System.out.println(entry.getKey() + "  :  " + entry.getValue());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Map<String, String> stringStringMap = JsonUtils.parseObjectByType(str, new TypeReference<LinkedHashMap<String, String>>() {
        });
        System.out.println(stringStringMap);

    }


}

