package cc.antx.www.controller;

import cc.antx.www.config.SearchEngineConfiguration;
import cc.antx.www.utils.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static cc.antx.www.config.SearchEngineConfiguration.defaultSearchEngine;
import static cc.antx.www.config.SearchEngineConfiguration.searchEngines;

@Controller
public class SearchController {

    @RequestMapping(value = "/search")
    public void search(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(required = false, defaultValue = "") String query,
            @RequestParam(required = false, defaultValue = "") String engine
    ) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        query = URLEncoder.encode(query,"UTF-8");
        engine = URLEncoder.encode(engine,"UTF-8");
        try {
            // 获取查询是否存在
            if (query.equals("")) {
                response.sendRedirect(request.getHeader("Referer"));
            }

            // 获取搜索引擎
            String searchEngine;
            if (!engine.equals("")) searchEngine = engine;
            else {
                Cookie[] cookies = request.getCookies();
                if (cookies == null) searchEngine = defaultSearchEngine;
                else searchEngine = CookieUtils.getCookieValue(cookies, "searchEngine");
                searchEngine = !searchEngine.equals("") ? searchEngine : defaultSearchEngine;
            }

            // 判断搜索引擎是否存在
            searchEngine = (searchEngines.contains(searchEngine)) ? searchEngine : defaultSearchEngine;

            // 保存搜索引擎至Cookie
            Cookie searchEngineCookie = new Cookie("searchEngine", searchEngine);
            response.addCookie(searchEngineCookie);

            // 获取搜索引擎连接
            String URL = getSearchEngineURL(searchEngine, query);

            // 链接跳转
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(URL);
            redirectView.setContextRelative(true);
            response.sendRedirect(redirectView.getUrl());
        } catch (Exception e) {
            try (PrintWriter writer = response.getWriter()) {
                writer.write("Server Error!\n");
                writer.write("Code: 500\n");
                writer.write("Message: " + e.getMessage());
                writer.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }


    private static String getSearchEngineURL(String engine, String query) {
        switch (engine) {
            case "google": {
                return "https://www.google.com/search?q=" + query;
            }
            case "bing": {
                return "https://www.bing.com/search?q=" + query;
            }
            case "yahoo": {
                return "https://search.yahoo.com/search?p=" + query;
            }
            case "360": {
                return "https://www.so.com/s?q=" + query;
            }
            case "sogou": {
                return "https://www.sogou.com/web?query=" + query;
            }
            case "baidu":
            default: {
                return "https://www.baidu.com/s?wd=" + query;
            }
        }
    }
}















