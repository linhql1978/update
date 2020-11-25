package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Demo3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(mappedBy = "demo3", cascade = CascadeType.PERSIST)
	private Demo4 demo4;

	public Demo4 getDemo4() {
		return demo4;
	}

	public void setDemo4(Demo4 demo4) {
		this.demo4 = demo4;
	}

	public long getId() {
		return id;
	}
}
