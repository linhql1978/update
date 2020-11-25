package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Demo1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}

	@OneToMany(mappedBy = "demo1")
	private Set<Demo2> demo2s;

	public Set<Demo2> getDemo2s() {
		if (demo2s == null)
			demo2s = new HashSet<Demo2>();
		return demo2s;
	}
}
