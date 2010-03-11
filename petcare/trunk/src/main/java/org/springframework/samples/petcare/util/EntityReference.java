package org.springframework.samples.petcare.util;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean(settersByDefault=false)
@RooToString
public final class EntityReference {

	private Long id;
	
	private String label;

	public EntityReference(Long id) {
		this.id = id;
	}

	public EntityReference(Long id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int hashCode() {
		return id.hashCode();
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof EntityReference)) {
			return false;
		}
		EntityReference ref = (EntityReference) o;
		return id.equals(ref.id);
	}
	
}
