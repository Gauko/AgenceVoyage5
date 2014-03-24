package m2tiil.agence.voyage.client.widgets.research;

import java.util.ArrayList;

import m2tiil.agence.voyage.client.GreetingService;
import m2tiil.agence.voyage.client.GreetingServiceAsync;
import m2tiil.agence.voyage.server.bdd.dao.VilleDAO;
import m2tiil.agence.voyage.shared.bdd.pojo.Ville;

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

	public void init()
	{
		ArrayList<Ville> v = VilleDAO.selectAll();
		
		for(int i=0;i<v.size();i++)
		{
			aeroportDepart.addItem(v.get(i).getNom());
			aeroportArrive.addItem(v.get(i).getNom());
		}
		
		for(int i=1;i<10;i++)
		{
			listPersonne.addItem(i+" Pers.");
		}
		listPersonne.addItem("9+ Pers.");
		
		ListFortfais.addItem("Adulte (25-64 ans)");
		ListFortfais.addItem("Jeune (12-17 ans)");
		ListFortfais.addItem("Junior (18-24 ans)");
		ListFortfais.addItem("Sénior (+ de 65 ans)");
	}
	public Research() {
		initWidget(binder.createAndBindUi(this));
	}

}
