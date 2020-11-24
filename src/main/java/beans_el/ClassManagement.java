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
	private List<DataClass> dataClasses;

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
		if (dataClass != null && dataClass.getStudents().contains(student)
				&& student.getDataClasses().contains(dataClass)) {
			session.createNativeQuery("delete from class_student where id_class=" + dataClass.getId()
					+ " and id_student=" + student.getId()).executeUpdate();
			session.refresh(dataClass);
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
