package ttt.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ttt.model.Result;

@FeignClient(value = "zuul")
public interface TestServiceZuul {

    @RequestMapping(value = "/service-config-client/", method = RequestMethod.GET)
    String indexService();

    @RequestMapping(value = "/service-config-client/plus", method = RequestMethod.GET)
    Result plusService(@RequestParam(name = "numA") int numA, @RequestParam(name = "numB") int numB);
}