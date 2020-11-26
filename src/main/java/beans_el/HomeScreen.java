package beans_el;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class HomeScreen implements Serializable {

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
	private StudentService studentService;
	@Inject
	private ClassManagement classManagement;

	public ClassManagement getClassManagement() {
		return classManagement;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	// ####
	private String tab = "";

	public String getTab() {
		return tab;
	}

	public void tabToStudentService() {
		tab = "StudentService";
		this.begin();
		studentService.begin();

	}

	public void studentServiceEnd() {
		tab = "";
		studentService.end();
		this.end();

	}

	public void tabToClassManagement() {
		tab = "ClassManagement";
		this.begin();
		classManagement.begin();
	}

	public void classManagementEnd() {
		tab = "";
		studentService.end();
		this.end();
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
