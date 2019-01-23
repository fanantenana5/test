package jirama;

import java.sql.Connection;
import database.Connect;
import general.*;
import java.sql.Date;

public class FactureAvoir{
	String idFactureAvoir;
	String idFacture;
	double montant;
	Date dateFactureAvoir;
	int typeAnnulation = -1;
	
	
	public void setNegativeMontant(String montant){
		Double d = new Double(montant);
		this.montant = d.doubleValue() * -1;	
	}
	public void save()throws Exception{
		Connection con = null;
		try{
			Connect c = new Connect();
			con = c.connexion();
			Generalise g = new Generalise();
			Object[] fact = g.select(con, new Facture(), "idFacture='"+this.getIdFacture()+"'");
			if(this.getMontant() + ((Facture)fact[0]).getMontantTotal() < 0){
				throw new Exception("le montant ne doit pas etre superieur au montant de la facture");
			} else if(this.getMontant() + ((Facture)fact[0]).getMontantTotal() > 0){
				this.typeAnnulation = 1;
			} else{
				this.typeAnnulation = 0;
			}
		} catch(Exception e){
			throw e;
		} finally{
			con.close();
		}
		
	}
	
	public int getTypeAnnulation(){
		return this.typeAnnulation;
	}
	
	public void setTypeAnnulation(int typeAnnulation){
		this.typeAnnulation = typeAnnulation;
	}
	
	public void setDateFactureAvoir(String dateFactureAvoir)throws Exception{
		Generalise g = new Generalise();
		Date d = new Date(g.controlDate(dateFactureAvoir).getTime());
		//Date d = new Date(Date.parse(dateFactureAvoir));
		this.setDateFactureAvoir(d);
	}
	
	public void setMontant(String montant){
		Double d = new Double(montant);
		this.setMontant(d.doubleValue());
	}
	
	public void setIdFactureAvoir(String idFactureAvoir){
		this.idFactureAvoir = idFactureAvoir;
	}
	public void setIdFacture(String idFacture){
		this.idFacture = idFacture;
	}
	public void setMontant(double montant){
		this.montant = montant;
	}
	public void setDateFactureAvoir(Date dateFactureAvoir){
		this.dateFactureAvoir = dateFactureAvoir;
	}
	public String getIdFactureAvoir(){
		return this.idFactureAvoir;
	}
	public String getIdFacture(){
		return this.idFacture;
	}
	public double getMontant(){
		return this.montant;
	}
	public Date getDateFactureAvoir(){
		return this.dateFactureAvoir;
	}
}