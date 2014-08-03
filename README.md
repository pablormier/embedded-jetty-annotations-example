# Embedded Jetty template with support for JSP and Servlet 3.0 annotations

This is a template based on the original template for [Jetty Embedded Servlet](https://github.com/jetty-project/embedded-servlet-3.0)
and the [Heroku Embedded Jetty Example](https://github.com/heroku/template-java-embedded-jetty)
                                        

## Running the application locally

First build with:

    $mvn clean install

Then run it with:

    $java -cp target/classes:"target/dependency/*" com.example.Launcher

