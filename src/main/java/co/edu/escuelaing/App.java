package co.edu.escuelaing;

import static spark.Spark.*;
import static co.edu.escuelaing.SecureURLReader.*;

public class App {
    public static void main(String[] args) {
        port(getPort());
        secure(getKeyStore(), getKeyPwdStore(), null, null);
        loadTrustStore(getTrustStore(), getKeyPwdStore());
        get("hello", (req, res) -> "Hello " + getName() + "!");
        get("remote", (req, res) -> readURL(getLink()));
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

    private static String getKeyStore() {
        if (System.getenv("KS") != null) {
            return System.getenv("KS");
        }
        return "keystores/ecikeystore.p12";
    }

    private static String getKeyPwdStore() {
        if (System.getenv("KSPWD") != null) {
            return System.getenv("KSPWD");
        }
        return "eci123";
    }

    private static String getTrustStore() {
        if (System.getenv("TSTORE") != null) {
            return System.getenv("TSTORE");
        }
        return "keystores/awscertifies.p12";
    }

    private static String getLink() {
        if (System.getenv("LINK") != null) {
            return System.getenv("LINK");
        }
        return "https://ec2-3-82-150-130.compute-1.amazonaws.com:5000/hello";
    }

    private static String getName() {
        if (System.getenv("NAME") != null) {
            return System.getenv("NAME");
        }
        return "World";
    }
}
