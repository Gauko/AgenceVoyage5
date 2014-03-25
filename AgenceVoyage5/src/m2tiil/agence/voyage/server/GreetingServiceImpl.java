package m2tiil.agence.voyage.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import m2tiil.agence.voyage.client.GreetingService;
import m2tiil.agence.voyage.server.bdd.dao.MoyenDeTransportDAO;
import m2tiil.agence.voyage.server.bdd.dao.OffreDAO;
import m2tiil.agence.voyage.server.bdd.dao.ReservationDAO;
import m2tiil.agence.voyage.server.bdd.dao.SocieteDAO;
import m2tiil.agence.voyage.server.bdd.dao.TrajetDAO;
import m2tiil.agence.voyage.server.bdd.dao.TypeDAO;
import m2tiil.agence.voyage.server.bdd.dao.UtilisateurDAO;
import m2tiil.agence.voyage.server.bdd.dao.VilleDAO;
import m2tiil.agence.voyage.shared.ConnectionException;
import m2tiil.agence.voyage.shared.FieldVerifier;
import m2tiil.agence.voyage.shared.bdd.pojo.Offre;
import m2tiil.agence.voyage.shared.bdd.pojo.Reservation;
import m2tiil.agence.voyage.shared.bdd.pojo.Trajet;
import m2tiil.agence.voyage.shared.bdd.pojo.Type;
import m2tiil.agence.voyage.shared.bdd.pojo.Utilisateur;
import m2tiil.agence.voyage.shared.bdd.pojo.Ville;
import m2tiil.agence.voyage.shared.util.critere.Critere;
import m2tiil.agence.voyage.shared.util.critere.CritereOffreDate;
import m2tiil.agence.voyage.shared.util.critere.CritereTrajetDate;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	
	private UtilisateurDAO userDao = new UtilisateurDAO();
	private TypeDAO typeDao = new TypeDAO();
	private MoyenDeTransportDAO moyenDeTransportDao = new MoyenDeTransportDAO();
	private OffreDAO offreDao = new OffreDAO();
	private ReservationDAO reservationDao = new ReservationDAO();
	private SocieteDAO societeDao = new SocieteDAO();
	private TrajetDAO trajetDao = new TrajetDAO();
	private VilleDAO villeDao = new VilleDAO();
	
	
	
//	private HashMap<String,List<String>> listCriteres = new HashMap<String,List<String>>();
//	{
//		listCriteres.put("date aller", new ArrayList<String>());
//		listCriteres.get("date aller").add("<");
//		listCriteres.get("date aller").add(">");
//		listCriteres.get("date aller").add("=");
//		
//		listCriteres.put("date retour", new ArrayList<String>());
//		listCriteres.get("date retour").add("<");
//		listCriteres.get("date retour").add(">");
//		listCriteres.get("date retour").add("=");
//		
//		
//		//date aller, < > =
//		//date retour, < > =
//		//prix, < > =,
//		//prix (compris entre x et y)
//		//type, & | =
//		//ville depart,
//		//ville arrivé,
//		//
//		
//	}
	
	
	
	
	
	
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	// variables
	private  Integer timeout = 2000000000;
	private  Integer nbTentative = 20000;
	
	private  Hashtable<String,Date> listTokens = new Hashtable<String,Date>();
	{
		listTokens.put("",new Date((new Date()).getTime()+timeout));
	}
	
	/**
	 * Methode de verification du token de connexion
	 * @param token
	 * @throws ConnectionException
	 */
	private void verifToken(String token) throws ConnectionException{
		Date now = new Date();
		Date tokenTimeOut = listTokens.get(token);
		if(tokenTimeOut == null){
			throw new ConnectionException("Pas de connection");
		}else if(tokenTimeOut.before(now)){
			throw new ConnectionException("Temps écoulé");
		}
		
		
	}
	
	
	//// interface
	/**
	 * Permet de se connecter en tant qu'utilisateur. Fournit un token de connection qui sera demandé pour chacunes
	 * des autres actions. 
	 * @param userMail
	 * @param password
	 * @return
	 * @throws ConnectionException
	 */
	public String login(String userMail, String password) throws ConnectionException{
		String token = "defaultToken";
		int i = 0;
		
		// check user et password
		List<Utilisateur> listU = userDao.selectAll();
		Utilisateur user = null;
		for(Utilisateur u : listU){
			if(u.getMail().equals(userMail)){
				user = u;
				break;
			}
		}
		
		if(user == null){
			// pas d'utilisateur correspondant
			throw new ConnectionException("Mauvais identifiant");
		}else if(!user.getPassword().equals(password)){
			// mauvais mot de pass
			throw new ConnectionException("Mauvais identifiant");
		}
		
		
		//
		
		do{
			token = "" + Random.nextInt();
		}while(i<nbTentative && !listTokens.containsKey(token));
		
		if(i == nbTentative){
			throw new ConnectionException("Erreur, il n'y a plus de connection disponible");
		}
		Date timeout = new Date();
		
		listTokens.put(token, new Date(timeout.getTime()+this.timeout));
		
		return token;
	}
	
	
	
	/** 
	 * Crée un nouvel utilisateur
	 * @param nom
	 * @param prenom
	 * @param password
	 * @param mail
	 * @throws Exception
	 */
	public void registerNewUser(String nom, String prenom, String password, String mail) throws Exception{
		Utilisateur user = new Utilisateur();
		user.setMail(mail);
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setPassword(password);
		
		//TODO check doublon
		for(Utilisateur u : userDao.selectAll()){
			if(u.getMail().equals(user.getMail())){
				throw new Exception("This email is already used !");
			}
		}
		
		userDao.save(user);
		
	}
	
	
	
	
	/**
	 * Retourne la liste des types de transport disponible
	 * @param token
	 * @return
	 * @throws ConnectionException
	 */
	public List<Type> getTypeTransport(String token) throws ConnectionException{
		verifToken(token);
		
		List<Type> l = typeDao.selectAll();
		
		return l;
	}
	
//	/**
//	 * Retourne la liste des critères de recherche
//	 * @param token
//	 * @return
//	 * @throws ConnectionException
//	 */
//	public HashMap<String,List<String>> getCritere(String token) throws ConnectionException{
//		verifToken(token);
//		
//		
//		return listCriteres;
//	}
	
	
	
	
	/**
	 * Obtenir les offres du jour
	 * @param token
	 * @return
	 * @throws ConnectionException
	 */
	public List<Offre> getOffreDuJour(String token) throws ConnectionException{
		verifToken(token);
		Date d = new Date();
		List<Offre> l = offreDao.selectAll();
		List<Offre> l2 = new ArrayList<Offre>();
		for(Offre o : l){
			if(o.getDateDebut().compareTo(d) <0 && o.getDateFin().compareTo(d)>0 ){
				l2.add(o);
			}
			
		}
		
		
		return l2;
	}
	
	
	
	/**
	 * Retourne la liste des trajets correspondant aux critères fournis.
	 * @param token
	 * @param critereValeur
	 * @return
	 * @throws ConnectionException
	 */
	public List<Trajet> rechercheTrajet(String token, List<Critere<Trajet,?>> listCritere) throws ConnectionException{
		verifToken(token);
		List<Trajet> l = trajetDao.selectAll();
		List<Trajet> l2 = new ArrayList<Trajet>();
		boolean keep = true;
		
		for(Trajet t : l){
			keep = true;
			if(listCritere != null){
				for(Critere c : listCritere){
					if(c.isActivated()){
						keep = keep && c.correspond(t);
					}
				}
			}
			
			if(keep){
				l2.add(t);
			}
		}
		
		
		return l2;
	}
	
	
	
	
	public List<Offre> rechercheOffre(String token, List<Critere<Offre,?>> listCritere) throws ConnectionException{
		verifToken(token);
		List<Offre> l = offreDao.selectAll();
		List<Offre> l2 = new ArrayList<Offre>();
		boolean keep = true;
		
		for(Offre t : l){
			keep = true;
			
			if(listCritere != null){
				for(Critere c : listCritere){
					if(c.isActivated()){
						keep = keep && c.correspond(t);
					}
				}
			}
			
			if(keep){
				l2.add(t);
			}
		}
		
		
		return l2;
	}
	
	
	
	public List<Ville> rechercheVille(String token, List<Critere<Ville,?>> listCritere) throws ConnectionException{
		verifToken(token);
		List<Ville> l = villeDao.selectAll();
		List<Ville> l2 = new ArrayList<Ville>();
		boolean keep = true;
		
		for(Ville t : l){
			keep = true;
			
			if(listCritere != null){
				for(Critere c : listCritere){
					if(c.isActivated()){
						keep = keep && c.correspond(t);
					}
				}
			}
			
			if(keep){
				l2.add(t);
			}
		}
		return l2;
	}
	
	
	
	
	
	
	/**
	 * Effectue une réservation
	 * @throws ConnectionException
	 */
	public void doReservation(String token, List<Reservation> panier, String cbNum) throws ConnectionException{
		verifToken(token);
		
		
		
		
	}
	
	
	
	
	
	/**
	 * main test
	 * @param args
	 */
	public static void main(String[] args) {
		GreetingServiceImpl a = new GreetingServiceImpl();
		
		
		try {
			a.login("toto@toto.com", "toto");
		} catch (ConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		/*
		List<Critere<Trajet,?>> lc = new ArrayList<Critere<Trajet,?>>();
		CritereTrajetDate c = new CritereTrajetDate();
		c.setActivated(true);
		c.setComparator(Critere.Comparator.EQUAL);
		c.setFirstValue(new Date("2014/03/03"));
		
		lc.add(c);
		
		try {
			List<Trajet> res = a.rechercheTrajet("", lc);
			
			System.out.println(res.size());
			
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
	}
	
	
}
