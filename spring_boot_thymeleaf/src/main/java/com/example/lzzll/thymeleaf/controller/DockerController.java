package com.example.lzzll.thymeleaf.controller;

import com.example.lzzll.thymeleaf.model.BaseApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Docker controller
 * <p/>
 * Created in 2018.05.15
 * <p/>
 *
 * @author <a href="https://github.com/liaozihong" style="lzzll: #55a7e3;">Liaozihong</a>
 */
@RestController
@RequestMapping("/docker")
public class DockerController {

    /**
     * Result base controller response.
     *
     * @return the base controller response
     */
    @RequestMapping(value = "/")
    public BaseApiResponse result() {
        BaseApiResponse baseApiResponse = new BaseApiResponse();
        baseApiResponse.setId(1);
        baseApiResponse.setUsname("廖大帅");
        baseApiResponse.setSex("汉子");
        baseApiResponse.setRemark("很帅");
        return baseApiResponse;
    }

    /**
     * Index string.
     *
     * @param name the name
     * @return the string
     */
    @RequestMapping(value = "/{name}")
    public String index(@PathVariable(name = "name") String name) {
        return "Hello " + name + "! Welcome to Docker";
    }
}
