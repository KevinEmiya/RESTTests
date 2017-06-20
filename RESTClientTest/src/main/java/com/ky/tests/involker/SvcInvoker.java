package com.ky.tests.involker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/invoke")
public class SvcInvoker
{
    @Autowired
    ClientHttpRequestFactory factory;
    
    @RequestMapping(path = "/timelog")
    public @ResponseBody String getTimeLogs()
    {
        RestTemplate involker = new RestTemplate(factory);
        return involker.getForEntity("https://172.17.99.187:8443/timelog/all", String.class).getBody();
    }
}
