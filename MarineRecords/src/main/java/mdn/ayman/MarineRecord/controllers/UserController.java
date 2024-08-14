package mdn.ayman.MarineRecord.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mdn.ayman.MarineRecord.model.User;
import mdn.ayman.MarineRecord.services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model, HttpSession session) {
    	
    	String role= (String) session.getAttribute("role");
    	System.out.println(role);
    	 User user = (User) session.getAttribute("user");
         if (user == null  ||role.compareTo("user")==0  ) {
        	 System.out.println(user+" user");
             return "redirect:/login";
         }
         
         
        model.addAttribute("users", userService.findAll());
        return "users/list";
        
        
        
    }

    @GetMapping("/users/add")
    public String addUserForm(Model model, HttpSession session) {
    	String role= (String) session.getAttribute("role");
    	System.out.println(role);
    	 User user = (User) session.getAttribute("user");
         if (user == null  ||role.compareTo("user")==0  ) {
        	 System.out.println(user+" user");
             return "redirect:/login";
         }
    	
    	 
         
         
        model.addAttribute("user", new User());
        return "users/add";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute User user, HttpSession session) {
    	
    	
    	String role= (String) session.getAttribute("role");
    	System.out.println(role);
    	 User user1 = (User) session.getAttribute("user");
         if (user1 == null  ||role.compareTo("user")==0  ) {
        	 System.out.println(user1+" user");
             return "redirect:/login";
         }
         
         
        user.setRole(user.getRole());
        userService.save(user);
        return "redirect:/users";
    }
}
