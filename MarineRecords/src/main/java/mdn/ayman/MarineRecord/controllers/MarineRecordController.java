package mdn.ayman.MarineRecord.controllers;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mdn.ayman.MarineRecord.model.MarineRecord;
import mdn.ayman.MarineRecord.model.User;
import mdn.ayman.MarineRecord.services.MarineRecordService;
import mdn.ayman.MarineRecord.services.UserService;



@Controller
public class MarineRecordController {
    @Autowired
    private MarineRecordService service;

    @GetMapping("/records")
    public String listRecords(Model model, HttpSession session) {
    	
    	String role= (String) session.getAttribute("role");
    	User user = (User) session.getAttribute("user");
         if (user == null  ) {
             return "redirect:/login";
         }
         model.addAttribute("userRole", role); 
        model.addAttribute("records", service.findAll());
        return "records/list";
    }
    
    @GetMapping("/record/query")
    public String queryRecord(@RequestParam("query") String query,Model model, HttpSession session) {
    	String role= (String) session.getAttribute("role"); 
    	
    	if (session.getAttribute("user") == null) {
             return "redirect:/login";
         }
    	 model.addAttribute("userRole", role);
         
         
       
         model.addAttribute("records", service.searchItemsByName(query));
          
    	 
         return "records/list";
    }
    @GetMapping("/records/{id}")
    public String viewRecord(@PathVariable Long id, Model model, HttpSession session) {
    	String role= (String) session.getAttribute("role");
    	model.addAttribute("userRole", role);
    	model.addAttribute("record", service.findById(id));
        return "records/view";
    }

    @GetMapping("/records/add")
    public String addRecordForm(Model model , HttpSession session) {
    	String role= (String) session.getAttribute("role");
    	 model.addAttribute("userRole", role);
    	model.addAttribute("record", new MarineRecord());
        return "records/add";
    }

    @PostMapping("/records")
    public String addRecord(@ModelAttribute MarineRecord record, Model model, HttpSession session) {
    	 
    	 String role= (String) session.getAttribute("role");
    	 model.addAttribute("userRole", role);
    	service.save(record);
        return "redirect:/records";
    }

    @GetMapping("/records/edit/{id}")
    public String editRecordForm(@PathVariable Long id, Model model, HttpSession session) {
    	String role= (String) session.getAttribute("role");
    	model.addAttribute("userRole", role);
    	model.addAttribute("record", service.findById(id));
        return "records/edit";
    }

    @PostMapping("/records/{id}")
    public String editRecord(@PathVariable Long id, @ModelAttribute MarineRecord record, Model model, HttpSession session) {
       String role= (String) session.getAttribute("role");
    	 model.addAttribute("userRole", role);
    	record.setId(id);
        service.save(record);
        return "redirect:/records";
    }

    @GetMapping("/records/delete/{id}")
    public String deleteRecord(@PathVariable Long id, Model model, HttpSession session) {
       String role= (String) session.getAttribute("role");
    	 model.addAttribute("userRole", role);
    	service.deleteById(id);
        return "redirect:/records";
    }
}
