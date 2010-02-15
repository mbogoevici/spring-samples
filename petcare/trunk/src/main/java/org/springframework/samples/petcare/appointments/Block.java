package org.springframework.samples.petcare.appointments;

import java.util.LinkedHashSet;
import java.util.Set;

import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean(settersByDefault=false)
@RooToString
public class Block {
	
	@DateTimeFormat(pattern="h a")
	private LocalTime time;
	
	//  would be nice to eliminate this with @RooEquals
	
	public int hashCode() {
		return time.hashCode();
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Block)) {
			return false;
		}
		Block b = (Block) o;
		return time.equals(b.time);
	}

	public static Set<Block> createWorkDayBlocks() {
		Set<Block> blocks = new LinkedHashSet<Block>();
		LocalTime time = new LocalTime(8, 0);
		for (int i = 0; i < 9; i++) {
			blocks.add(new Block(time));
			time = time.plusHours(1);
		}
		return blocks;
	}
	
	// internal 
	
	private Block(LocalTime time) {
		this.time = time;
	}
}