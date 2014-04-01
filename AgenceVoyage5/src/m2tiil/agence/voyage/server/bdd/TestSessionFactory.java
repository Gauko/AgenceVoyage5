package m2tiil.agence.voyage.server.bdd;

import m2tiil.agence.voyage.shared.bdd.pojo.*;
import m2tiil.agence.voyage.server.bdd.dao.*;


import java.util.ArrayList;

public class TestSessionFactory {
	
	public static void testAfficheOffre()
	{
		ArrayList<Offre> a = OffreDAO.selectAll();
		
		System.out.println("Nombre d'offres : "+a.size());
		
		for(int i=0; i<a.size();i++)
		{
			System.out.println(a.get(i).toString());
		}
	}
	
	public static void testAfficheMoyenDeTransport()
	{
		ArrayList<MoyenDeTransport> a = MoyenDeTransportDAO.selectAll();
		
		System.out.println("Nombre de moyens de transport : "+a.size());
		
		for(int i=0; i<a.size();i++)
		{
			System.out.println(a.get(i).toString());
		}
	}
	
	public static void testAfficheReservation()
	{
		ArrayList<Reservation> a = ReservationDAO.selectAll();
		
		System.out.println("Nombre de reservations : "+a.size());
		
		for(int i=0; i<a.size();i++)
		{
			System.out.println(a.get(i).toString());
		}
	}
	
	public static void testAfficheSociete()
	{
		ArrayList<Societe> a = SocieteDAO.selectAll();
		
		System.out.println("Nombre de societes : "+a.size());
		
		for(int i=0; i<a.size();i++)
		{
			System.out.println(a.get(i).toString());
		}
	}
	
	public static void testAfficheTrajet()
	{
		ArrayList<Trajet> a = TrajetDAO.selectAll();
		
		System.out.println("Nombre de trajets : "+a.size());
		
		for(int i=0; i<a.size();i++)
		{
			System.out.println(a.get(i).toString());
		}
	}
	
	public static void testAfficheType()
	{
		ArrayList<Type> a = TypeDAO.selectAll();
		
		System.out.println("Nombre de types : "+a.size());
		
		for(int i=0; i<a.size();i++)
		{
			System.out.println(a.get(i).toString());
		}
	}
	
	public static void testAfficheUtilisateur()
	{
		ArrayList<Utilisateur> a = UtilisateurDAO.selectAll();
		
		System.out.println("Nombre d'utilisateurs : "+a.size());
		
		for(int i=0; i<a.size();i++)
		{
			System.out.println(a.get(i).toString());
		}
	}
	
	public static void testAfficheVille()
	{
		ArrayList<Ville> a = VilleDAO.selectAll();
		
		System.out.println("Nombre de villes : "+a.size());
		
		for(int i=0; i<a.size();i++)
		{
			System.out.println(a.get(i).toString());
		}
	}
	
	public void testInsert()
	{
		System.out.println("Insertion du moyen de transport PousPous ChinaAirlines");
		Type t = new Type(0, "PousPous");
		int idT = TypeDAO.save(t);
		Societe s = new Societe(0,"ChinaAirlines");
		int idS = SocieteDAO.save(s);
		
		int idt2 = TypeDAO.findByLibelle("PousPous").getId();
		System.out.println("id t : "+idT+" id s : "+idS+"id 2 t : "+idt2);
		
		MoyenDeTransport m = new MoyenDeTransport(0, "PousPous ChinaAirlines", idT, idS);
		MoyenDeTransportDAO.save(m);
	}
	
	public void testAffiche()
	{
		testAfficheMoyenDeTransport();
		testAfficheOffre();
		testAfficheReservation();
		testAfficheSociete();
		testAfficheTrajet();
		testAfficheType();
		testAfficheUtilisateur();
		testAfficheVille();
	}
	
	public void testFind()
	{
		/*Societe s1 = SocieteDAO.findById(2);
		System.out.println(s1.toString());*/
		MoyenDeTransport s2 = MoyenDeTransportDAO.findByNom("Car AirFrance");
		
		System.out.println(s2.toString());
	}
	
	public static void main(String[] args) {
			TestSessionFactory t = new TestSessionFactory();
			//t.testAffiche();
			t.testInsert();
			//t.testFind();
		

	}
	

}
