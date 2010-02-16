package org.springframework.samples.petcare.appointments;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<Block> getBlocks() {
		List<Block> blocks = new ArrayList<Block>(NUMBER_OF_BLOCKS);
		LocalTime time = new LocalTime(8, 0);
		for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
			blocks.add(new Block(time));
			time = time.plusMinutes(BLOCK_LENGTH_IN_MINUTES);
		}
		return blocks;
	}
	
	public static int count() {
		return NUMBER_OF_BLOCKS;
	}
	
	public static int indexOf(Appointment appointment) {
		return appointment.getStartTime().getHourOfDay() - 8;
	}
	
	// internal 
	
	private Block(LocalTime time) {
		this.time = time;
	}

	private static final int NUMBER_OF_BLOCKS = 9;
	
	private static final int BLOCK_LENGTH_IN_MINUTES = 60;

}