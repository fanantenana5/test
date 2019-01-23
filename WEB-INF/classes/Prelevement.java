package jirama;
import java.sql.Date;

public class Prelevement{
	String idPrelevement;
	int indexCompteurElec;
	int ancientIndexElec;
	String datePrelevement;
	int consommationElec;
	String numComptElec;
	int etat;
	
	public Prelevement(){
		
	}
	public Prelevement(String idPrelevement,int indexCompteurElec,int ancientIndexElec,String datePrelevement,int consommationElec,String numComptElec){
		this.idPrelevement = idPrelevement;
		this.indexCompteurElec = indexCompteurElec;
		this.ancientIndexElec = ancientIndexElec;
		this.datePrelevement = datePrelevement;
		this.consommationElec = consommationElec;
		this.numComptElec = numComptElec;
	}
	public String getIdPrelevement() {
		return this.idPrelevement;
	}

	public void setIdPrelevement(String idPrelevement) {
		this.idPrelevement = idPrelevement;
	}

	public int getIndexCompteurElec() {
		return this.indexCompteurElec;
	}

	public void setIndexCompteurElec(int indexCompteurElec)throws Exception	{
		if(indexCompteurElec<0){
			throw new Exception("indexCompteurElec invalide");
		}
		this.indexCompteurElec = indexCompteurElec;
	}

	public int getAncientIndexElec() {
		return this.ancientIndexElec;
	}

	public void setAncientIndexElec(int ancientIndexElec) {
		this.ancientIndexElec = ancientIndexElec;
	}

	public String getDatePrelevement() {
		return this.datePrelevement;
	}

	public void setDatePrelevement(String datePrelevement) {
		this.datePrelevement = datePrelevement;
	}

	public int getConsommationElec() {
		return this.consommationElec;
	}

	public void setConsommationElec(int consommationElec) {
		this.consommationElec = consommationElec;
	}

	public String getNumComptElec() {
		return this.numComptElec;
	}

	public void setNumComptElec(String numComptElec) {
		this.numComptElec = numComptElec;
	}
	
	public void setEtat(int etat){
		this.etat= etat;
	} 
	
	public int getEtat(){
		return this.etat;
	}
}