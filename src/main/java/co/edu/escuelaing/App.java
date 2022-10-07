package co.edu.escuelaing;

import static spark.Spark.*;
import static co.edu.escuelaing.SecureURLReader.*;


public class App {
    public static void main(String[] args) {
        port(getPort());
        secure(getKeyStore(), getKeyPwdStore(), null, null);
        loadTrustStore(getTrustStore(),getKeyPwdStore());
        get("hello", (req,res) -> "Hello " + getName() + "!");
        get("remote", (req,res) -> readURL(getLink()));
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001;
    }

    private static String getKeyStore() {
        if (System.getenv("KS") != null) {
            return System.getenv("KS");
        }
        return "";
    }

    private static String getKeyPwdStore() {
        if (System.getenv("KSPWD") != null) {
            return System.getenv("KSPWD");
        }
        return "";
    }

    private static String getTrustStore() {
        if (System.getenv("TSTORE") != null) {
            return System.getenv("TSTORE");
        }
        return "";
    }

    private static String getLink() {
        if (System.getenv("LINK") != null) {
            return System.getenv("LINK");
        }
        return "";
    }

    private static String getName() {
        if (System.getenv("NAME") != null) {
            return System.getenv("NAME");
        }
        return "";
    }
}
