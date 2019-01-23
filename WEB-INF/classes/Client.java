package jirama;
public class Client{
	String idClient;
	String nomTarif;
	String nom;
	String prenom;
	String adresse;
	String numComptElec;
	String categorie;
	double argent;

	public void setArgent(String argent){
		Double d = new Double(argent);
		this.setArgent(d.doubleValue());
	}

	public Client(){
		
	}
	public Client(String idClient,String nomTarif,String nom,String prenom,String adresse,String numComptElec,String categorie){
		this.idClient=idClient;
		this.nomTarif=nomTarif;this.nom=nom;
		this.prenom=prenom;this.adresse=adresse;
		this.numComptElec=numComptElec;
		this.categorie=categorie;
		
	}
	public String getIdClient() {
		return this.idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getNomTarif() {
		return this.nomTarif;
	}

	public void setNomTarif(String nomTarif) {
		this.nomTarif = nomTarif;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumComptElec() {
		return this.numComptElec;
	}

	public void setNumComptElec(String numComptElec) {
		this.numComptElec = numComptElec;
	}

	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public double getArgent() {
		return this.argent;
	}

	public void setArgent(double argent) {
		this.argent = argent;
	}

}