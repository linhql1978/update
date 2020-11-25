package beans_el;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

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
	private Collection<DataClass> dataClasses;

	public Collection<DataClass> getDataClasses() {
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

	private Collection<DataClass> getListDataClasses() {
		return session.createQuery("select dc from DataClass dc left join fetch dc.students", DataClass.class).list()
				.stream().collect(Collectors.toSet());
	}
	// /####

	// ####
	private DataClass dataClass;

	public DataClass getDataClass() {
		return dataClass;
	}

	public void toggle(DataClass dataClass) {
		this.dataClass = dataClass;
		setUpStudentsOuterDataClass();
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
			student = session // just only owner remove is ok
					.createQuery("select s from Student s left join fetch s.dataClasses where s.id=" + student.getId(),
							Student.class)
					.list().get(0);
			dataClass.getStudents().remove(student);
			studentsOuterDataClass.add(student);
			student.getDataClasses().remove(dataClass);
			session.update(student);
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

	// ###
	private Collection<Student> studentsOuterDataClass;

	public void setUpStudentsOuterDataClass() {
		Collection<Student> listStudent = session
				.createQuery("select s from Student s left join fetch s.dataClasses", Student.class).list().stream()
				.collect(Collectors.toSet());
		studentsOuterDataClass = listStudent.stream().filter(s -> !dataClass.getStudents().contains(s))
				.collect(Collectors.toList());
	}

	// get students which not relation with dataClass
	public Collection<Student> getStudentsOuterDataClass() { // <f:selectItems call to four times ???
		if (studentsOuterDataClass == null)
			studentsOuterDataClass = new HashSet<Student>();
		return studentsOuterDataClass.stream().filter(s -> !dataClass.getStudents().contains(s)).sorted((s1, s2) -> {
			if (s1.getId() > s2.getId())
				return 1;
			return -1;
		}).collect(Collectors.toList());
	}
	// /###

	public void addStudentToDataClass() {
		if (dataClass != null && studentToAdd != null) {
			dataClass.getStudents().add(studentToAdd);
			studentsOuterDataClass.remove(studentToAdd);
			session.detach(studentToAdd);
			studentToAdd.getDataClasses().add(dataClass);
			session.update(studentToAdd);
		}
	}
	// /####

	// ####
//	@PostConstruct
//	public void print() {
//		System.out.println("PostConstruct " + this);
//	}
//
//	@PreDestroy
//	public void print1() {
//		System.out.println("PreDestroy " + this);
//	}
	// /####
}
