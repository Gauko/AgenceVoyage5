package m2tiil.agence.voyage.client.widgets.tableoffres;

import java.util.List;

import m2tiil.agence.voyage.client.GreetingService;
import m2tiil.agence.voyage.client.GreetingServiceAsync;
import m2tiil.agence.voyage.shared.bdd.pojo.Offre;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;



public class TableOffres extends Composite {

	private static final Binder binder = GWT.create(Binder.class);

	final SimplePager pager = new SimplePager();
	final ListDataProvider<Offre> listOffers = new ListDataProvider<Offre>();
	protected MultiSelectionModel<Offre> multiSelectionModel;

	private final GreetingServiceAsync service = GWT.create(GreetingService.class);

	@UiField(provided = true)
	CellTable<Offre> cellTable = new CellTable<Offre>();

	interface Binder extends UiBinder<Widget, TableOffres> {
	}



	public TableOffres() {

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

		Column<Offre, Boolean> checkColumn = new Column<Offre, Boolean>(new CheckboxCell(true, false)) {

			@Override
			public Boolean getValue(Offre object) {

				return multiSelectionModel.isSelected(object);
			}
		};

		// Add the columns.
		cellTable.addColumn(titleCollumn, "Intitulle");
		cellTable.addColumn(priceColumn, "Prix");
		cellTable.addColumn(checkColumn);
		service.getOffreDuJour(new AsyncCallback<List<Offre>>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();
			}



			@Override
			public void onSuccess(List<Offre> result) {

				listOffers.setList(result);
			}

		});
		listOffers.addDataDisplay(cellTable);

		final SimplePager pager = new SimplePager();
		pager.setDisplay(cellTable);
	}



	public MultiSelectionModel<Offre> getMultiSelectionModel() {

		return multiSelectionModel;
	}



	public void setMultiSelectionModel(MultiSelectionModel<Offre> multiSelectionModel) {

		this.multiSelectionModel = multiSelectionModel;
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

}