package utiltis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
//		Student student = new Student();
//		Session session = HibernateUtils.getSessionFactory().openSession();
//		session.saveOrUpdate(student);
//		session.close();
//
//		System.out.println(student.getId());

		String str = "2020-03 31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm yyyy");
		Date date = null;
		try {
			date = sdf.parse(str);
			System.out.println(date);
			System.out.println(sdf.format(date));
			System.out.println(sdf1.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
		LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
		System.out.println(localDate);
	}
}
