package com.ripple.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.*;
import com.ripple.pojo.Invocation;
import com.ripple.service.HelloService;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;

public class GsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static Object fromJson(String json, Class clz) {
        return gson.fromJson(json, clz.getClass());
    }


    private static final Gson INSTANCE_TIMESTAMP;
    private static final Gson INSTANCE_DATE;


    static {
        JsonDeserializer<Integer> INTEGER_ADAPTER = new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (StringUtils.isBlank(json.getAsString()) || "null".equals(json))
                    return 0;
                return json.getAsInt();
            }
        };
        JsonDeserializer<Long> LONG_ADAPTER = new JsonDeserializer<Long>() {
            @Override
            public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (StringUtils.isBlank(json.getAsString()) || "null".equals(json))
                    return 0L;
                return json.getAsLong();
            }
        };
        JsonDeserializer<Float> FLOAT_ADAPTER = new JsonDeserializer<Float>() {
            @Override
            public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (StringUtils.isBlank(json.getAsString()) || "null".equals(json))
                    return 0f;
                return json.getAsFloat();
            }
        };
        JsonDeserializer<Double> DOUBLE_ADAPTER = new JsonDeserializer<Double>() {
            @Override
            public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (StringUtils.isBlank(json.getAsString()) || "null".equals(json))
                    return 0d;
                return json.getAsDouble();
            }
        };
        JsonDeserializer<Byte> BYTE_ADAPTER = new JsonDeserializer<Byte>() {
            @Override
            public Byte deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (StringUtils.isBlank(json.getAsString()) || "null".equals(json))
                    return 0;
                return json.getAsByte();
            }
        };
        JsonDeserializer<Short> SHORT_ADAPTER = new JsonDeserializer<Short>() {
            @Override
            public Short deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (StringUtils.isBlank(json.getAsString()) || "null".equals(json))
                    return 0;
                return json.getAsShort();
            }
        };
        JsonDeserializer<BigDecimal> BIGDECIMAL_ADAPTER = new JsonDeserializer<BigDecimal>() {
            @Override
            public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (StringUtils.isBlank(json.getAsString()) || "null".equals(json))
                    return new BigDecimal("0");
                return json.getAsBigDecimal();
            }
        };
        INSTANCE_TIMESTAMP = new GsonBuilder()
                .registerTypeAdapter(int.class, INTEGER_ADAPTER)
                .registerTypeAdapter(Integer.class, INTEGER_ADAPTER)
                .registerTypeAdapter(long.class, LONG_ADAPTER)
                .registerTypeAdapter(Long.class, LONG_ADAPTER)
                .registerTypeAdapter(float.class, FLOAT_ADAPTER)
                .registerTypeAdapter(Float.class, FLOAT_ADAPTER)
                .registerTypeAdapter(double.class, DOUBLE_ADAPTER)
                .registerTypeAdapter(Double.class, DOUBLE_ADAPTER)
                .registerTypeAdapter(byte.class, BYTE_ADAPTER)
                .registerTypeAdapter(Byte.class, BYTE_ADAPTER)
                .registerTypeAdapter(short.class, SHORT_ADAPTER)
                .registerTypeAdapter(Short.class, SHORT_ADAPTER)
                .registerTypeAdapter(BigDecimal.class, BIGDECIMAL_ADAPTER)
                .registerTypeAdapter(Date.class, new TimestampAdapter())
                .create();
        INSTANCE_DATE = new GsonBuilder()
                .registerTypeAdapter(int.class, INTEGER_ADAPTER)
                .registerTypeAdapter(Integer.class, INTEGER_ADAPTER)
                .registerTypeAdapter(long.class, LONG_ADAPTER)
                .registerTypeAdapter(Long.class, LONG_ADAPTER)
                .registerTypeAdapter(float.class, FLOAT_ADAPTER)
                .registerTypeAdapter(Float.class, FLOAT_ADAPTER)
                .registerTypeAdapter(double.class, DOUBLE_ADAPTER)
                .registerTypeAdapter(Double.class, DOUBLE_ADAPTER)
                .registerTypeAdapter(byte.class, BYTE_ADAPTER)
                .registerTypeAdapter(Byte.class, BYTE_ADAPTER)
                .registerTypeAdapter(short.class, SHORT_ADAPTER)
                .registerTypeAdapter(Short.class, SHORT_ADAPTER)
                .registerTypeAdapter(BigDecimal.class, BIGDECIMAL_ADAPTER)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }


    private static class TimestampAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {


        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsLong());
        }


        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }

    public static void main(String[] args) throws Exception {
        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello",
                new Object[]{"this is RPC client"}, new Class[]{String.class});

        String s = JSON.toJSONString(invocation);
        System.out.println(s);
        Invocation invocation1 = (Invocation) JSON.parseObject(s, Invocation.class);
        System.out.println(invocation1.toString());
    }
}
