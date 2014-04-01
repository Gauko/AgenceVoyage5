package m2tiil.agence.voyage.shared.util.critere;

import java.util.Date;

import m2tiil.agence.voyage.shared.bdd.pojo.Offre;

public class CritereOffreDateRetour extends Critere<Offre,Date> {

	
	
	
	

	@Override
	public boolean correspond(Offre object) {
		
		
		if(value1 == null) return false;
		
		switch(comparator){
		case INFERIOR : {
			if(value1.compareTo(object.getDateFin())>0 ){
				return false;
			}
		}break;
		case SUPERIOR : {
			if(value1.compareTo(object.getDateFin())<0 ){
				return false;
			}
		}break;
		case BETWEEN : {
			if(value2 == null) return false;
			if( value1.compareTo(object.getDateFin())<0 || value2.compareTo(object.getDateFin())>0){
				return false;
			}
		}break;
		case EQUAL : {
			if(!value1.equals(object.getDateFin()) ){
				return false;
			}
		}break;
		}
		
		
		
		return true;
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
