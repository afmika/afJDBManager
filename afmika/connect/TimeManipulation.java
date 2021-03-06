package connect;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeManipulation {
    
    public static Timestamp geDatePaque(int annee) {
        // application de l algo de Butcher-Meeus
        int a = annee % 19,
            b = annee / 100,
            c = annee % 100,
            d = b / 4,
            e = b % 4,
            g = (8 * b + 13) / 25,
            h = (19 * a + b - d - g + 15) % 30,
            j = c / 4,
            k = c % 4,
            m = (a + 11 * h) / 319,
            r = (2 * e + 2 * j - k - h + m + 32) % 7,
            n = (h - m + r + 90) / 25,
            p = (h - m + r + n + 19) % 32;
        return Timestamp.valueOf(annee+"-"+n+"-"+p+" 00:00:00");
    }
    
    public static Timestamp addJour(Timestamp date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, n);
        Timestamp time = new Timestamp(calendar.getTime().getTime());
        return time;
    }
    
    public static Timestamp addMinutes(Timestamp date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.MINUTE, n);
        Timestamp time = new Timestamp(calendar.getTime().getTime());
        return time;
    }
    
    public static String getDatePartOf (Timestamp time) {
        String[] temp = time.toString().split(" ");
        return temp[0];
    }
    
    public static Timestamp getNow() {
        Calendar calendar = Calendar.getInstance();
        Timestamp time = new Timestamp(calendar.getTime().getTime());
        return time;
    }

    public static String getNowDateOnlyAsString() {
        Calendar calendar = Calendar.getInstance();
        Timestamp time = new Timestamp(calendar.getTime().getTime());
        String[] temp = time.toString().split(" ");
        return temp[0];
    }
    
    public static int diffDeuxDate(Timestamp a, Timestamp b) {
        LocalDateTime date1 = a.toLocalDateTime();
        LocalDateTime date2 = b.toLocalDateTime();
        //System.out.println(date1);
        //System.out.println(date2);
        int diff = (int) ChronoUnit.DAYS.between(date1, date2);
        return diff;
    }
    
    public static int diffDeuxDateMinutes(Timestamp a, Timestamp b) {
        LocalDateTime date1 = a.toLocalDateTime();
        LocalDateTime date2 = b.toLocalDateTime();
        //System.out.println(date1);
        //System.out.println(date2);
        int diff = (int) ChronoUnit.MINUTES.between(date1, date2);
        return diff;
    }
    
    public static boolean isBetweenStrictly(Timestamp start, Timestamp end, Timestamp to_test) {
        return start.compareTo(to_test) < 0 && end.compareTo(to_test) > 0;
    }
    
    public static boolean estFerier(Timestamp a) {
        Vector<Timestamp> feriers = new Vector<Timestamp>();
        feriers.add(Timestamp.valueOf("2000-01-01 00:00:00"));
        feriers.add(Timestamp.valueOf("2000-11-01 00:00:00"));
        feriers.add(Timestamp.valueOf("2000-12-25 00:00:00"));

        // jours de paques a ne pas oublier!
        Timestamp paque = geDatePaque(2019);
        Timestamp lundipaque = addJour(paque, 1);
        feriers.add(paque);
        feriers.add(lundipaque);

        for (int i = 0; i < feriers.size(); i++) {
            Timestamp jourFerier = feriers.get(i);
            if(a.getMonth() == jourFerier.getMonth() && a.getDate() == jourFerier.getDate()) {
                return true;
            }
        }
        // samedi ou dimanche
        if(a.getDay() == 6 || a.getDay() == 0) {
            return true;
        }
        return false;
    }

    public static int getNbrJoursWithoutFerierBetween(Timestamp a, Timestamp b) {
        // les jours feriers sont les samedis et dimanches
        // 1er janvier
        int nbrJours = diffDeuxDate(a, b);
        // System.out.println("-------\nJours au total "+nbrJours);
        int joursValides = 0;
        Timestamp nextDay = a;
        for(int i=0; i < nbrJours; i++) {
                // on parcours les jours si on rencontre un jour ferier alors on s arrete
                // jour apres le jour a, juste pour le projet Bourse
                nextDay = addJour(nextDay, 1);
                if(estFerier(nextDay) == false) {
                    joursValides++;
                } else {
                    // System.out.println("Jour Ferier le "+nextDay);
                }
        }
        // System.out.println("Jours valides "+joursValides+"\n-------");
        return joursValides;
    }
}
