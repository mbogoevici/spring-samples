package org.springframework.samples.mvc.convert;

import java.util.List;
import java.util.Map;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class NestedBean {
	
	private String foo;

	private List<NestedBean> list;
	
	private Map<String, NestedBean> map;

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NestedBean ");
        sb.append("Foo: ").append(getFoo()).append(", ");
        sb.append("List: ").append(getList() == null ? "null" : getList()).append(", ");
        sb.append("Map: ").append(getMap() == null ? "null" : getMap());
        return sb.toString();
    }
}