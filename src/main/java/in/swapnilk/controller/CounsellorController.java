package in.swapnilk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.swapnilk.entity.Counsellor;
import in.swapnilk.service.CounsellorService;

@Controller
public class CounsellorController {
	@Autowired
	private CounsellorService counsellorSvc;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "loginview";
		
	}
	@PostMapping("/login")
	public String handleLogin(Counsellor c, Model model) {
		
		Counsellor obj =counsellorSvc.loginCheck(c.getEmail(),c.getPwd());
		
		if (obj == null) {
			model.addAttribute("errMsg","Invalid Credentials");
			return "loginview";
		}
			return "redirect:dashboard";
	}
	
	@GetMapping("/dashboard")
	public String buildDashboard(Model model) {
		//
		return "dashboardView";
	}
	
	
	
	@GetMapping("/register")
	public String regPage(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "registerView";
	}
	
	@PostMapping("/register")
	public String handleRegistration(Counsellor c, Model model) {
		String msg = counsellorSvc.saveCounsellor(c);
		model.addAttribute("msg",msg);
		return "registerView";
	}
	
	
	@GetMapping("/forgot-pwd")
	public String recoverPwdPage(Model model) {
		
		return "forgotpwdView";
		
	}
	@GetMapping("/recover-pwd")
	public String recoverPwd(@RequestParam String email,Model model) {
		boolean status =counsellorSvc.recoverPwd(email);
		if (status) {
			model.addAttribute("msg","Pwd sent to mail succesfully");
		}else {
			model.addAttribute("errormsg", "Invalid Email");
			
		}
		return "forgotpwdView";
	}
	
	
	
	
}
