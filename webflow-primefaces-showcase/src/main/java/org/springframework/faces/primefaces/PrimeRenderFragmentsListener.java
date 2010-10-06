package org.springframework.faces.primefaces;

import java.util.Arrays;

import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.execution.FlowExecutionListener;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.View;

/**
 * <p>A simple {@link FlowExecutionListener} integrating the Web Flow <code>render</code> action with 
 * PrimeFaces partial rendering support.</p>
 * 
 * <p>The Web Flow <code>render</code> action provides control over partial rendering from a flow
 * definition rather than from the view. This means that instead of specifying renderIds 
 * via f:ajax or updateIds via p:commandButton you can add a render action to your flow 
 * definition indicating what parts of the view should be rendered.</p>
 * 
 * <p>In addition to configuring your FlowExecutor with this listener and using the render 
 * action in your flow definition, there is one runtime requirement that the request must be a
 * JSF 2 Ajax request. This requirement is easily satisified if using the p:commandButton component.</p>
 * 
 */
public class PrimeRenderFragmentsListener extends FlowExecutionListenerAdapter {

	public void viewRendering(RequestContext requestContext, View view, StateDefinition stateDefinition) {
		String[] fragments = (String[]) requestContext.getFlashScope().get(View.RENDER_FRAGMENTS_ATTRIBUTE);
		if (fragments != null && fragments.length > 0) {
			org.primefaces.context.RequestContext primeContext = org.primefaces.context.RequestContext.getCurrentInstance();
			if (primeContext != null) {
				primeContext.addPartialUpdateTargets(Arrays.asList(fragments));
			}
		}
	}

}
