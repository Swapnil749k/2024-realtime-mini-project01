package in.swapnilk.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.swapnilk.binding.DashboardResponse;
import in.swapnilk.entity.Counsellor;
import in.swapnilk.entity.StudentEnq;
import in.swapnilk.repo.CounsellorRepo;
import in.swapnilk.repo.StudentEnqRepo;
import in.swapnilk.util.EmailUtils;

@Service
public class CounsellorServiceImpl implements CounsellorService {
@Autowired
	private CounsellorRepo crepo;
@Autowired
private StudentEnqRepo srepo;
@Autowired
private EmailUtils emailUtils;

	@Override
	public String saveCounsellor(Counsellor c) {
		 
		 Counsellor obj=crepo.findByEmail(c.getEmail());
		 if(obj != null) {
			 return "Duplicate Email";
		 }
		 Counsellor savedObj=crepo.save(c);
		 if (savedObj.getCid()!=null) {
			 return "Registration Success";
			 }
		return "Registration Failed";
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {
		return crepo.findByEmailAndPwd(email,pwd);
		}

	@Override
	public boolean recoverPwd(String email) {
	Counsellor c = crepo.findByEmail(email);
		if (c == null){
			return false;
	}
		String subject ="Recover Password - swapnilIT";
		String body = "<h1>Your Password : "+ c.getPwd()+"</h1>";
		return emailUtils.sendEmail(subject, body ,email);
 
	}
	@Override
	public DashboardResponse getDashboardInfo(Integer cid) {		
		List<StudentEnq>allEnqs= srepo.findByCid(cid);
		
	int enrolledEnqs=allEnqs.stream()
				.filter(e -> e.getEnqStatus().equals("Enrolled"))
				.collect(Collectors.toList())
				.size();
		
		DashboardResponse resp =new DashboardResponse();
		resp.setTotalEnq(allEnqs.size());
		resp.setEnrolledEnq(enrolledEnqs);
		resp.setLostEnq(allEnqs.size() - enrolledEnqs);
		return resp;
	}

}
