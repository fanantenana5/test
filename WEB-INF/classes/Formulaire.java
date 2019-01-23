package fonction;

import java.sql.*;
import java.util.Vector;
import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Formulaire {
    Class lequel;
    Champ[] composant;
    String action;
	
	public void setAction(String action){
		this.action = action;
	}
	
    public Formulaire(Object o) {
        lequel = o.getClass();
        this.setcomposant();
		int i = lequel.getName().lastIndexOf(".");
        String name = lequel.getName().substring(i+1);
        action = "Insert"+name+".jsp";
    }
	
    public void setcomposant() {
        Field[] classes = lequel.getDeclaredFields();
        this.composant=new Champ[classes.length];
        for(int i=0;i<classes.length;i++) {
            this.composant[i] = new Champ();
            this.composant[i].setType("text");
            this.composant[i].setAttrib(classes[i]);
        }
    }
    public Class getlequel() {
        return this.lequel;
    }
    public Champ[] getcomposant() {
        return this.composant;
    }
    public String getAction() {
        return this.action;
    }
    public String getHTML() {
        String html="<form action="+this.action+" method=\"POST\">";
		System.out.println(this.lequel.getName());
		html += "<input type='hidden' name='className' value='"+this.lequel.getName()+"'>";
		for(int i=0;i<this.composant.length;i++) { 
			if(this.composant[i].getVisible()==true) {
				html+="<label>"+this.composant[i].getAttrib().getName()+"</label>";
				if(this.composant[i].getType().equals("text")) {
					html+="<input type="+this.composant[i].getType()+" value='"+this.composant[i].getValue()+"' name="+this.composant[i].getAttrib().getName()+"> </br>";
				}
				if(this.composant[i].getType().equals("select")) {
					html+="<select name="+this.composant[i].getAttrib().getName()+">";
					String[] value=this.composant[i].getDefaut();
					for(int f=0;f<value.length;f++) {
						html+="<option value="+value[f]+">"+value[f]+"</option>";
                    }
                    html+="</select></br>";
                }
                if(this.composant[i].getType().equals("checkbox")) {
					String[] value1=this.composant[i].getDefaut();
					for(int l=0;l<value1.length;l++) {
						html+="<input type=\"radio\" name="+this.composant[i].getAttrib().getName()+" value="+value1[l]+" id="+value1[l]+">";
						html+="<label for="+value1[l]+">"+value1[l]+"</label>";
					}
					html+="</br>";
                }
            }
			else if(this.composant[i].getVisible() == false){
				html += "<input type='hidden' value='"+this.composant[i].getValue()+"' name=" +this.composant[i].getAttrib().getName()+ ">";
			}
		}   
        html += "<input type=\"submit\" value=\"okey\">";
		html += "</form>";
		return html;
    }
    public Champ getChamp(String nom) {
        for(int i=0;i<composant.length;i++) {
            if(composant[i].getAttrib().getName().compareToIgnoreCase(nom)==0) {
                return composant[i];
            }
        }
        return null;
    }
    public void setChamp(String nom, Champ newC) {
        for(int i=0;i<composant.length;i++) {
            if(composant[i].getAttrib().getName().compareToIgnoreCase(nom)==0) {
                composant[i]=newC;
            }
        }
    }
}