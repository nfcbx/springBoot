package 工具.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.ptg.AttrPtg;
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
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        // 取现默认转换timestamps形式
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 忽略空bean转json的错误，允许序列化空的POJO类，否则会抛出异常
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 设置统一的日期格式
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));

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

    public static <T> T parseObject(String str, Class<T> clazz) {
        if (StringUtils.isBlank(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            LOGGER.error("Jackson Parse String to Object error : {}", e.getMessage(), e);
            return null;
        }
    }

    public static <T> List<T> parseArray(String str, Class<T> clazz) {
        if (StringUtils.isBlank(str) || clazz == null) {
            return null;
        }
        try {
            return objectMapper.readValue(str, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            LOGGER.error("Jackson Parse String to Object error : {}", e.getMessage(), e);
            return null;
        }
    }


//    parseArray


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


    }

    public static void main2(String[] args) {


        ObjectMapper mapper = new ObjectMapper();


        HashMap<Object, Object> map = Maps.newHashMap();
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        String value = null;
        try {
            value = mapper.writeValueAsString(map);
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Map data = mapper.readValue(value, Map.class);
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

