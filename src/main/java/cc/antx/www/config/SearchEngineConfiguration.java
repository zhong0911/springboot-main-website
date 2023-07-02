package cc.antx.www.config;

import com.alibaba.fastjson.JSONArray;

import java.util.Arrays;

public class SearchEngineConfiguration {
    public static String defaultSearchEngine = "baidu";
    public static JSONArray searchEngines = new JSONArray(Arrays.asList("baidu", "google", "yahoo", "bing", "sogou", "360"));
}
