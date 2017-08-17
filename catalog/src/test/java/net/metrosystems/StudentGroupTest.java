package net.metrosystems;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.metrosystems.domain.StudentGroup;
import net.metrosystems.repository.StudentGroupRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentGroupTest {

	@Autowired
	StudentGroupRepository studentGroupRepository;
	
	@Test
	public void studentGroupExistsInDatabase() {
		
		List<StudentGroup> studentGroups = new ArrayList<>();
		
		Assert.assertNotNull(studentGroupRepository);
		
		studentGroupRepository.findAll()
			.forEach(studentGroup -> studentGroups.add(studentGroup));
		
		Assert.assertNotNull(studentGroups);
		if (studentGroups.size() < 1) {
			Assert.fail("There should be at least one StudentGroup in the database");
		}
		Assert.assertEquals(studentGroups.size(), 3);		
	
	}
	
	@Test
	public void studentGroup12AContains5Students() {
		
		StudentGroup studentGroup12A = studentGroupRepository.findOne(1);
		Assert.assertEquals(studentGroup12A.getStudents().size(), 5);
		
	}
}
