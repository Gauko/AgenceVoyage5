package m2tiil.agence.voyage.client;

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

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	
	public String greetServer(String input) throws IllegalArgumentException;
	public String login(String userMail, String password) throws ConnectionException;
	public void registerNewUser(String nom, String prenom, String password, String mail) throws Exception;
	public List<Type> getTypeTransport(String token) throws ConnectionException;
//	public HashMap<String,List<String>> getCritere(String token) throws ConnectionException;
	public List<Offre> getOffreDuJour();
	public List<Trajet> rechercheTrajet(String token, List<Critere<Trajet,?>> listCritere) throws ConnectionException;
	public List<Offre> rechercheOffre(String token, List<Critere<Offre,?>> listCritere) throws ConnectionException;
	public List<Ville> rechercheVille(String token, List<Critere<Ville,?>> listCritere) throws ConnectionException;
	public List<Reservation> rechercheReservation(String token, List<Critere<Reservation,?>> listCritere) throws ConnectionException;
	public List<MoyenDeTransport> rechercheMoyenDeTransport(String token, List<Critere<MoyenDeTransport,?>> listCritere) throws ConnectionException;
	public List<Societe> rechercheSociete(String token, List<Critere<Societe,?>> listCritere) throws ConnectionException;

//	public Object doReservation(String token, String user, Object panier, Object carteBanquaire) throws ConnectionException;
	public void doReservation(String token, List<Reservation> panier, String cbNum) throws ConnectionException;
	
	
	//modif
	
}
