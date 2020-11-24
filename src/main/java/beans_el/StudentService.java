package beans_el;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;

import entities.Student;
import qualifier.HibernateSession;

@Named
@ConversationScoped
public class StudentService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Student> students;

	private Student student;

	// ####

	@Inject
	private Conversation conversation;

	public void begin() {
		if (this.conversation.isTransient())
			this.conversation.begin();
	}

	public void end() {
		if (!this.conversation.isTransient())
			this.conversation.end();
	}

	// /####

	@Inject
	@HibernateSession
	private Session session;

	// ####
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
	// /####

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

	// ####

	@PostConstruct
	public void print() {
		System.out.println("PostConstruct " + this);
	}

	@PreDestroy
	public void print1() {
		System.out.println("PreDestroy " + this);
	}
}
