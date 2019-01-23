package jirama;
public class PrepaidCompter{
    String idPrepaidCompter;
    double prix;
    int quantite;
    int duree;

    public void setDuree(String duree){
        this.setDuree(Integer.valueOf(duree));
    }

    public void setPrix(String prix){
        Double d = new Double(prix);
        this.setPrix(d.doubleValue());
    }
    public void setQuantite(String quantite){
        this.setQuantite(Integer.valueOf(quantite));
    }
    public void setIdPrepaidCompter(String idPrepaidCompter){
        this.idPrepaidCompter = idPrepaidCompter;
    }
    public void setPrix(double prix){
        this.prix = prix;
    }
    public void setQuantite(int quantite){
        this.quantite = quantite;
    }
    public void setDuree(int duree){
        this.duree = duree;
    }
    public String getIdPrepaidCompter(){
        return this.idPrepaidCompter; 
    }
    public double getPrix(){
        return this.prix; 
    }
    public int getQuantite(){
        return this.quantite; 
    }
    public int getDuree(){
        return this.duree;
    }
}