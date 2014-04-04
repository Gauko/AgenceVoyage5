package m2tiil.agence.voyage.client;

import m2tiil.agence.voyage.client.widgets.connection.Connection;
import m2tiil.agence.voyage.client.widgets.research.Research;
import m2tiil.agence.voyage.client.widgets.shopingcard.Shopingcard;
import m2tiil.agence.voyage.client.widgets.tableoffres.TableOffres;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AgenceVoyage5 implements EntryPoint {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	public Shopingcard panier = new Shopingcard();
	public TableOffres offres = new TableOffres();



	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		VerticalPanel vpanel = new VerticalPanel();
		HorizontalPanel hpanel = new HorizontalPanel();
		vpanel.add(new Connection());
		hpanel.add(new Research(offres));
		hpanel.add(this.panier);
		vpanel.add(hpanel);
		vpanel.add(this.offres);
		this.offres.setPanier(panier);
		RootPanel.get("page1").add(vpanel);
	}



	public Shopingcard getPanier() {

		return panier;
	}



	public void setPanier(Shopingcard panier) {

		this.panier = panier;
	}

}