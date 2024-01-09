package in.swapnilk.service;

import java.util.List;

import in.swapnilk.binding.SearchCriteria;
import in.swapnilk.entity.StudentEnq;

public interface EnquiryService {
	public boolean addEnq(StudentEnq se);

	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s);
}
