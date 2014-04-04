package m2tiil.agence.voyage.client.widgets.research;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import m2tiil.agence.voyage.client.GreetingService;
import m2tiil.agence.voyage.client.GreetingServiceAsync;
import m2tiil.agence.voyage.client.widgets.tableoffres.TableOffres;
import m2tiil.agence.voyage.shared.bdd.pojo.Offre;
import m2tiil.agence.voyage.shared.bdd.pojo.Ville;
import m2tiil.agence.voyage.shared.util.critere.Critere;
import m2tiil.agence.voyage.shared.util.critere.Critere.Comparator;
import m2tiil.agence.voyage.shared.util.critere.CritereOffreDateDebut;
import m2tiil.agence.voyage.shared.util.critere.CritereOffreDateRetour;
import m2tiil.agence.voyage.shared.util.critere.CritereOffreVilleDepartArrive;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
	
	private TableOffres tableOffre;

	interface Binder extends UiBinder<Widget, Research> {
	}

	public Research(TableOffres tableOffre) {
		initWidget(binder.createAndBindUi(this));
		init();
		this.tableOffre = tableOffre;
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
		service.rechercheVille(null, new AsyncCallback<List<Ville>>() {

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
	
	
	@UiHandler("validate")
	public void acceptConnexion(ClickEvent e){
		List<Critere<Offre,?>> listCritere = new ArrayList<Critere<Offre,?>>();
		
		//critères dates
		CritereOffreDateDebut c1 = new CritereOffreDateDebut();
		c1.setActivated(true);
		c1.setComparator(Comparator.EQUAL);
		Date d = dateDepart.getValue();
		if(d==null){
			return;
		}
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		c1.setFirstValue(d);
		listCritere.add(c1);
		
		
		CritereOffreDateRetour c2 = new CritereOffreDateRetour();
		c2.setActivated(true);
		c2.setComparator(Comparator.EQUAL);
		d = dateArrive.getValue();
		if(d==null){
			return;
		}
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		c2.setFirstValue(d);
		listCritere.add(c2);
		
		System.out.println(""+dateDepart.getValue()+"  to  "+dateArrive.getValue());
		
		
//		CritereOffreVilleDepartArrive c3 = new CritereOffreVilleDepartArrive();
//		c3.setActivated(true);
//		c3.setFirstValue(villes.get(aeroportDepart.getSelectedIndex()));
//		c3.setFirstValue(villes.get(aeroportArrive.getSelectedIndex()));
//		listCritere.add(c3);
		
		
		
		
		if(true){
			service.rechercheOffre(listCritere, new AsyncCallback<List<Offre>>() {
				
				@Override
				public void onSuccess(List<Offre> result) {
					tableOffre.getListOffers().setList(result);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("error on research");
					
				}
			});
		}
	}

}