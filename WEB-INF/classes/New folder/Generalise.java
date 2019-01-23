package general;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import database.*;

public class Generalise {

	public Object[] select(String column, String value, Object[] ob_tab) throws Exception {
		Method m = getFunction(ob_tab,column);
		Object[] res = new Object[0];
		
		for(int e=0; e < ob_tab.length; e++) {
			String str = m.invoke(ob_tab[e]).toString().toLowerCase();
			
			// System.out.println("str == " + str);
			// System.out.println("value == " + value);
			// System.out.println("comp == " + str.compareTo(value.toLowerCase()));
			
			if(str.indexOf(value.toLowerCase()) != -1) {
				res = addObj(res,ob_tab[e]);
				// System.out.println("trouve");
			}
		}
		
		return res;
	}
	
	public Method getFunction(Object[] objs, String att) throws Exception {
		att = att.toLowerCase();
        String nomFonction = "get" + att.substring(0,1).toUpperCase() + att.substring(1);
        Class c = objs[0].getClass();
        Method m = c.getMethod(nomFonction);

        return m;
    }
	
	public Object[] addObj(Object[] tab, Object obj) {
		int n = tab.length;
		Object[] res = new Object[n+1];
		int i = 0;
		
		while(i < n) {
			res[i] = tab[i];
			i++;
		}
		res[n] = obj;
		
		return res;
	}

	public Object[] ajoutObj(Object[] obj, Object o) {
		Object[] nouv = new Object[obj.length+1];
		int i = 0;
		
		while(i < obj.length) {
			nouv[i] = obj[i];
			
			i++;
		}
		
		nouv[i] = o;
		
		return nouv;
	}
	
	public String getTableFromClass(Class c) {
        String table_name = c.getName();
        int n = table_name.lastIndexOf('.') + 1;
        table_name = table_name.substring(n);
        
        return table_name;
    }
	
	public String FName(String type, String att) {
		String nomF = type + att.substring(0,1).toUpperCase() + att.substring(1);
		
		return nomF;
	}
	
	public Object[] select(Connection con, Object o, String where) throws Exception {
		boolean b = false;
		Object[] res = new Object[0];
		try{
			if(con == null){
				Connect db = new Connect();
				con = db.connexion();
				b = true;
			}
			Class c = o.getClass();
			String nt = getTableFromClass(c);
			String sql = "SELECT * FROM " + nt;	
			if(where != null) {
				sql += " " + where;
			}
			System.out.println(sql);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			Field[] f = c.getDeclaredFields();
		
			for(int p=0; rs.next(); p++) {
				Object obj = c.newInstance();

				for(int i=0; i < f.length; i++) {
					String att = f[i].getName();
					String nF = FName("set",att);
					Method m = null;
				
					try {
						m = c.getMethod(nF,String.class);
					}
					catch(Exception e) {
						throw new Exception("Vous devez creer la fonction " + nF + "(String "+ att +") dans la classe " + nt);
					}
					String v = rs.getString(att);
					m.invoke(obj,v);
				}
			
				res = ajoutObj(res,obj);
			}
		
			rs.close();
			s.close();
		} catch(Exception e){
			throw e;
		} finally{
			if(b==true){
				con.close();
			}
		}
		return res;
	}
}