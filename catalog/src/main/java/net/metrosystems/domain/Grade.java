package net.metrosystems.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GeneratorType;

@Entity
public class Grade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int value;
	private Date date;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
