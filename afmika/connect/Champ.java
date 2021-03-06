package connect;
public class Champ {
    String name = "";
    String type = "";
    String label = "";
    String id = "";
    String value = "";
    boolean visible = true;
    public Champ(String name, String type, String label) {
        this.setName(name);
        this.setType(type);
        this.setLabel(label);
    }
    public Champ(String name, String type, String label, String id) {
        this.setName(name);
        this.setType(type);
        this.setLabel(label);
        this.setId(id);
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setValue(String val) {
        this.value = val;
    }
    public String getId() {
        return this.id;
    }
    public String getLabel() {
        return this.label;
    }
    public String getValue() {
        return this.value;
    }
    public boolean getVisible() {
        return this.visible;
    }
    public String getName() {
        return this.name;
    }
    public String getHTML() {
        String html = "<label for='"+this.name+"'>"+(this.visible ? this.label : "")+"</label><br/>";
        String bout = (!this.visible) ? "hidden " : " ";
        bout += !this.id.equals("") ? "id='"+this.id+"'" : "";
        html += "<input type='"+this.type+"' name='"+this.name+"' "+bout+" value='"+this.value+"'/>";
        return html;
    }
}