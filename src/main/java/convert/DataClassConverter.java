package convert;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import entities.DataClass;
import utiltis.HibernateUtils;

@FacesConverter(value = "dataClassConverter")
public class DataClassConverter implements Converter {

	private Session session = HibernateUtils.getSessionFactory().openSession();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		System.out.println("getAsObject(FacesContext context, UIComponent component, String value) begin");
		if (value == null || value.isEmpty())
			return null;
		try {
			return session.createQuery(
					"select dc from DataClass dc left join fetch dc.students where dc.id=" + Long.valueOf(value),
					DataClass.class).list().get(0);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("%s is not valid a DataClass ID", value), e);
		} finally {
			session.close();
//			System.out.println("getAsObject(FacesContext context, UIComponent component, String value) end");
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null)
			return null;
		if (value.getClass() == DataClass.class) {
			return String.valueOf(((DataClass) value).getId());
		} else {
			throw new ConverterException(new FacesMessage("%s is not valid a DataClass", value.toString()));
		}
	}

}
