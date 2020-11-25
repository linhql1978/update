package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Demo4 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Demo3 demo3;

	public Demo3 getDemo3() {
		return demo3;
	}

	public void setDemo3(Demo3 demo3) {
		this.demo3 = demo3;
	}

	public long getId() {
		return id;
	}
}
