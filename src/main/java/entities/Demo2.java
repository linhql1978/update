package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Demo2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@PrimaryKeyJoinColumn
	private Demo1 demo1;

	public Demo1 getDemo1() {
		return demo1;
	}

	public void setDemo1(Demo1 demo1) {
		this.demo1 = demo1;
	}

	public long getId() {
		return id;
	}
}
