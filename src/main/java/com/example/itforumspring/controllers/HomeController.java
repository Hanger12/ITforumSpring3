package com.example.itforumspring.controllers;

import com.example.itforumspring.bdclass.Quastion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {
    @Autowired
    private com.example.itforumspring.QuastionRepository QuastionRepository;

    @Autowired
    private com.example.itforumspring.customInterface customInterface;

    @RequestMapping(value={"/","/greeting"}, method= RequestMethod.GET)
    public String greetingForm(@RequestParam(name="search", defaultValue = "test") String search, Model model) {
        List<Quastion> Quastion = null;
        System.out.println(search);
        if(search.equals("test")) {
            Quastion = this.QuastionRepository.findAll();
        }
        else{
            Quastion = this.customInterface.findByTags(search);
            model.addAttribute("isSearch",true);
        }

        model.addAttribute("Quastion", Quastion);
        return "result";
    }

    @RequestMapping(value="/greeting", method=RequestMethod.POST)
    public String greetingSubmit(@RequestParam(name="search", defaultValue = "test") String search,
                                 RedirectAttributes redirect,Model model) {
        redirect.addAttribute("search",search);

        return "redirect:/greeting";
    }
    @RequestMapping(value = "/submitForm",method = RequestMethod.POST)
    public String submitForm(@RequestParam(name="UrlAvatar", defaultValue = "") String UrlAvatar, ModelMap modelMap){
        if(!UrlAvatar.equals(""))
        {
            System.out.println(UrlAvatar);
        }
        return "redirect:/greeting";
    }

}
