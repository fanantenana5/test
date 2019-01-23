package jirama;
import java.sql.Date;
public class Facture{
	
	String idFacture;
	String idClient;
	String idPrelevement;
	String dateFacture;
	String moisFacture;
	String anneeFacture;
	String agence;
	int consommationElec;
	double montantElec;
	double montantElec2;
	double montantElec3;
	double montantTotal;
	
	public void setConsommationElec(String consommationElec)throws Exception{
		this.setConsommationElec(Integer.valueOf(consommationElec));
	}
	
	public void setMontantElec(String montantElec){
		Double d = new Double(montantElec);
		this.setMontantElec(d.doubleValue());
	}
	
	public void setMontantElec2(String montantElec2){
		Double d = new Double(montantElec2);
		this.setMontantElec2(d.doubleValue());
	}
	
	public void setMontantElec3(String montantElec3){
		Double d = new Double(montantElec3);
		this.setMontantElec3(d.doubleValue());
	}
	
	public void setMontantTotal(String montantTotal){
		Double d = new Double(montantTotal);
		this.setMontantTotal(d.doubleValue());
	}
	
	public void setMoisFacture(String moisFacture){
		this.moisFacture=moisFacture;
	}
	public String getMoisFacture(){
		return this.moisFacture;
	}
	public void setAnneeFacture(String anneeFacture){
		this.anneeFacture=anneeFacture;
	}
	public String getAnneeFacture(){
		return this.anneeFacture;
	}
	public void setIdFacture(String idFacture){
		this.idFacture=idFacture;
	}
	
	public void setIdClient(String idClient){
		this.idClient=idClient;
	}
	
	public void setIdPrelevement(String idPrelevement){
		this.idPrelevement=idPrelevement;
	}
	
	public void setDateFacture(String dateFacture){
		this.dateFacture=dateFacture;
	}
	
	public void setAgence(String  agence){
		this.agence=agence;
	}
	
	public void setConsommationElec(int consommationElec)throws Exception{
		System.out.println("classe facture"+consommationElec);
		if(consommationElec < 0){
			throw new Exception("La consommation ne doit pas etre negatif");
		}
		this.consommationElec=consommationElec;
	}
	
	public void setMontantElec(double montantElec){
		this.montantElec=montantElec;
	}
	
	public void setMontantTotal(double montantTotal){
		this.montantTotal=montantTotal;
	}
	
	public void setMontantElec2(double montantElec2){
		this.montantElec2=montantElec2;
	}
	
	public String getIdFacture() {
		return this.idFacture;
	}

	public String getIdClient() {
		return this.idClient;
	}

	public String getIdPrelevement() {
		return this.idPrelevement;
	}

	public String getDateFacture() {
		return this.dateFacture;
	}

	public String getAgence() {
		return this.agence;
	}

	public int getConsommationElec() {
		return this.consommationElec;
	}

	public double getMontantElec() {
		return this.montantElec;
	}
	
	public double getMontantElec2() {
		return this.montantElec2;
	}

	public double getMontantTotal() {
		return this.montantTotal;
	}
	
	public void setMontantElec3(double montantElec3){
		this.montantElec3 = montantElec3;
	}
	
	public double getMontantElec3(){
		return this.montantElec3;
	}
	
	public Facture(String idFacture, String idClient, String idPrelevement, String dateFacture,String moisFacture, String anneeFacture, String agence, int consommationElec, double montantElec, double montantTotal,double montantElec2,double montantElec3) {
		this.idFacture = idFacture;
		this.idClient = idClient;
		this.idPrelevement = idPrelevement;
		this.dateFacture = dateFacture;
		this.moisFacture=moisFacture;
		this.anneeFacture=anneeFacture;
		this.agence = agence;
		this.consommationElec = consommationElec;
		this.montantElec2 = montantElec2;
		this.montantElec = montantElec;
		this.montantElec3 = montantElec3;
		this.montantTotal = montantTotal;
	}
	
	public Facture(){
		
	}
}