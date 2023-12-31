package co.edu.eci.arep.securespark;

import static spark.Spark.*;

public class HelloWorld {

    private static final String SECOND_SERVICE_URL = "https://ec2-34-229-16-130.compute-1.amazonaws.com:9000/hello2";

    private static final String PATH_FILE = "target/certificados/ecikeystore2.p12";

    public static void main(String[] args) {
        port(getPort());
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        secure("target/keystores/ecikeystore.p12", "123456", null, null);
        get("/hello", (req, res) -> "Hello World, this is the FIRST Hello World service");
        get("/hellosecondservice", (req, res) -> URLReader.main(PATH_FILE, SECOND_SERVICE_URL));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}