package jirama;
public class Tarif{
	String nomTarif;
	double prixUnitaireElec;
	double prixUnitaireElec2;
	double prixUnitaireElec3;
	int limiteElec;
	int limiteElec2;
	
	
	public Tarif(){
		
	}
	public Tarif(String nomTarif,double prixUnitaireElec,double prixUnitaireElec2,double prixUnitaireElec3,int limiteElec,int limiteElec2){
		this.nomTarif = nomTarif;
		this.prixUnitaireElec = prixUnitaireElec;
		this.prixUnitaireElec2 = prixUnitaireElec2;
		this.prixUnitaireElec3 = prixUnitaireElec3;
		this.limiteElec = limiteElec;
		this.limiteElec2= limiteElec2;
	}
	public String getNomTarif() {
		return this.nomTarif;
	}

	public void setNomTarif(String nomTarif) {
		this.nomTarif = nomTarif;
	}

	public double getPrixUnitaireElec() {
		return this.prixUnitaireElec;
	}

	public void setPrixUnitaireElec(double prixUnitaireElec) {
		this.prixUnitaireElec = prixUnitaireElec;
	}

	public double getPrixUnitaireElec2() {
		return this.prixUnitaireElec2;
	}

	public void setPrixUnitaireElec2(double prixUnitaireElec2) {
		this.prixUnitaireElec2 = prixUnitaireElec2;
	}

	public int getLimiteElec() {
		return this.limiteElec;
	}

	public void setLimiteElec(int limiteElec) {
		this.limiteElec = limiteElec;
	}
	
	public void setLimiteElec2(int limiteElec2) {
		this.limiteElec2 = limiteElec2;
	}
	
	public int getLimiteElec2() {
		return this.limiteElec2;
	}
	
	public void setPrixUnitaireElec3(double prixUnitaireElec3){
		this.prixUnitaireElec3= prixUnitaireElec3;
	}
	
	public double getPrixUnitaireElec3(){
		return this.prixUnitaireElec3;
	}
}