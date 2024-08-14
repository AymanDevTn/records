package mdn.ayman.MarineRecord.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Year;
import java.util.Map;

import javax.servlet.http.HttpSession;
import mdn.ayman.MarineRecord.model.User;
import mdn.ayman.MarineRecord.services.StatisticsService;
import mdn.ayman.MarineRecord.services.UserService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StatisticsService statisticsService;
    
    @GetMapping("/")
    public String Home(Model model, HttpSession session) {
    	
    	
    	String role= (String) session.getAttribute("role");
    	User user = (User) session.getAttribute("user");
         if (user == null  ) {
              model.addAttribute("user", new User());
        	 return "redirect:/login";
         }
         if (role.compareTo("user")  == 0 ) {
            
       	 return "redirect:/records";
        }
    	
       
        return "redirect:/statistics";
    }
    
    
    
    
    @GetMapping("/statistics")
    public String showStatistics(Model model) {
    	
    	
    	 Map<String, Integer> penaltiesByMonth = statisticsService.getPenaltiesByMonth(Year.now().getValue());
         Map<String, Integer> penaltiesByYear = statisticsService.getPenaltiesByYear();
         Map<String, Integer> maxPenaltiesByBoat = statisticsService.getMaxPenaltiesByBoat();
         Map<String, Integer> maxPenaltiesByPort = statisticsService.getMaxPenaltiesByPort();

    	
         model.addAttribute("penaltiesByMonth_keySet", penaltiesByMonth.keySet()  );
         model.addAttribute("penaltiesByMonth_values", penaltiesByMonth.values()  );
         
         
         model.addAttribute("penaltiesByYear_keySet", penaltiesByYear.keySet());
         model.addAttribute("penaltiesByYear_values", penaltiesByYear.values());
         
         model.addAttribute("maxPenaltiesByBoat_keySet", maxPenaltiesByBoat.keySet());
         model.addAttribute("maxPenaltiesByBoat_values", maxPenaltiesByBoat.values());
         
         model.addAttribute("maxPenaltiesByPort_keySet", maxPenaltiesByPort.keySet());  
         model.addAttribute("maxPenaltiesByPort_values", maxPenaltiesByPort.values());
         
        System.out.println("-------------------"+maxPenaltiesByPort.keySet());
        System.out.println("-------------------"+maxPenaltiesByPort.values());
        
        //
        
        
        List<Map.Entry<String, Integer>> boatEntries = maxPenaltiesByBoat.entrySet().stream()
        		 .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        		.collect(Collectors.toList());
        List<Map.Entry<String, Integer>> portEntries = maxPenaltiesByPort.entrySet().stream()
        		 .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        		.collect(Collectors.toList());

        model.addAttribute("boatEntries", boatEntries);
        model.addAttribute("portEntries", portEntries);
        
        //
        
        return "statistics";
    }
    
    
    
    
    
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session) {
    	
        User existingUser = userService.findByUsername(user.getUsername());
        
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
        	
            session.setAttribute("user", existingUser);
            session.setAttribute("role", existingUser.getRole());
            System.out.println("----"+existingUser.getRole());
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

