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

import entities.DataClass;
import entities.Student;
import qualifier.HibernateSession;

@Named
@ConversationScoped
public class ClassManagement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private List<DataClass> dataClasses;

	public List<DataClass> getDataClasses() {
		if (dataClasses == null) {
			dataClasses = getListDataClasses();
		}
		dataClasses = dataClasses.stream().sorted((dbc1, dbc2) -> {
			if (dbc1.getId() > dbc2.getId())
				return 1;
			return -1;
		}).collect(Collectors.toList());

		return dataClasses;
	}

	private List<DataClass> getListDataClasses() {
		return session.createNativeQuery("select * from class", DataClass.class).list();
	}
	// /####

	// ####
	private DataClass dataClass;

	public DataClass getDataClass() {
		return dataClass;
	}

	public void toggle(DataClass dataClass) {
		this.dataClass = dataClass;

	}

	public void toggle() {
		dataClass = null;
	}

	public void updateDataClass() {
		session.update(dataClass);

		dataClasses = null;
	}

	public void addDataClass() {
		dataClass = new DataClass();
		session.save(dataClass);

		dataClasses = null;
	}

	// remove student from dataClass
	public void removeStudent(Student student) {
		if (dataClass != null && student != null) {
			dataClass.getStudents().remove(student);
			student.getDataClasses().remove(dataClass);
			session.merge(dataClass);
		}
	}
	// /####

	// ####
	private Student studentToAdd;

	public Student getStudentToAdd() {
		return studentToAdd;
	}

	public void setStudentToAdd(Student studentToAdd) {
		this.studentToAdd = studentToAdd;
	}

	// get students which not relation with dataClass
	public List<Student> getStudentsOuterDataClass() {
		List<Student> listStudent = session.createNativeQuery("select * from student", Student.class).list();
		return listStudent.stream().filter(s -> !dataClass.getStudents().contains(s)).sorted((s1, s2) -> {
			if (s1.getId() > s2.getId())
				return 1;
			return -1;
		}).collect(Collectors.toList());
	}

	public void addStudentToDataClass() {
		if (dataClass != null && studentToAdd != null) {
			dataClass.getStudents().add(studentToAdd);
			studentToAdd.getDataClasses().add(dataClass);
			session.merge(dataClass);
		}
	}
	// /####

	// ####
	@PostConstruct
	public void print() {
		System.out.println("PostConstruct " + this);
	}

	@PreDestroy
	public void print1() {
		System.out.println("PreDestroy " + this);
	}
	// /####
}
