package ttt.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 通过RestTemplate访问远程testservice，Ribbon会负责把servicename替换成实际要访问的host
  **/
@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "indexError")
    public Object index() {
        return restTemplate.getForObject("http://service-config-client", String.class);
    }

    public Object plus(int numA, int numB) {
        String url = String.format("http://service-config-client/plus?numA=%s&numB=%s", numA, numB);
        return restTemplate.getForObject(url, String.class);
    }

    public Object indexError() {
        return "{\"code\": 999,\"message\": \"服务断路\"}";
    }
}