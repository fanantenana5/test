package general;

import java.sql.*;
import java.lang.reflect.*;
import database.*;
import java.util.Vector;
import java.util.Date;

public class Generalisation{
	/* Object find(String tableName,String condition,Connection conn)throws Exception{
		String requete = "select * from"+tableName+condition;
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery(requete);
		Class c = Class.forName(tableName);
		Field[] attr = c.getDeclaredFields();
		while(result.next()){
			for(int i=0;i<attr.length;i++){
				String name = attr[i].getName();
			}
		}
		
		
		state.close();
		conn.close();
		return obj;
	} */

	public int update(String table,String colonne,String value,String condition,String valeurCondition,Connection con)throws Exception{
		int reponse = 0;
		String requete ="UPDATE "+table+" set "+colonne+"= "+"'"+value+"' where "+condition+"= '"+valeurCondition+"'";
		boolean b = false;
		if(con == null){
			Connect db = new Connect();
			con = db.connexion();
			b = true;
		}
		Statement state = con.createStatement();
		reponse = state.executeUpdate(requete);
		state.close();
		if(b == true){
			con.close();
		}
		return reponse;
	}

	public double somme(Object[] liste,String nomColonne)throws Exception{
		double somme = 0;
		String methodName = "get"+nomColonne.substring(0,1).toUpperCase()+nomColonne.substring(1);
		Class c = liste[0].getClass();
		for(int i=0;i<liste.length;i++){
			Method m = c.getMethod(methodName);
			somme += (double)(m.invoke(liste[i]));
		}
		return somme;
	}
	
	String getClassName(String classes){
		int index = classes.lastIndexOf('.');
		String reponse = classes.substring(index+1);
		return reponse;
	}

	String createSetter(Field[] attr){
		String html ="";
		for(int i =0;i<attr.length;i++){
			String nom = attr[i].getName();
			String type = this.getClassName(attr[i].getType().getName());
			html += "public void ";
			html += "set"+nom.substring(0,1).toUpperCase()+nom.substring(1)+"("+type+" "+attr[i].getName()+")"+"{"+"<br/>";
			html += "this."+nom+" = "+nom+";"+"<br/>";
			html += "}";
			html += "<br/>";
		}
		return html;
	}

	String createGetter(Field[] attr){
		String html = "";
		for(int i =0;i<attr.length;i++){
			String nom = attr[i].getName();
			String type = this.getClassName(attr[i].getType().getName());
			html += "public "+type;
			html += " get"+nom.substring(0,1).toUpperCase()+nom.substring(1)+"()"+"{"+"<br/>";
			html += "return this."+nom+"; <br/>";
			html += "}";
			html += "<br/>";
		}
		return html;
	}

	public String getCodeClass(Object obj){
		String html = "";
		Class c = obj.getClass();
		String name = this.getClassName(c.getName());
		html += "public class "+name+"{"+"<br/>";
		Field[] attr = c.getDeclaredFields();
		html += this.createSetter(attr);
		html += this.createGetter(attr);
		html += "}";
		return html;
	}

	public String couper(String s){
		String reponse;
		int place=s.lastIndexOf(",");
		reponse=s.substring(0,place);
		return reponse;
	}

	String convertDate(Date d){
		String date = "";
		int year = d.getYear() + 1900;
		int month = d.getMonth() + 1;
		int day = d.getDate();
		date = day +"-" +month+ "-" + year;
		System.out.println("jour = "+day+" mois= "+month+" annee= "+year+" date = "+date);
		return date;
	}

	public String insert(Object obj,Connection con)throws Exception{
		boolean b = false;
		if(con == null){
			Connect db = new Connect();
			con = db.connexion();
			b = true;
		}
		Statement state = con.createStatement();
		Class c = obj.getClass();
		String nomTable=this.getClassName(c.getName());
		Field [] attr =  c.getDeclaredFields();
		String requete="insert into " +nomTable+" values(";
		for(int i=0;i<attr.length;i++){
			String nomFonc = attr[i].getName();
			nomFonc = nomFonc.substring(0,1).toUpperCase() + nomFonc.substring(1);
			nomFonc = "get"+nomFonc;
			Method m=c.getMethod(nomFonc);
			Class retour=m.getReturnType();
			String type=this.getClassName(retour.getName());
			System.out.println(type);
            String x=(m.invoke(obj)).toString();
			System.out.println(x);
            if(type.equals("double")==true||type.equals("int")==true){
                requete=requete +x+",";
            }else if(type.equals("Timestamp")){
				requete += "timestamp" +"'" +x+"'"+","; 
			} else if(type.equals("Date")){
				System.out.println("ca passe");
				requete += "'"+this.convertDate((Date)(m.invoke(obj))).toString()+"',";
			}
            else{
                requete=requete +"'" +x+"'"+",";
            }
            
		}
		requete=this.couper(requete);
		requete=requete + ")";	
		state.close();
		if(b == true){
			con.close();
		}
		return requete;
	}

	public int executeInsert(Object obj,Connection con)throws Exception{
		boolean b = false;
		if(con == null){
			Connect db = new Connect();
			con = db.connexion();
			b = true;
		}
		Statement state = con.createStatement();
        String requete=this.insert(obj,con);
        System.out.println(requete);
        //int r =state.executeUpdate("alter session  set NLS_DATE_FORMAT='YYYY-MM-DD'");
        int r =state.executeUpdate("alter session set nls_date_format = 'DD-MM-YYYY HH24:MI:SS'");
		int nbr=state.executeUpdate(requete);
		state.close();
		if(b == true){
			con.close();
		}
		return nbr;
	}

	public String autoIncrement(String sequence,Connection con)throws Exception{
		boolean b = false;
		if(con == null){
			Connect db = new Connect();
			con = db.connexion();
			b = true;
		}
		Statement state = con.createStatement();
		String val=sequence+".nextval";
		String requete="select "+val+" from dual";
		ResultSet result=state.executeQuery(requete);
		String reponse="";
		for(int i=0;result.next();i++){
			reponse=result.getString("nextval");
		}
		System.out.println(reponse);
		state.close();
		if(b == true){
			con.close();
		}
		return reponse;
	}
}
