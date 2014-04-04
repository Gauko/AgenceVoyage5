package m2tiil.agence.voyage.client.widgets.shopingcard;

import java.util.ArrayList;
import java.util.List;

import m2tiil.agence.voyage.client.GreetingService;
import m2tiil.agence.voyage.client.GreetingServiceAsync;
import m2tiil.agence.voyage.shared.bdd.pojo.Offre;
import m2tiil.agence.voyage.shared.bdd.pojo.Reservation;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

public class Shopingcard extends Composite {

	private static final Binder binder = GWT.create(Binder.class);
	@UiField(provided=true)
	CellTable<Offre> cellTable = new CellTable<Offre>();
	@UiField Button buttonBuy;

	final SimplePager pager = new SimplePager();
	
	final ListDataProvider<Offre> listOffers = new ListDataProvider<Offre>();
	
	protected MultiSelectionModel<Offre> multiSelectionModel;
	
	private final GreetingServiceAsync service = GWT.create(GreetingService.class);
	
	interface Binder extends UiBinder<Widget, Shopingcard> {
	}

	public Shopingcard() {
		initWidget(binder.createAndBindUi(this));
		createOffersTable();
	}
	
	
	
	public void createOffersTable() {

		TextColumn<Offre> titleCollumn = new TextColumn<Offre>() {

			@Override
			public String getValue(Offre offre) {

				return offre.getLibelle();
			}
		};

		TextColumn<Offre> priceColumn = new TextColumn<Offre>() {

			@Override
			public String getValue(Offre offre) {

				return String.valueOf(offre.getPrix());
			}
		};

		this.multiSelectionModel = new MultiSelectionModel<Offre>();
		cellTable.setSelectionModel(multiSelectionModel, DefaultSelectionEventManager.<Offre> createCheckboxManager());

		ButtonCell cellButton = new ButtonCell();
		Column<Offre, String> column = new Column<Offre, String>(cellButton) {
			
			@Override
			public String getValue(Offre object) {
				return "DELETE";
			}
		};
		column.setFieldUpdater(new FieldUpdater<Offre, String>() {

			@Override
			public void update(int index, Offre object, String value) {
				listOffers.getList().remove(object);
			}
		});

		// Add the columns.
		cellTable.addColumn(titleCollumn, "Intitulle");
		cellTable.addColumn(priceColumn, "Prix");
		cellTable.addColumn(column);
//		final SimplePager pager = new SimplePager();
//		pager.setDisplay(cellTable);
		
		listOffers.addDataDisplay(cellTable);
		
	}

	public CellTable<Offre> getCellTable() {
		return cellTable;
	}

	public void setCellTable(CellTable<Offre> cellTable) {
		this.cellTable = cellTable;
	}

	public ListDataProvider<Offre> getListOffers() {
		return listOffers;
	}
	
	
	
	@UiHandler("buttonBuy")
	public void acceptBuy(ClickEvent e){
		
		//RootPanel.get("page2");
		Window.alert("Le système de paiement est actuellement en maintenance, merci de votre compréhension.");
		
		List<Reservation> l = new ArrayList<Reservation>();
		for(Offre o : listOffers.getList()){
			Reservation r = new Reservation();
			r.setIdOffre(o.getId());
			r.setIdUtilisateur(4);
			
			l.add(r);
			
		}
		
		service.doReservation("", l , "09090909090", new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {

				// TODO Auto-generated method stub
				
			}


			@Override
			public void onSuccess(Void result) {

				
				
			}
		});
		
	}

	
}