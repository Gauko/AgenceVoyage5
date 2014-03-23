package m2tiil.agence.voyage.shared.util.critere;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import m2tiil.agence.voyage.server.bdd.dao.OffreDAO;
import m2tiil.agence.voyage.shared.bdd.pojo.Offre;
import m2tiil.agence.voyage.shared.bdd.pojo.Trajet;

public class CritereTrajetDate extends Critere<Trajet,Date> {

	
	
	

	@Override
	public boolean correspond(Trajet object) {
		
		if(value1 == null) return false;
		
		OffreDAO offreDao = new OffreDAO();
		List<Offre> l = offreDao.selectAll();
		List<Offre> l2 = new ArrayList<Offre>();
		for(Offre o : l){
			if(o.getIdTrajet() == object.getId()){
				l2.add(o);
			}
		}
		
		for(Offre o : l2){
			
			switch(comparator){
			case INFERIOR : {
				if(value1.compareTo(o.getDateDebut())<0 ){
					return true;
				}
			}break;
			case SUPERIOR : {
				if(value1.compareTo(o.getDateDebut())>0 ){
					return true;
				}
			}break;
			case BETWEEN : {
				if(value2 == null) return false;
				if( value1.compareTo(o.getDateDebut())>0 && value2.compareTo(o.getDateFin())<0){
					return true;
				}
			}break;
			case EQUAL : {
				if(value1.equals(o.getDateDebut()) ){
					return true;
				}
			}break;
			}
			
		}
		
		
		
		
		
		return false;
	}

	@Override
	public void setFirstValue(Date o) {
//		if(o instanceof Date){
			value1 = (Date)o;
//		}
	}

	@Override
	public void setSecondValue(Date o) {
//		if(o instanceof Date){
			value2 = (Date)o;
//		}		
	}
	
	
	
	

}
