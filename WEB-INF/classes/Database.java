package database;

import java.sql.*;
import jirama.*;
import general.*;

public class Database{
	
	public int annulationFacture(FactureAvoir fa)throws Exception{
		int rep = -1;
		if(fa.getTypeAnnulation() == 0){
			rep = this.annulerPrelevement(fa, fa.getDateFactureAvoir());
		}
		return rep;
	}
	
	public int annulerPrelevement(FactureAvoir fa, Date date)throws Exception{
		int rep = 0;
		Connect c = new Connect();
		Connection con = c.connexion();
		Generalisation gen = new Generalisation();
		Generalise g = new Generalise();
		Object[] factures = g.select(con, new Facture(), "idFacture = '"+fa.getIdFacture()+"'");
		PrelevementAnnulee pa = new PrelevementAnnulee();
		pa.setIdPrelevementAnnulee(gen.autoIncrement("idPrelevementAnnulee", con));
		pa.setIdPrelevement(((Facture)factures[0]).getIdPrelevement());
		pa.setDatePrelevementAnnulee(date);
		gen.executeInsert(pa, con);
		con.close();
		return rep;
	}
}