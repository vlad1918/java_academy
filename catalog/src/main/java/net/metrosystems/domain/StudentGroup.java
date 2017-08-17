package net.metrosystems.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class StudentGroup {

	@Id
	private int id;
	private String name;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="student_group_id")
	private List<Student> students;
	@ManyToMany
	@JoinTable(name="student_group_course", 
		joinColumns=@JoinColumn(name="student_group_id"), 
		inverseJoinColumns=@JoinColumn(name="course_id"))
	private List<Course> courses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
