package entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class DataClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String monitor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "id= " + id + ", name= " + name + ", monitor= " + monitor;
	}

	// ####
	@ManyToMany(mappedBy = "dataClasses", cascade = CascadeType.PERSIST)
	private Set<Student> students;

	public Set<Student> getStudents() {
		if (students == null)
			students = new HashSet<Student>();
		return students;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id + name + monitor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		DataClass dbc = (DataClass) obj;
		return this.id == dbc.id;
	}
	// /####
}
