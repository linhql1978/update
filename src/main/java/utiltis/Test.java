package utiltis;

import org.hibernate.Session;

import entities.Demo3;
import entities.Demo4;

public class Test {
	public static void main(String[] args) {
		// ####
//		Student student = new Student();
//		Session session = HibernateUtils.getSessionFactory().openSession();
//		session.saveOrUpdate(student);
//		session.close();
//
//		System.out.println(student.getId());
		// /####

		// ####
//		String str = "2020-03 31";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM dd");
//		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm yyyy");
//		Date date = null;
//		try {
//			date = sdf.parse(str);
//			System.out.println(date);
//			System.out.println(sdf.format(date));
//			System.out.println(sdf1.format(date));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(date);
//		LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
//		System.out.println(localDate);
		// /####

		// ####
//		Student student = new Student(); //owner side
//		DataClass dataClass = new DataClass();
////		student.getDataClasses().add(dataClass);
//		dataClass.getStudents().add(student);
////		dataClass.getStudents().add(new Student());
////		dataClass.getStudents().add(new Student());
//		Session session = HibernateUtils.getSessionFactory().openSession();
//		session.beginTransaction();
////		session.save(student);
////		session.save(dataClass);
//		session.saveOrUpdate(dataClass);
////		session.saveOrUpdate(student);
////		session.remove(dataClass);
//		session.getTransaction().commit();
//		session.close();
////		session = HibernateUtils.getSessionFactory().openSession();
////		session.refresh(student);
////		DataClass dataClass2 = new DataClass();
////		dataClass2.getStudents().add(student);
////		student.getDataClasses().add(dataClass2);
////		session.beginTransaction();
////		session.saveOrUpdate(dataClass2);
////		session.getTransaction().commit();
////		session.close();
		// ###
//		Session session = HibernateUtils.getSessionFactory().openSession();
////		DataClass dataClass = session.find(DataClass.class, (long) 22);
//		Student student = session.find(Student.class, (long) 36);
//		session.beginTransaction();
////		session.remove(dataClass);
//		session.remove(student);
//		session.getTransaction().commit();
//		session.close();
		// ###
//		Session session = HibernateUtils.getSessionFactory().openSession();
//		session.beginTransaction();
////		DataClass dataClass = session.find(DataClass.class, (long) 30);
////		dataClass.setName("abc");
////		Student student = session.find(Student.class, (long) 44);
////		student.setEmail("sdfsd");
////		System.out.println("dc:" + dataClass.getStudents().size());
////		System.out.println("st:" + student.getDataClasses().size());
////		dataClass.getStudents().remove(student);
////		student.getDataClasses().remove(dataClass);
////		System.out.println("dc:" + dataClass.getStudents().size());
////		System.out.println("st:" + student.getDataClasses().size());
////		session.save(dataClass);
//		session.createNativeQuery("delete from class_student cs where cs.id_class=31 and cs.id_student=45")
//				.executeUpdate();
////		session.refresh(dataClass);
////		System.out.println(dataClass.getStudents().size());
//		session.getTransaction().commit();
//		session.close();
		// /####

		// ####
//		Set<String> set = new HashSet<String>();
//		set.toArray();
//		set.iterator();
//		set.stream().collect(Collectors.toList());
		// /####

		// ####
//		Demo1 demo1 = new Demo1();
//		Demo2 demo2 = new Demo2();
////		demo2.setDemo1(demo1);
//		demo1.getDemo2s().add(demo2);
//		Session session = HibernateUtils.getSessionFactory().openSession();
//		session.beginTransaction();
//		session.save(demo1);
//		session.save(demo2);
//		session.getTransaction().commit();
//		session.close();
		// /####

		// ####
//		Demo3 demo3 = new Demo3();
//		Demo4 demo4 = new Demo4();
////		Demo4 demo4_1 = new Demo4();
//		demo3.setDemo4(demo4);
////		demo4.setDemo3(demo3);
////		demo4_1.setDemo3(demo3);
//		Session session = HibernateUtils.getSessionFactory().openSession();
//		session.beginTransaction();
//		session.persist(demo3);
////		session.save(demo4);
////		session.save(demo4_1);
//		session.getTransaction().commit();
//		session.close();
		// /####

		// ####
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Demo3 demo3 = session.find(Demo3.class, (long) 1);
		System.out.println(demo3.getDemo4().getId());

		Demo4 demo4 = session.find(Demo4.class, (long) 1);
		System.out.println(demo4.getDemo3().getId());

		// /####
	}
}
