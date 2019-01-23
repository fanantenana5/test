package jirama;

import java.sql.Date;
import general.*;

public class PrelevementAnnulee{
	String idPrelevementAnnulee;
	String idPrelevement;
	Date datePrelevementAnnulee;
	
	public void setDatePrelevementAnnulee(String datePrelevementAnnulee)throws Exception{
		Generalise g = new Generalise();
		Date d = new Date(g.controlDate(datePrelevementAnnulee).getTime());
		//Date d = new Date(Date.parse(dateFactureAvoir));
		this.setDatePrelevementAnnulee(d);
	}
	
	public void setIdPrelevementAnnulee(String idPrelevementAnnulee){
		this.idPrelevementAnnulee = idPrelevementAnnulee;
	}
	public void setIdPrelevement(String idPrelevement){
		this.idPrelevement = idPrelevement;
	}
	public void setDatePrelevementAnnulee(Date datePrelevementAnnulee){
		this.datePrelevementAnnulee = datePrelevementAnnulee;
	}
	public String getIdPrelevementAnnulee(){
		return this.idPrelevementAnnulee;
	}
	public String getIdPrelevement(){
		return this.idPrelevement;
	}
	public Date getDatePrelevementAnnulee(){
		return this.datePrelevementAnnulee;
	}
}