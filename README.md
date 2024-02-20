# EJB-JPA Homework

> java-21-openjdk
> glassfish-7.0.0
> wildfly-31.0.0

> Simple hosted on `http://localhost:8080/ejb-jpa-1.0/home`
> JNDI hosted on `http://localhost:8080/ejb-jpa-jndi-1.0/home`

## Build java app

Run `gradle clean build` in the source base dir.

## GlassFish

Copy the generated `.war` into `$GLASSFISH_HOME/glassfish/domains/domain1/autodeploy`
Run `$GLASSFISH_HOME/bin/asadmin start-domain` and `stop-domain` to start and stop the server.

## WildFly

Copy the generated `.war` into `standalone/deployments`.
Run `$WILDFLY_HOME/bin/standalone.sh` to run the server.
