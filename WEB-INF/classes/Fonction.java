package fonction;
import java.lang.reflect.*;

public class Fonction{
	public String getHtml(Object obj){
		String html = "";
		Class cl = obj.getClass();
		Field[] attr = cl.getDeclaredFields();
		html += "<table>";
		for(int i=0;i<attr.length;i++){
			String name=attr[i].getName();
			html += "<tr>";
			html += "<td>";
			html += "<label for='"+name+"'>"+name+"</label>";
			html += "</td>";
			html += "<td>"; 
			html += "<input type='text' name='"+name+"'placeholder='"+name+"'/>";
			html += "</td>";
			html += "</tr>";
		}
		html += "<tr>";
		html += "<td>";
		html += "<input type='submit' value='valider' />";
		html += "</tr>";
		html += "</td>";
		html +=  "</table>";
		return html;
	}
	
	public String createFormulaire(Object obj){
		String html = "";
		return html;
	}
}