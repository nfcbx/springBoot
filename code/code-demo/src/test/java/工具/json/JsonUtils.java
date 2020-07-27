package 工具.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);
        // 取现默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 忽略空bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        // 设置统一的日期格式
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        // 忽略在json字符串中存在，但是在java对象中不存在对应属性的情况，防止错误。
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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




//    parseArray

    public static void main(String[] args) {


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

