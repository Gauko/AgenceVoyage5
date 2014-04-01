package m2tiil.agence.voyage.client;

import java.util.HashMap;
import java.util.List;

import m2tiil.agence.voyage.shared.ConnectionException;
import m2tiil.agence.voyage.shared.bdd.pojo.MoyenDeTransport;
import m2tiil.agence.voyage.shared.bdd.pojo.Offre;
import m2tiil.agence.voyage.shared.bdd.pojo.Reservation;
import m2tiil.agence.voyage.shared.bdd.pojo.Societe;
import m2tiil.agence.voyage.shared.bdd.pojo.Trajet;
import m2tiil.agence.voyage.shared.bdd.pojo.Type;
import m2tiil.agence.voyage.shared.bdd.pojo.Ville;
import m2tiil.agence.voyage.shared.util.critere.Critere;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void login(String userMail, String password, AsyncCallback<String> callback);

	void registerNewUser(String nom, String prenom, String password,
			String mail, AsyncCallback<Void> callback);

	void getTypeTransport(String token, AsyncCallback<List<Type>> callback);

//	void getCritere(String token,
//			AsyncCallback<HashMap<String, List<String>>> callback);

	void getOffreDuJour(AsyncCallback<List<Offre>> callback);

	void rechercheTrajet(String token, List<Critere<Trajet,?>> listCritere,
			AsyncCallback<List<Trajet>> callback);
	
	void rechercheOffre(String token, List<Critere<Offre,?>> listCritere,
			AsyncCallback<List<Offre>> callback);
	
	void rechercheVille(String token, List<Critere<Ville,?>> listCritere,
			AsyncCallback<List<Ville>> callback);

	void rechercheReservation(String token,
			List<Critere<Reservation, ?>> listCritere,
			AsyncCallback<List<Reservation>> callback);

	void rechercheMoyenDeTransport(String token,
			List<Critere<MoyenDeTransport, ?>> listCritere,
			AsyncCallback<List<MoyenDeTransport>> callback);

	void rechercheSociete(String token, List<Critere<Societe, ?>> listCritere,
			AsyncCallback<List<Societe>> callback);

	void doReservation(String token, List<Reservation> panier, String cbNum, AsyncCallback<Void> callback);


//	void doReservation(String token, String user, Object panier,
//			Object carteBanquaire, AsyncCallback<Object> callback);
}
