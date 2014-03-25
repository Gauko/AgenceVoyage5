package m2tiil.agence.voyage.client.widgets.research;

import java.util.List;

import m2tiil.agence.voyage.client.GreetingService;
import m2tiil.agence.voyage.client.GreetingServiceAsync;
import m2tiil.agence.voyage.shared.bdd.pojo.Ville;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

public class Research extends Composite {

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	ListBox aeroportDepart;
	@UiField
	ListBox aeroportArrive;
	@UiField
	DatePicker dateDepart;
	@UiField
	DatePicker dateArrive;
	@UiField
	CheckBox meilleurPrix;
	@UiField
	ListBox listPersonne;
	@UiField
	ListBox ListFortfais;
	@UiField
	Button validate;

	private final GreetingServiceAsync service = GWT
			.create(GreetingService.class);

	protected List<Ville> villes;

	interface Binder extends UiBinder<Widget, Research> {
	}

	public Research() {
		initWidget(binder.createAndBindUi(this));
		init();
	}

	public void init() {
		initVilles();
		initPersonnes();
		initForfais();
	}

	private void initForfais() {
		ListFortfais.addItem("Adulte (25-64 ans)");
		ListFortfais.addItem("Jeune (12-17 ans)");
		ListFortfais.addItem("Junior (18-24 ans)");
		ListFortfais.addItem("Senior(+ de 65 ans)");
	}

	private void initPersonnes() {

		for (int i = 1; i < 10; i++) {
			listPersonne.addItem(i + " Pers.");
		}
		listPersonne.addItem("9+ Pers.");

	}

	private void initVilles() {
		service.rechercheVille("", null, new AsyncCallback<List<Ville>>() {

			@Override
			public void onSuccess(List<Ville> result) {
				villes = result;
				for (int i = 0; i < villes.size(); i++) {
					aeroportDepart.addItem(villes.get(i).getNom());
					aeroportArrive.addItem(villes.get(i).getNom());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ville null");
			}
		});
	}

}