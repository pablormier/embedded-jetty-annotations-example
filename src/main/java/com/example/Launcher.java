package com.example;


import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;

/**
 * 
 * This class launches the web application in an embedded Jetty container and activates
 * support for Servlet 3.0 annotations. This example is based on https://github.com/jetty-project/embedded-servlet-3.0/
 * and https://github.com/heroku/template-java-embedded-jetty.
 *
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        // Take the port from the environment variable and launch the server
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) webPort = "8080";
        Server server = new Server(Integer.valueOf(webPort));
        
        // Since we don't package the project as a war, we use our src/main/webapp directory
        // as the resource base directory for the server
        String webappDir = "src/main/webapp/";

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setResourceBase(webappDir);
        // Descriptor file (empty)
        context.setDescriptor(webappDir + "WEB-INF/web.xml");
        // Configuration classes. This gives support for multiple features.
        // The annotationConfiguration is required to support annotations like @WebServlet
        context.setConfigurations(new Configuration[] {
                new AnnotationConfiguration(), new WebXmlConfiguration(),
                new WebInfConfiguration(),
                new PlusConfiguration(), new MetaInfConfiguration(),
                new FragmentConfiguration(), new EnvConfiguration() });

	// Important! make sure Jetty scans all classes under ./classes looking for annotations. Classes
	// directory is generated running 'mvn package'
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/classes/.*");
        context.setParentLoaderPriority(true);
        
        // Config and launch server
        server.setHandler(context);
        server.start();
        server.dump(System.err);
        server.join();
    }

}
