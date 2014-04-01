package m2tiil.agence.voyage.client.widgets.shopingcard;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Shopingcard extends Composite {

 private static final Binder binder = GWT.create(Binder.class);
 @UiField(provided=true) CellList<String> cellList = new CellList<String>(new TextCell());

 interface Binder extends UiBinder<Widget, Shopingcard> {
 }

 public Shopingcard() {
  initWidget(binder.createAndBindUi(this));
 }

 public CellList<String> getCellList() {
  return cellList;
 }

 public void setCellList(CellList<String> cellList) {
  this.cellList = cellList;
 }
 
}