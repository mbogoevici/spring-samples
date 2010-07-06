package org.springframework.samples.mvc.convert;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class JavaBean {
	
	private Integer primitive;
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date date;
	
	// list will auto-grow as its dereferenced e.g. list[0]=value
	private List<Integer> list;

	// annotation type conversion rule will be applied to each list element
	@DateTimeFormat(iso=ISO.DATE)
	private List<Date> formattedList;

	// map will auto-grow as its dereferenced e.g. map[key]=value
	private Map<Integer, String> map;

	// nested will be set when it is referenced e.g. nested.foo=value
	private NestedBean nested;
	
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JavaBean ");
        sb.append("Primitive: ").append(getPrimitive()).append(", ");
        sb.append("Date: ").append(getDate()).append(", ");
        sb.append("List: ").append(getList() == null ? "null" : getList()).append(", ");
        sb.append("FormattedList: ").append(getFormattedList() == null ? "null" : getFormattedList()).append(", ");
        sb.append("Map: ").append(getMap() == null ? "null" : getMap()).append(", ");
        sb.append("Nested: ").append(getNested());
        return sb.toString();
    }
}
