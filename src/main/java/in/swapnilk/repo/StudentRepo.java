package in.swapnilk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.swapnilk.entity.StudentEnq;

public interface StudentRepo extends JpaRepository<StudentEnq, Integer>{

}
