package m2tiil.agence.voyage.client.widgets.shopingcard;

import m2tiil.agence.voyage.client.GreetingService;
import m2tiil.agence.voyage.client.GreetingServiceAsync;
import m2tiil.agence.voyage.shared.bdd.pojo.Offre;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.ButtonCellBase;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

public class Shopingcard extends Composite {

	private static final Binder binder = GWT.create(Binder.class);
	@UiField(provided=true)
	CellTable<Offre> cellTable = new CellTable<Offre>();

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
	
	private static interface removeValue<C>{
		C removeValue(Offre offre);
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
		final SimplePager pager = new SimplePager();
		pager.setDisplay(cellTable);
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