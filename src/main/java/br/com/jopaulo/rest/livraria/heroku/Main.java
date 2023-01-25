package br.com.jopaulo.rest.livraria.heroku;

import java.io.File;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * This class launches the web application in an embedded Jetty container. This is the entry point to your application. The Java
 * command that is used for launching should fire this main method.
 */
public class Main {

    public static void main(String[] args) throws Exception{
//    	File fileKeystore = new File("server.keytore");
//    	
//    	final Server server = new Server();
//    	
//    	HttpConfiguration httpConfiguration = new HttpConfiguration();
//    	httpConfiguration.setSecureScheme("https");
//    	httpConfiguration.setSecurePort(8443);
//    	
//    	ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(httpConfiguration));
//    	http.setPort(8080);
//    	
//    	SslContextFactory contextFactory = new SslContextFactory() {};
//    	contextFactory.setKeyStorePath(fileKeystore.getAbsolutePath());
//    	contextFactory.setKeyStorePassword("senha");
//    	contextFactory.setKeyManagerPassword("senha");
//    	
//    	HttpConfiguration httpsConfiguration = new HttpConfiguration(httpConfiguration);
//    	
//    	ServerConnector https = new ServerConnector(server, 
//    			new SslContextFactory(contextFactory, 
//    					HttpVersion.HTTP_1_1.asString()), 
//    			new HttpConnectionFactory(httpsConfiguration));
//    	https.setPort(8443);
//    	
//    	server.setConnectors(new ServerConnector[] {http, https});
    	
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        final Server server = new Server(Integer.valueOf(webPort));
        final WebAppContext root = new WebAppContext();

        root.setContextPath("/livraria-virtual");
        root.setParentLoaderPriority(true);

        final String webappDirLocation = "src/main/webapp/";
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);

        server.setHandler(root);

        server.start();
        server.join();
    }
}
