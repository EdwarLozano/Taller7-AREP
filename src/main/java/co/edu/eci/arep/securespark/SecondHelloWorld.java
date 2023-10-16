package co.edu.eci.arep.securespark;

import static spark.Spark.*;

public class SecondHelloWorld {

    private static final String SECOND_SERVICE_URL = "https://ec2-54-226-41-202.compute-1.amazonaws.com:5000/hello";

    private static final String PATH_FILE = "target/certificados/ecikeystore.p12";

    public static void main(String[] args) {
        port(getPort());
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        secure("target/keystores/ecikeystore2.p12", "123456", null, null);
        get("/hello", (req, res) -> "Hello World, this is the FIRST Hello World service");
        get("/hellosecondservice", (req, res) -> URLReader.main(PATH_FILE, SECOND_SERVICE_URL));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 9000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
