package in.swapnilk.service;

import in.swapnilk.binding.DashboardResponse;
import in.swapnilk.entity.Counsellor;

public interface CounsellorService {
	
	public String saveCounsellor(Counsellor c);

	public Counsellor loginCheck(String email, String pwd);

	public boolean recoverPwd(String email);

	public DashboardResponse getDashboardInfo(Integer cid);
}
