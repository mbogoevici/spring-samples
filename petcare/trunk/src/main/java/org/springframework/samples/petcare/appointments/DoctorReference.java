package org.springframework.samples.petcare.appointments;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean(settersByDefault=false)
@RooToString
public final class DoctorReference {

	private Long id;
	
	private String name;

	public DoctorReference(Long id) {
		this.id = id;
	}

	public DoctorReference(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int hashCode() {
		return id.hashCode();
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof DoctorReference)) {
			return false;
		}
		DoctorReference ref = (DoctorReference) o;
		return id.equals(ref.id);
	}
	
}
