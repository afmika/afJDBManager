package operation;

import java.util.ArrayList;

import core.*;

// temporary class
class Sequence {
    int value = 0;
    public Sequence () {}
    public int getValue () {
        return value;
    }
    public void setValue (int value) {
        this.value = value;
    }
}

public class afSequenceOperation extends afOperation {

    String sequence_name = null;
	public afSequenceOperation(afQuery afquery, String sequence_name) throws Exception {
        super(afquery);
        this.sequence_name = sequence_name;
    }
    
    public int nextValue () throws Exception {
        String sql = "SELECT " + sequence_name + ".nextval value FROM DUAL";
        Sequence instance = new Sequence();
        ArrayList<Sequence> out = afquery.run(sql).<Sequence>get(instance);
        return out.get(0).getValue();
    }

    public int currentValue () throws Exception {
        String sql = "SELECT " + sequence_name + ".currval value FROM DUAL";
        Sequence instance = new Sequence();
        ArrayList<Sequence> out = afquery.run(sql).<Sequence>get(instance);
        return out.get(0).getValue();
    }
}
