package m2tiil.agence.voyage.client.widgets.research;

import m2tiil.agence.voyage.client.GreetingService;
import m2tiil.agence.voyage.client.GreetingServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;

public class Research extends Composite {

	private static final Binder binder = GWT.create(Binder.class);
	@UiField ListBox aeroportDepart;
	@UiField ListBox aeroportArrive;
	@UiField DatePicker dateDepart;
	@UiField DatePicker dateArrive;
	@UiField CheckBox meilleurPrix;
	@UiField ListBox listPersonne;
	@UiField ListBox ListFortfais;
	@UiField Button validate;

	private final GreetingServiceAsync service = GWT
			.create(GreetingService.class);
	
	interface Binder extends UiBinder<Widget, Research> {
	}

	public Research() {
		initWidget(binder.createAndBindUi(this));
	}

}
