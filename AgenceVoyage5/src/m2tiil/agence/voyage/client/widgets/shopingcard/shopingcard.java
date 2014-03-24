package m2tiil.agence.voyage.client.widgets.shopingcard;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class shopingcard extends Composite {

	private static final Binder binder = GWT.create(Binder.class);
	@UiField(provided=true) CellList<Object> cellList = new CellList<Object>(new AbstractCell<Object>(){
		@Override
		public void render(Context context, Object value, SafeHtmlBuilder sb) {
			// TODO
		}
	});

	interface Binder extends UiBinder<Widget, shopingcard> {
	}

	public shopingcard() {
		initWidget(binder.createAndBindUi(this));
	}

}
