package jirama;
import java.util.Date;
import java.text.SimpleDateFormat;
import database.*;
import general.*;
import java.sql.Connection;

public class Compteur{
    String idCompteur;
    String numComptElec;
    String idClient;
    String idPrepaidCompter;
    Date dateAchat;
    Date dateExpiration;


    Date addDate(Date now, int duree){
        Date d = now;
        int day = d.getDate()+duree;
        int month = d.getMonth();
        int year = d.getYear();
        d.setYear(year);
        if(day <= 31 && day > 0){
            d.setDate(day);
        } else {
            while(day > 31 && month < 11){
                day -= 31;
                month += 1;
                while(month >= 11){
                    month -= 11;
                    d.setYear(year+1);
                }
                d.setMonth(month);
            }
        }
		System.out.println("ici ="+d);
        return d;
    }
    public void setDateExpiration(Connection con)throws Exception{
        Generalise g = new Generalise();
        Object[] prepaidCompters = g.select(con, new PrepaidCompter(), "where idPrepaidCompter = '"+this.idPrepaidCompter+"'");
        int duree = ((PrepaidCompter)prepaidCompters[0]).getDuree();
        Date now = new Date();
        Date d = this.addDate(now, duree);
        this.setDateExpiration(d);
    }
    public Compteur(){

    }
    public Compteur(String idCompteur, String numComptElec, String idClient, String idPrepaidCompter){
        this.setIdCompteur(idCompteur);
        this.setNumComptElec(numComptElec);
        this.setIdClient(idClient);
        this.setIdPrepaidCompter(idPrepaidCompter);
        this.setDateAchat();
    }
    public void setDateExpiration(String dateExpiration)throws Exception{
        Date d = new SimpleDateFormat("yyyy-mm-dd").parse(dateExpiration);
        this.setDateExpiration(d);
    }
    public void setDateExpiration(Date dateExpiration){
        this.dateExpiration = dateExpiration;
    }
    public void setDateAchat(String dateAchat)throws Exception{
        Date d = new SimpleDateFormat("yyyy-mm-dd").parse(dateAchat);
        this.setDateAchat(d);
    }
    public void setDateAchat(Date dateAchat){
        this.dateAchat = dateAchat;
    }
    public void setDateAchat(){
        Date now = new Date();
        this.dateAchat = now;
    }
    public void setIdCompteur(String idCompteur){
        this.idCompteur = idCompteur;
    }
    public void setNumComptElec(String numComptElec){
        this.numComptElec = numComptElec;
    }
    public void setIdClient(String idClient){
        this.idClient = idClient;
    }
    public void setIdPrepaidCompter(String idPrepaidCompter){
        this.idPrepaidCompter = idPrepaidCompter;
    }
    public String getIdCompteur(){
        return this.idCompteur; 
    }
    public String getNumComptElec(){
        return this.numComptElec; 
    }
    public String getIdClient(){
        return this.idClient; 
    }
    public String getIdPrepaidCompter(){
        return this.idPrepaidCompter; 
    }
    public Date getDateAchat(){
        return this.dateAchat;
    }
    public Date getDateExpiration(){
        return this.dateExpiration;
    }
}