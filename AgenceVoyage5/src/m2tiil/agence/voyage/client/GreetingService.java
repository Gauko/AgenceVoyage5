package m2tiil.agence.voyage.client;

import java.util.HashMap;
import java.util.List;

import m2tiil.agence.voyage.shared.ConnectionException;
import m2tiil.agence.voyage.shared.bdd.pojo.Offre;
import m2tiil.agence.voyage.shared.bdd.pojo.Trajet;
import m2tiil.agence.voyage.shared.bdd.pojo.Type;
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
	public List<Offre> getOffreDuJour(String token) throws ConnectionException;
	public List<Trajet> rechercheTrajet(String token, List<Critere<Trajet,?>> listCritere) throws ConnectionException;
	public List<Offre> rechercheOffre(String token, List<Critere<Offre,?>> listCritere) throws ConnectionException;
//	public Object doReservation(String token, String user, Object panier, Object carteBanquaire) throws ConnectionException;

	
	
	//modif
	
}
