package com.example.itforumspring.controllers;
import com.example.itforumspring.Service.CustomAnswerService;
import com.example.itforumspring.Service.CustomQuestionService;
import com.example.itforumspring.bdclass.Answers;
import com.example.itforumspring.bdclass.Quastion;
import com.example.itforumspring.bdclass.Role;
import com.example.itforumspring.bdclass.Users;
import com.example.itforumspring.Service.CustomUserDetailsService;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class LoginController {
    public static Boolean Authentication;
    public static Users usersAuth;
    public static Quastion question;
    @Autowired
    private CustomUserDetailsService userService;
    @Autowired
    private CustomQuestionService questionService;
    @Autowired
    private CustomAnswerService answersService;
    @RequestMapping(value={"/home/Users"}, method=RequestMethod.GET)
    public ModelAndView Users(Model model)
    {
        List<Users> users;
        users = userService.findAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        if(Authentication)
        {
            modelAndView.addObject("Users",usersAuth);
            modelAndView.addObject("Auth", true);
        }
        else
        {
            modelAndView.addObject("Auth", false);
        }
        modelAndView.addObject("Usersw",users);
        modelAndView.setViewName("users");
        return modelAndView;
    }
    @RequestMapping(value={"/home/questions"}, method=RequestMethod.GET)
    public ModelAndView questions(Model model)
    {
        List<Quastion> Quastion = null;
        Quastion = questionService.sortByDeskId();
        for(Quastion questions:Quastion)
        {
            System.out.println(questions.getId());
        }
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("Quastion", Quastion);
        if(Authentication)
        {
            modelAndView.addObject("Users",usersAuth);
            modelAndView.addObject("Auth", true);
        }
        else
        {
            modelAndView.addObject("Auth", false);
        }
        modelAndView.setViewName("questions");
        return modelAndView;
    }
    @RequestMapping(value = "/home/profile",method = RequestMethod.GET)
    public ModelAndView profile(@RequestParam("id") long id)
    {

        ModelAndView modelAndView = new ModelAndView();
        Users user = userService.findUserByID(id);
        System.out.println(user.getEmail());
        if(Authentication)
        {
            modelAndView.addObject("UsersAuth",usersAuth);
            modelAndView.addObject("Auth", true);
        }
        else
        {
            modelAndView.addObject("Auth", false);
        }
        modelAndView.addObject("User",user);
        modelAndView.setViewName("profile");
        return modelAndView;
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(String login)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        Users user = new Users();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Users user, BindingResult bindingResult,
                                      @RequestParam("file")MultipartFile img,
                                      @RequestParam("post") String post,
                                      @RequestParam("Nickname") String nickname,
                                      @RequestParam("inlineRadioOptions") String male ) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(img.getOriginalFilename());
        System.out.println("Должность "+post);
        System.out.println("пол "+male);
        String MFemale;
        if(male.equals("option1"))
        {
            MFemale="М";
        }
        else
        {
            MFemale="Ж";
        }
        if (!img.isEmpty()) {
            try {
                byte[] bytes = img.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream("src/main/resources/static/img/"+img.getOriginalFilename()));
                stream.write(bytes);
                stream.close();
                System.out.println("Загрузка удалась");
            } catch (Exception e) {
                System.out.println("Загрузка не удалась"+e.getMessage());
            }
        } else {
            System.out.println("Загрузка не удалась файл пустой");
        }
        Users userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {
            user.setURLimage("/img/"+img.getOriginalFilename());
            user.setNickname(nickname);
            user.setPost(post);
            user.setMFemale(MFemale);
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Users());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
    @RequestMapping(value = "/home/AddQuestion",method = RequestMethod.GET)
    public ModelAndView AddQuestion()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("questionAdd");
        return modelAndView;
    }
    @RequestMapping(value="/home/AddQuestion",method = RequestMethod.POST)
    public String Question(@Valid Quastion quastion,
                                 @RequestParam("nameQuestion") String nameQuestion,
                                 @RequestParam("tags") String tags,
                                 @RequestParam("description") String description,
                                 @RequestParam("code") String code)
    {
        quastion.setNameQuestion(nameQuestion);
        quastion.setTags(tags.split(";"));
        quastion.setDescription(description);
        quastion.setCode(code);
        questionService.saveQuestion(quastion,usersAuth);
        return "redirect:/home";
    }
    @RequestMapping(value = "/home/correct",method = RequestMethod.GET)
    public String CorrectAnswer(@RequestParam("id") long id)
    {
        answersService.UpdateAnswers(id);
        return "redirect:/home/questionSelect?id="+question.getId();
    }
    @RequestMapping(value = "/home/questionSelect", method = RequestMethod.GET)
    public  ModelAndView questionSelect(@RequestParam("id") long id)
    {
        ModelAndView modelAndView = new ModelAndView();
        question = questionService.findByIdQuestion(id);
        List<Answers> answers = answersService.findAnswersbyQuestion(id);
        for (Answers answer : answers)
        {
            System.out.println(answer.getDescriptionAnswers());
            for(Users user:answer.getUser())
            {
                System.out.println(user.getEmail());
            }
        }
        if(Authentication)
        {
            modelAndView.addObject("Users",usersAuth);
            modelAndView.addObject("Auth", true);
        }
        else
        {
            modelAndView.addObject("Auth", false);
        }
        modelAndView.addObject("Question",question);
        modelAndView.addObject("AnswersOnQuestion",answers);
        modelAndView.setViewName("questionSelect");
        return modelAndView;
    }
    @RequestMapping(value ="/home/questionSelect",method = RequestMethod.POST)
    public String Answers(@Valid Answers answers,
                          @RequestParam("descriptionAnswer") String descriptionAnswer,
                          @RequestParam("CodeAnswer") String CodeAnswer)
    {

        answers.setDescriptionAnswers(descriptionAnswer);
        answers.setCodeAnswers(CodeAnswer);
        answersService.SaveAnswer(answers,question,usersAuth);
        return "redirect:/home/questionSelect?id="+question.getId();
    }
    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String greetingForm(@RequestParam(name="search", defaultValue = "test") String search, Model model) {
        List<Quastion> Quastion = null;
        System.out.println(search);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null)
        {
            Users users = userService.findUserByEmail(auth.getName());
            if(users!=null)
            {

                Authentication=true;
                usersAuth = users;
                model.addAttribute("Users",usersAuth);
                model.addAttribute("Auth",Authentication);
            }
            else
            {
                Authentication=false;
                model.addAttribute("Auth",Authentication);
                model.addAttribute("disabledButton",null);
            }
        }
        if(search.equals("test")) {
            Quastion = questionService.sortByDeskId();
        }
        else{
            Quastion = questionService.findQuestionByTags(search);
            model.addAttribute("isSearch",true);
        }
        model.addAttribute("Quastion", Quastion);
        return "home";
    }
    @RequestMapping(value="/home", method=RequestMethod.POST)
    public String greetingSubmit(@RequestParam(name="search", defaultValue = "test") String search,
                                 RedirectAttributes redirect, Model model) {
        redirect.addAttribute("search",search);
        return "redirect:/home";
    }
}
