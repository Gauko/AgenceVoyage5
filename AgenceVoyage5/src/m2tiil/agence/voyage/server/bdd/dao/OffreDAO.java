package m2tiil.agence.voyage.server.bdd.dao;

import java.util.*;

import org.hibernate.*;

import m2tiil.agence.voyage.server.bdd.HibernateUtil;
import m2tiil.agence.voyage.shared.bdd.pojo.MoyenDeTransport;
import m2tiil.agence.voyage.shared.bdd.pojo.Offre;

public class OffreDAO 
{
	
	public static ArrayList<Offre> selectAll()
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		
		Query select = s.createQuery("select new "+HibernateUtil.locatePojo+".Offre(o.id,o.libelle,o.prix,o.placesTotales,o.placesDisponibles,o.dateDebut,o.dateFin,o.idTrajet) from Offre o");
		
		ArrayList<Offre> offres = new ArrayList<Offre>();
		
		//System.out.println(select.list().get(0).getClass().toString());
		List<?> res = select.list();

		for(int i=0;i<res.size();i++)
		{
			Offre a = (Offre)res.get(i);
			Offre o = new Offre();
			o.setId(a.getId());
			o.setLibelle(a.getLibelle());
			o.setPlacesDisponibles(a.getPlacesDisponibles());
			o.setPlacesTotales(a.getPlacesTotales());
			o.setPrix(a.getPrix());
			o.setDateDebut(a.getDateDebut());
			o.setDateFin(a.getDateFin());
			o.setIdTrajet(a.getIdTrajet());
			
			offres.add(o);
			
			//System.out.println(o.toString());
		}

		tx.commit();
		s.close();
		return offres;
	}
	
	public static Offre findById(int id)
	{
		ArrayList<Offre> offres = selectAll();
		Iterator<Offre> i = offres.iterator();
		Offre o = new Offre();
		for(;i.hasNext() ; o = i.next())
		{
			if (o.getId() == id)
			{
				return o;
			}
		}
		return null;
	}
	
	public static Offre findByNom(String nom)
	{
		ArrayList<Offre> Offre = selectAll();
		Iterator<Offre> i = Offre.iterator();
		Offre o = new Offre();
		o = i.next();
		for(;i.hasNext() ; o = i.next())
		{
			if (o.getLibelle().equals(nom))
			{
				return o;
			}
		}
		return null;
	}
	
	public static ArrayList<Offre> findByPeriod(Date start, Date end) 
	//la recherche peut etre ouverte - si end est nul on aura toutes les date a partir de start
	{
		boolean startOK = false;
		boolean endOK = false;
		ArrayList<Offre> Offre = selectAll();
		ArrayList<Offre> res = new ArrayList<Offre>();
		Iterator<Offre> i = Offre.iterator();
		Offre o = new Offre();
		for(;i.hasNext() ; o = i.next())
		{
			if (o.getDateDebut().before(start))
			{
				startOK = true;
			}
			
			if (o.getDateFin().before(end))
			{
				endOK = true;
			}
			
			if((startOK || start == null)&&(endOK || end == null))
			{
				res.add(o);
			}
		}
		return res;
	}
	
	public static boolean delete(Offre o)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		
		Query q = s.createSQLQuery("delete from Offre where id="+o.getId());
		
		q.executeUpdate();
		tx.commit();
		
		s.close();
		return true;
	}
	
	public static int save(Offre o)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		
		Query q = s.createSQLQuery("insert into Offre(libelle, placesDisponibles, placesTotales, prix, dateDebut, dateFin, idTrajet) values (\'"+o.getLibelle()+"\',"+o.getPlacesDisponibles()+","+o.getPlacesTotales()+","+o.getPrix()+","+o.getDateDebut()+","+o.getDateFin()+","+o.getIdTrajet()+")");
		
		q.executeUpdate();
		tx.commit();
		
		s.close();
		return findByNom(o.getLibelle()).getId();
	}
	
	public static boolean update(Offre origine, Offre modif)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		
		Query q = s.createSQLQuery("update Offre set libelle=\'"+modif.getLibelle()+"\', placesDisponibles="+modif.getPlacesDisponibles()+", placesTotales="+modif.getPlacesTotales()+",prix="+modif.getPrix()+", dateDebut="+modif.getDateDebut()+", dateFin="+modif.getDateFin()+", idTrajet="+modif.getIdTrajet()+" where id="+origine.getId());
		
		q.executeUpdate();
		tx.commit();
		
		s.close();
		return true;
	}
}
