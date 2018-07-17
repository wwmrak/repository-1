package ejb.services;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("autoriConverter")
public class AuthorConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				AuthorService service = (AuthorService) fc.getExternalContext().getApplicationMap()
						.get("autoriService");

				if (service.getVrstaSearcha().equals("autori"))
					return service.getAutori().get(Integer.parseInt(value));
				if (service.getVrstaSearcha().equals("katalog"))
					return service.getAutoriIKnjige().get(Integer.parseInt(value));

				return service.getKnjige().get(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
			}
		} else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Author) object).getId());
		} else {
			return null;
		}
	}
}