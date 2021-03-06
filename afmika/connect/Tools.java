package connect;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;
import java.util.regex.*;

public class Tools {
        public static void numeroChecker(String numero) throws Exception {
            String[] numeros = Tools.getOccurenceMatch(numero, "[0-9]{10}");
            System.out.println("Numero to check " + numero);
            if (numeros.length == 1) {
                if (!numero.startsWith("034"))
                    throw new Exception("Numero invalide! Doit commencer par 034");
                if (numero.length() != 10)
                    throw new Exception("Numero invalide! (doit contenir 10 chiffres)");
            } else
                throw new Exception("Numero invalide! (doit contenir 10 chiffres)");
        }
	public static String formatMoney(int number) {
            String n = "" + number;
            String res = "";
            int k=0;
            for(int i = n.length() - 1; i >= 0; i--) {
                    res = n.substring(i, i+1) + res;
                    res = (k+1) % 3 == 0 && i != 0 ?  " "+res : ""+res;
                    k++;
            }
            return res;
	}
        
	public static String formatMoney(BigDecimal number) {
            String n = "" + number;
            n = n.split("\\.")[0];
            String res = "";
            int k=0;
            for(int i = n.length() - 1; i >= 0; i--) {
                    res = n.substring(i, i+1) + res;
                    res = (k+1) % 3 == 0 && i != 0 ?  " "+res : ""+res;
                    k++;
            }
            return res;
	}
        
	public static  String formatMoney(double number) {
		String n = "" + number;
		n = n.split("\\.")[0];
		String res = "";
		int k=0;
		for(int i = n.length() - 1; i >= 0; i--) {
			res = n.substring(i, i+1) + res;
			res = (k+1) % 3 == 0 && i != 0 ?  " "+res : ""+res;
			k++;
		}
		return res;
	}
	public static String[] getOccurenceMatch(String str, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		Vector<String> liste = new Vector<String>();
		while(m.find()) {
				liste.add(m.group(0));
		}
		String[] listefin = new String[liste.size()];
		for(int i=0; i < listefin.length; i++) {
			listefin[i] = liste.get(i);
		}
		return listefin;
	}
	public static String getQuantiemeJour(Timestamp time) {
		String[] liste = {"dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
		int pos = time.getDay();
		return liste[pos];
	}
	public static  String joinStr(String[] tab, String join) {
		String res = "";
		for(int i=0; i < tab.length; i++) {
			res += tab[i];
			res += tab.length != i+1 ? join : "";
		}
		return res;
	}
	public static Timestamp str_to_Date(String date) throws Exception {
		String date_motif = "([0-9]{2}|[0-9]{4})[-\\/, .][0-9]{1,2}[-\\/, .]([0-9]{4}|[0-9]{2})";
		String heure_motif = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
		String date_part = "";
		String hour_part = " 00:00:00";
		String[] tmpDate = getOccurenceMatch(date, date_motif);
		String[] tmpHour = getOccurenceMatch(date, heure_motif);
		if(tmpDate.length == 0) {
			throw new Exception("Date introuvable");
		} else {
			date_part = tmpDate[0];
		}
		
		if(tmpHour.length == 1) {
			//une heure trouvee
			hour_part = " "+ tmpHour[0];
		}
		String[] res = date_part.split("[-\\.,_/ ]");
		Timestamp u = null; //resultat
		try {
			String tmp = joinStr(res, "-");
			u = Timestamp.valueOf(tmp + hour_part);
		} catch(Exception e) {
				try {
					String tmp2 = res[0];
					res[0] = res[2];
					res[2] = tmp2;
					String tmp = joinStr(res, "-");
					u = Timestamp.valueOf(tmp + hour_part);
				} catch(Exception e2) {
					throw new Exception("Date invalide essayer avec DD MM YYYY ou YYYY MM DD. On peut rajouter HH:MI:SS");
				}
		}
		
		if(u == null) {
			throw new Exception("Date invalide  essayer DDMMYYYY ou YYYYMMDD. On peut rajouter HH:MI:SS");
		}
		return u;
	}
}