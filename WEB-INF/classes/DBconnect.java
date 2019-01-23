package database;

import general.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import jirama.*;
import fonction.*;
import java.util.Vector;
import java.lang.reflect.*;

public class DBconnect{

	public int calculConsommation(int index, int ancientIndex, String numCompt,Connect con)throws Exception{
		int consommation = 0;
		if(con == null){
			con = new Connect();
		}
		Connection conn = con.connexion();
		Generalise gen = new Generalise();
		//maka compteur
		Object[] compteur = gen.select(conn, new Compteur(),"numComptElec = '"+numCompt+"' order by dateExpiration desc");
		if(compteur.length == 0){
			consommation = index - ancientIndex;
		} else {
			//maka offre prepayer
			boolean b = this.getOffreValidity(((Compteur)compteur[0]).getDateExpiration());
			String idPrepaidCompter = ((Compteur)compteur[0]).getIdPrepaidCompter();
			Object[] offre = gen.select(conn, new PrepaidCompter(),"idPrepaidCompter = '"+idPrepaidCompter+"'");
			int quantite = ((PrepaidCompter)offre[0]).getQuantite();
			consommation = index - ancientIndex;
			System.out.println("quantite ="+(quantite));
			System.out.println("consommation "+consommation);
			System.out.println("validite ="+b);
			if(b == true){
				if(quantite >= consommation){
					consommation = 0; 
				} else {
					consommation -= quantite;
				}
			}
		}
		conn.close();
		return consommation;
	}
	
	boolean getOffreValidity(Date expiration){
		boolean b = false;
		Date now = new Date();
		int month = expiration.getMonth();
		expiration.setMonth(month+1);
		System.out.println("month "+month);
		int n = now.compareTo(expiration);
		if(n<0){
			b = true;
		}
		return b;
	}


	private Facture[] transformTable(Object[] table){
		Facture[] reponse=new Facture[table.length];
		for(int i=0;i<table.length;i++){
			reponse[i]=(Facture)table[i];
		}
		return reponse;
	}
	
	public Facture[] getFacture(Client c,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		String id=c.getIdClient();
		String req="select * from facture where idClient like "+id;
		ResultSet result=state.executeQuery(req);
		Vector table=new Vector();
		Facture fact;
		for(int i=0;result.next();i++){
			fact=new Facture(result.getString("idFacture"),result.getString("idClient"),result.getString("idPrelevement"),result.getString("dateFacture"),result.getString("moisFacture"),result.getString("anneeFacture"),result.getString("agence"),result.getInt("consommationElec"),result.getDouble("montantElec"),result.getDouble("montantTotal"),result.getDouble("montantElec2"),result.getDouble("montantElec3"));
			table.add(fact);
		}
		Object[] tab=table.toArray();
		Facture[] reponse=this.transformTable(tab);
		
		state.close();
		conn.close();
		
		return reponse;
	}
	
	String clName(Class c){
		String reponse;
		String stock=c.getName();
		int place=stock.lastIndexOf(".");
		reponse=stock.substring(place+1);
		return reponse;
	}
	
	String couper(String s){
		String reponse;
		int place=s.lastIndexOf(",");
		reponse=s.substring(0,place);
		return reponse;
	}
	
	String couper2(String s){
		String reponse;
		int place=s.indexOf("-");
		int place2=s.lastIndexOf("-");			
		reponse=s.substring(place+1,place2);
			
		return reponse;
	}

	String couper3(String s){
		String reponse;
		int place=s.lastIndexOf("-");			
		reponse=s.substring(place+1);
		
		return reponse;
	}
	
	public String insert(Object obj,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		Class c = obj.getClass();
		String nomTable=this.clName(c);
		Field [] attr =  c.getDeclaredFields();
		String requete="insert into " +nomTable+" values(";
		for(int i=0;i<attr.length;i++){
			String nomFonc = attr[i].getName();
			nomFonc = nomFonc.substring(0,1).toUpperCase() + nomFonc.substring(1);
			nomFonc = "get"+nomFonc;
			Method m=c.getMethod(nomFonc);
			Class retour=m.getReturnType();
			String type=this.clName(retour);
			System.out.println(type);
			String x=(m.invoke(obj)).toString();
			System.out.println("blabla"+x);
			requete=requete +"'" +x+"'"+",";
		}
		requete=this.couper(requete);
		requete=requete + ")";
		
		state.close();
		conn.close();

		return requete;
	}
	
	public int executeInsert(Object obj,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		int r =state.executeUpdate("alter session set nls_date_format = 'DD-MM-YYYY HH24:MI:SS'");
		String requete=this.insert(obj,con);
		System.out.println(requete);
		int nbr=state.executeUpdate(requete);
		return nbr;
	}
	
	public Prelevement getPrelevement(String idPrelevement,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		Prelevement prel=new Prelevement();
		String requete ="SELECT * FROM PRELEVEMENT WHERE idPrelevement= '"+idPrelevement+"'";
		ResultSet result=state.executeQuery(requete);
		for(int i=0;result.next();i++){
			prel=new Prelevement(result.getString("idPrelevement"),result.getInt("indexCompteurElec"),result.getInt("ancientIndexElec"),result.getString("datePrelevement"),result.getInt("consommationElec"),result.getString("numComptElec"));
		}
		
		state.close();
		conn.close();

		return prel;
	}
	
	public Prelevement getPrelevement2(String numComptElec,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		Prelevement prel=new Prelevement();
		String requete ="SELECT * FROM PRELEVEMENT WHERE numComptElec= '"+numComptElec+"'";
		ResultSet result=state.executeQuery(requete);
		for(int i=0;result.next();i++){
			prel=new Prelevement(result.getString("idPrelevement"),result.getInt("indexCompteurElec"),result.getInt("ancientIndexElec"),result.getString("datePrelevement"),result.getInt("consommationElec"),result.getString("numComptElec"));
		}
		state.close();
		conn.close();

		return prel;
	}
	
	public Client getClient(String idPrelevement,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		Client c=new Client();
		Prelevement p=getPrelevement(idPrelevement,con);
		String requete="select * from Client where numComptElec = '"+p.getNumComptElec()+"'";
		ResultSet result=state.executeQuery(requete);
		for(int i=0;result.next();i++){
			c=new Client(result.getString("idClient"),result.getString("nomTarif"),result.getString("nom"),result.getString("prenom"),result.getString("adresse"),result.getString("numComptElec"),result.getString("categorie"));
		}
		state.close();
		conn.close();

		return c;
	}
	
	public Tarif getTarif(String idPrelevement,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		Tarif t=new Tarif();
		Client c=getClient(idPrelevement,con);
		String requete="select * from Tarif where nomTarif="+c.getNomTarif();
		ResultSet result=state.executeQuery(requete);
		for(int i=0;result.next();i++){
			t=new Tarif(result.getString("nomTarif"),result.getDouble("prixUnitaireElec"),result.getDouble("prixUnitaireElec2"),result.getDouble("prixUnitaireElec3"),result.getInt("limiteElec"),result.getInt("limiteElec2"));
		}
		state.close();
		conn.close();

		return t;
	}
	
	public int getDiff(int a,int b){
		int reponse;
		reponse=a-b;
		return reponse ;
	}
	
	public double[] getPrixElec(String idPrelevement,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		double[] prix = new double[3];
		
		Tarif  t = this.getTarif(idPrelevement,con);
		Prelevement p=this.getPrelevement(idPrelevement,con);
		
		int limite = t.getLimiteElec();
		int limite2 = t.getLimiteElec2();
		int consom = p.getConsommationElec();
		
		double pu1 = t.getPrixUnitaireElec();
		double pu2 = t.getPrixUnitaireElec2();
		double pu3 = t.getPrixUnitaireElec3();
		
		if(consom<=limite){
			prix[0] = consom * pu1;
			prix[1] = 0;
			prix[2] = 0;
		}
		else if(consom>=limite && consom<=limite2){
			int consom2 = consom - limite;
			prix[0] = limite * pu1;
			prix[1] = consom2 * pu2;
			prix[2] = 0;
		}
		else if(consom>=limite2){
			int consom2 = limite2-limite;
			int consom3 = consom - (limite2);
			prix[0] = limite * pu1;
			prix[1] = consom2 * pu2;
			prix[2] = consom3 * pu3;
		}
		state.close();
		conn.close();

		return prix;
	}
	
	public String autoIncrement(String sequence,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		String val=sequence+".nextval";
		String requete="select "+val+" from dual";
		ResultSet result=state.executeQuery(requete);
		String reponse="";
		for(int i=0;result.next();i++){
			reponse=result.getString("nextval");
		}
		state.close();
		conn.close();

		return reponse;
	}
	
	public String getMoisFacture(Facture f){
		String mois = f.getDateFacture();
		String moisFacture = this.couper2(mois);
		return moisFacture;
	}
	
	public String getAnneeFacture(Facture f){
		String annee = f.getDateFacture();
		String anneeFacture = this.couper3(annee);
		return anneeFacture;
	}
	public Facture[] executeSearch(String idPrelevement,String mois,String annee,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		Client c = this.getClient(idPrelevement,con);
		String search = "select*from facture where facture.moisFacture like '%"+mois+"%' and facture.anneeFacture like '%"+annee+"%'";
	
		ResultSet result =state.executeQuery(search);
		Facture fact;
		int n = 0;
		while (result.next()==true){
			n=n+1;
		}
		result = state.executeQuery(search);
		Facture[]f = new Facture[n];
		for(int i =0;result.next();i++){
			fact=new Facture(result.getString("idFacture"),result.getString("idClient"),result.getString("idPrelevement"),result.getString("dateFacture"),result.getString("moisFacture"),result.getString("anneeFacture"),result.getString("agence"),result.getInt("consommationElec"),result.getDouble("montantElec"),result.getDouble("montantTotal"),result.getDouble("montantElec2"),result.getDouble("montantElec3"));
			f[i]=fact;
		}
		state.close();
		conn.close();

		return f;
	}
	public Facture createFacture(String idPrelevement,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn = con.connexion();
		Statement state = conn.createStatement();
		Facture f = new Facture();
		Tarif t = this.getTarif(idPrelevement,con);
		Prelevement p = this.getPrelevement(idPrelevement,con);
		Client c = getClient(idPrelevement,con);
		String date = "17-10-2018";
		String autoIncrement = this.autoIncrement("idFacture",con);
		double[] prixElec = this.getPrixElec(idPrelevement,con);
		//int consElec = getDiff(p.getIndexCompteurElec(),p.getAncientIndexElec());
		int consElec = p.getConsommationElec();
		String mois = this.couper2(date);
		String annee = this.couper3(date);
		System.out.println("cons excep "+consElec);
		f.setConsommationElec(consElec);
		f.setIdFacture(autoIncrement);
		f.setIdClient(c.getIdClient());
		f.setIdPrelevement(idPrelevement);
		f.setDateFacture(date);
		System.out.println("annee ="+annee);
		f.setMoisFacture(mois);
		f.setAnneeFacture(annee);
		f.setAgence("ITU");
		
		f.setMontantElec(prixElec[0]);
		f.setMontantElec2(prixElec[1]);
		f.setMontantElec3(prixElec[2]);
		f.setMontantTotal(prixElec[0]+prixElec[1]+prixElec[2]);

		state.close();
		conn.close();

		return f;
	}
	
/*	public Prelevement createPrelevement(int i,int a,String d,String n)throws Exception{
		Prelevement p =new Prelevement();
		String autoinc=this.autoIncrement("idPrelevement");
		int c = 0;
		c = i - a ;
		
		p.setIdPrelevement(autoinc);
		p.setIndexCompteurElec(i);
		p.setAncientIndexElec(a);
		p.setDatePrelevement(d);
		p.setNumComptElec(n);
		p.setConsommationElec(c);
		
		return p;
	}
*/
	public void updateprel(String idPrelevement,Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		String requete = "update prelevement set etat = 10 where prelevement.idPrelevement ='"+idPrelevement+"'";
		state.executeQuery(requete);
		state.close();
		conn.close();

	}
	
	public Prelevement[] getPrelevementfact(Connect con)throws Exception{
		if(con == null){
			con = new Connect();
			con.connexion();
		}
		Connection conn =con.connexion();
		Statement state = conn.createStatement();
		String requete = "select * from  prefact ";
		
		ResultSet result =state.executeQuery(requete);
		ResultSetMetaData resultMeta = result.getMetaData();
		int i =0 ;
		int n =0;
		while(result.next()==true){
			n++;
		}
		String [] id = new String[n];
		Prelevement [] ps=new Prelevement[n];
		result =state.executeQuery(requete);
		while(result.next()==true){
			for (int e =1 ; e <=resultMeta.getColumnCount();e++){
				id[i]=result.getObject(e).toString();
				ps[i]=new Prelevement();
				ps[i].setIdPrelevement(id[i]);
				this.updateprel(ps[i].getIdPrelevement(),con);
			}
			i++;
		}
		state.close();
		conn.close();

		return ps;
	}
	/* public static void main(String[] args)throws Exception{
		DBconnect conex=new DBconnect();
		Connect con = new Connect();
		Formulaire f = new Formulaire();
		f.setChamp(new Prelevement());
		f.getChamp(0).setVisible(true);
		
		System.out.println(f.getHtml());
		//Facture f=conex.createFacture("1",con);
		//System.out.println(f.getDateFacture());
		//System.out.println(conex.insert(f,con));
		//String  g = conex.getMoisFacture(f);
		//String r = conex.getAnneeFacture(f);
		//conex.executeSearch("1","OCT","2018",con);
		
	} */
}