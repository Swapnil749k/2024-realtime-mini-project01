package in.swapnilk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.swapnilk.binding.SearchCriteria;
import in.swapnilk.entity.StudentEnq;
import in.swapnilk.service.EnquiryService;

@Controller
public class EnquiryController {
	@Autowired
	private EnquiryService enqService;
	
	@GetMapping("/enquiry")
	public String enqPage(Model model) {
		model.addAttribute("enq" ,new StudentEnq());
		return "addEnqview";
	}
 
	@PostMapping("/enquiry")
	public String addEnquiry(StudentEnq se, Model model) {
		boolean addEnq =enqService.addEnq(se);
		if (addEnq) {
			//success msg
		}else {
			//error
		}
		return "addEnqview";
	}
	@GetMapping("/enquiries")
	public String viewEnquiries(Model model) {
		List<StudentEnq>enquiriesList= enqService.getEnquiries(null,null);
		model.addAttribute("enquiries",enquiriesList);		
		return "displayEnqview";
	}

	@PostMapping("/filter-enquiries")
	public String filterEnquiries(SearchCriteria sc, Model model) {
		List<StudentEnq>enquiriesList= enqService.getEnquiries(null,null);
		model.addAttribute("enquiries",enquiriesList);		
		return "displayEnqview";
	}
}
