package com.ky.tests.page;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageCtrl
{
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name, ModelMap model)
    {
        model.addAttribute("time", new Date());  
        model.addAttribute("message", "今天的风儿有些喧嚣呢。");  
        model.addAttribute("toUserName", name);  
        model.addAttribute("fromUserName", "Kevin Yang"); 
        return "hello";
    }
}
