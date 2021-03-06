package connect;
import java.lang.reflect.*;
public class Formulaire {
	public Champ[] champs = new Champ[0];
	Object obj;
	public Formulaire(Object o) {
		this.initChamps(o);
		this.obj = o;
	}
	
	public void initChamps(Object obj) {
		//on cree les champs
		Class c = obj.getClass();
		Field[] f = c.getFields();
		Champ[] res = new Champ[f.length];
		for(int i=0; i < f.length; i++) {
			String n = f[i].getName();
			res[i] = new Champ(n, "text", n);
		}
		this.champs = res;
	}
	
	public Champ getById(String idChamp) {
		for(int i=0; i < this.champs.length; i++) {
			if(this.champs[i].getId().equals(idChamp)) {
				return this.champs[i];
			}
		}
		return null;
	}
	public Champ getByName(String name) {
		for(int i=0; i < this.champs.length; i++) {
			if(this.champs[i].getName().equals(name)) {
				return this.champs[i];
			}
		}
		return null;
	}	
	public String getHTML() throws Exception {
		Class c = this.obj.getClass();
		Utils u = new Utils();
		String html = "<form method='get' action='insererGen.jsp'>";
		for(int i=0; i < this.champs.length; i++) {
			String nom = this.champs[i].getName();
			html += this.champs[i].getHTML() + "<br/>";
		}
		//le champ special qui contiendra la valeur
		Champ special = new Champ("nomClasse", "text", ""); //aucun label
		special.setVisible(false);
		special.setValue(c.getName());
		
		html += special.getHTML() + "<br/>";
		html += "<button>Valider</button>";
		html += "</form>";
		return html;
	}

}