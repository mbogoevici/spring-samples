package org.springframework.samples.petcare.util;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean(settersByDefault=false)
@RooToString
public final class ResourceReference {

	private Long id;
	
	private String label;

	public ResourceReference(Long id) {
		this.id = id;
	}

	public ResourceReference(Long id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int hashCode() {
		return id.hashCode();
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof ResourceReference)) {
			return false;
		}
		ResourceReference ref = (ResourceReference) o;
		return id.equals(ref.id);
	}
	
}
