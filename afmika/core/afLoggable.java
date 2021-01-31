package core;

/**
 * @author afmika
 */
public class afLoggable {
    private boolean all_log = true;
    private boolean std_log = true;
    private boolean err_log = true;

    public void enableLog () {
        this.all_log = true;
    }
    public void disableLog () {
        this.all_log = false;
    }

    public void enableSTDLog () {
        this.std_log = true;   
    }
    public void disableSTDLog () {
        this.std_log = false;   
    }

    public void enableERRLog () {
        this.err_log = true;   
    }
    public void disableERRLog () {
        this.err_log = false;   
    }

    public void log (Object x) {
        if (this.all_log && this.std_log)
            System.out.println("[AF_LOG] " + x);
    }

    public void err (Object x) {
        if (this.all_log && this.err_log)
            System.err.println("[AF_ERR] " + x);
    }

    public void copyLogConfigTo (afLoggable loggable) {
        loggable.setAll_log(all_log);
        loggable.setStd_log(std_log);
        loggable.setErr_log(err_log);
    }

    public boolean isAll_log() {
        return all_log;
    }

    public void setAll_log(boolean all_log) {
        this.all_log = all_log;
    }

    public boolean isStd_log() {
        return std_log;
    }

    public void setStd_log(boolean std_log) {
        this.std_log = std_log;
    }

    public boolean isErr_log() {
        return err_log;
    }

    public void setErr_log(boolean err_log) {
        this.err_log = err_log;
    }
}