package net.metrosystems;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

	@Query("SELECT st FROM Student st WHERE st.gender='MALE'")
	List<Student> customSelectWithHQL();

	@Query(value = "SELECT * FROM student WHERE gender='MALE'", nativeQuery = true)
	List<Student> customSelectWithNativeQuery();

	List<Student> findByGenderAndNameOrderByName(String gender, String name);

}
