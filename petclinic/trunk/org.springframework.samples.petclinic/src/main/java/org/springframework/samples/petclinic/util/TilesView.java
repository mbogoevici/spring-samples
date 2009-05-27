package org.springframework.samples.petclinic.util;

public class TilesView extends org.springframework.web.servlet.view.tiles2.TilesView {

	@Override
	public boolean checkResource() throws Exception {
		return true;
	}
	
}
