package com.powernode.SSM.handler;

import com.powernode.SSM.bean.User;
import com.powernode.SSM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ssmFirstController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/exception",method = RequestMethod.POST)
    public String err(){
        return "index";
    }

    @RequestMapping(value = "/test")
    public String ok(){
        return "ok";
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public User getById(@PathVariable("id") Integer id){
        return userService.getById(id);
    }
}
