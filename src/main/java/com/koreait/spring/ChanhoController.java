package com.koreait.spring;

import com.koreait.spring.vo.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChanhoController {

    @Autowired
    private ChanhoService service;

    @GetMapping("/")
    public String main(Model model){

        model.addAttribute("locationList", service.selLocationCodeList());

        return "main";
    }

    @GetMapping("/result")
    public String result(){
        return "";
    }


    @PostMapping("/result")
    public String result(SearchDTO param){
        System.out.println("mon" + param.getDeal_month());
        System.out.println("year"+param.getDeal_year());
        System.out.println("지역"+param.getExternal_cd());
        service.saveData(param);
        return "redirect:/result";

    }




}
