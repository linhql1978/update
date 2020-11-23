package beans_el;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;

import entities.Student;
import qualifier.HibernateSession;

@Named
@ApplicationScoped
public class StudentService {

	private List<Student> students;

	private Student student;

	@Inject
	@HibernateSession
	private Session session;

	public List<Student> getStudents() {
		if (students == null) {
			students = getListStudents();
		}
		return students.stream().sorted((s1, s2) -> {
			if (s1.getId() > s2.getId())
				return 1;
			return -1;
		}).collect(Collectors.toList());
	}

	private List<Student> getListStudents() {
		return session.createNativeQuery("select * from student", Student.class).list();
	}

	public Student getStudent() {
		return student;
	}

	public void toggle(Student student) {
		this.student = student;
	}

	public void toggle() {
		student = null;
	}

	public void updateStudent() {
		session.update(student);

		students = null;
	}

	public void addStudent() {
		student = new Student();
		session.save(student);

		students = null;
	}
}