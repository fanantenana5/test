package fonction;

import java.lang.reflect.Field;

public class Champ {
    Field attrib;
    Boolean visible;
    String[] defaut;
    String type;
	String value = " ";
	
	
	public void setValue(String value){
		this.value = value;
	}
	public String getValue(){
		return this.value;
	}
    public Champ() {
        this.visible=true;
        this.type="text";
    }
    public String getType() {
        return this.type;
    }
    public Field getAttrib() {
        return this.attrib;
    }
    public Boolean getVisible() {
        return this.visible;
    }
    public String[] getDefaut() {
        return this.defaut;
    }
    public void setType(String a) {
        this.type=a;
    }
    public void setVisible(Boolean a) {
        this.visible=a;
    }
      public void setDefaut(String[] a) {
        this.defaut=a;
    }
     public void setAttrib(Field a) {
        this.attrib=a;
    }
}