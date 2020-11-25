package utiltis;

import org.hibernate.Session;

import entities.DataClass;
import entities.Student;

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
		Student student = new Student();
		DataClass dataClass = new DataClass();
		student.getDataClasses().add(dataClass);
		dataClass.getStudents().add(student);
//		dataClass.getStudents().add(new Student());
//		dataClass.getStudents().add(new Student());
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
//		session.save(student);
//		session.save(dataClass);
		session.saveOrUpdate(dataClass);
//		session.remove(dataClass);
		session.getTransaction().commit();
		session.close();
		session = HibernateUtils.getSessionFactory().openSession();
		session.refresh(student);
		DataClass dataClass2 = new DataClass();
		dataClass2.getStudents().add(student);
		student.getDataClasses().add(dataClass2);
		session.beginTransaction();
		session.saveOrUpdate(dataClass2);
		session.getTransaction().commit();
		session.close();
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
	}
}
