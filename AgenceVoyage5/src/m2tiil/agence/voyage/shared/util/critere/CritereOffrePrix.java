package m2tiil.agence.voyage.shared.util.critere;

import java.util.Date;

import m2tiil.agence.voyage.shared.bdd.pojo.Offre;

public class CritereOffrePrix extends Critere<Offre,Double> {

	
	
	
	

	@Override
	public boolean correspond(Offre object) {
		
		
		if(value1 == null) return false;
		
		switch(comparator){
		case INFERIOR : {
			if(value1.compareTo(object.getPrix())>0 ){
				return false;
			}
		}break;
		case SUPERIOR : {
			if(value1.compareTo(object.getPrix())<0 ){
				return false;
			}
		}break;
		case BETWEEN : {
			if(value2 == null) return false;
			if( value1.compareTo(object.getPrix())<0 || value2.compareTo(object.getPrix())>0){
				return false;
			}
		}break;
		case EQUAL : {
			if(!value1.equals(object.getPrix()) ){
				return false;
			}
		}break;
		}
		
		
		
		return true;
	}

	

	

}
