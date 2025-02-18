package io.onedev.server.plugin.imports.github;

import java.io.Serializable;
import java.util.List;

import io.onedev.server.util.ComponentContext;
import io.onedev.server.web.editable.BeanEditor;
import io.onedev.server.web.editable.annotation.ChoiceProvider;
import io.onedev.server.web.editable.annotation.Editable;

@Editable
public class ImportOrganization implements Serializable {

	private static final long serialVersionUID = 1L;
	
	ImportServer server;
	
	private String organization;

	@Editable(order=100, name="GitHub Organization", description="Select organization to import from. "
			+ "Leave empty to import from repositories under current account")
	@ChoiceProvider("getOrganizationChoices")
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	@SuppressWarnings("unused")
	private static List<String> getOrganizationChoices() {
		BeanEditor editor = ComponentContext.get().getComponent().findParent(BeanEditor.class);
		ImportOrganization setting = (ImportOrganization) editor.getModelObject();
		return setting.server.listOrganizations();
	}
	
}
