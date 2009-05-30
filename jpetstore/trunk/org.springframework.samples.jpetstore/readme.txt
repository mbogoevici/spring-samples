=========================================
== Spring JPetStore sample application ==
=========================================

@author Juergen Hoeller
Based on Clinton Begin's JPetStore (http://www.ibatis.com).


1. MOTIVATION

Features a Spring-managed middle tier with iBATIS Database Layer as data access
strategy, in combination with Spring's transaction and DAO abstractions.
Can work with local JDBC transactions or JTA, with the latter on two databases.
Uses the same data model and demo contents as the original JPetStore.
See the context definitions "WEB-INF/dataAccessContext-local.xml" respectively
"WEB-INF/dataAccessContext-jta.xml" for details.

Offers two alternative web tier implementations with the same user interface:
one based on Spring's web MVC, and one based on Struts 1.2. The latter is close
to the original JPetStore but reworked for JSTL, to make the JSP implementations
as comparable as possible. See "WEB-INF/web.xml", "WEB-INF/petstore-servlet.xml",
and "WEB-INF/struts-config.xml" for details.

Compared to the original JPetStore, this implementation is significantly
improved in terms of internal structure and loose coupling: Leveraging Spring's
application context concept, there's a central place for wiring application
objects now. The most notable improvement is the former PetStoreLogic, now
called PetStoreFacade: It is no longer concerned with configuration, resource,
or transaction details.

Note that the Spring-based web tier implementation is deliberately similar to
the Struts-based one and does not aim to improve in terms of in-place error
messages or the like. The inclusion of two web tier alternatives outlines the
differences as well as the similarities in the respective programming model,
and also illustrates the different configuration styles.

2. BUILD AND DEPLOYMENT

This directory contains the web app source. For deployment, it needs to be built 
with Apache Maven. The only requirements are JDK >=1.5 and Maven >=2.0.8.

Run "mvn package" in this directory to build the war files. The war file 
(org.springframework.samples.jpetstore-1.0.0-SNAPSHOT.war) will be created 
in the "target" directory.

To execute the web application with its default settings, simply start the
HSQLDB instance in the "db/hsqldb" directory, for example using "server.bat".
For other databases, you'll need to use the corresponding schema and load scripts
in the "db" subdirectories (same as with the original JPetStore). In the local
case, the JDBC settings can be adapted in "WEB-INF/jdbc.properties". With JTA,
you need to set up corresponding DataSources in your J2EE container.

Note that the "WEB-INF/dataAccessContext-*.xml" files might have to be adapted
for certain databases like MS-SQL and Oracle, to use appropriate generation
strategies for order IDs. See the corresponding commented-out DAO definitions
in the context XML files.

A guide to step-by-step deployment, assuming JDK 1.5.x and Tomcat 6.x:
1. Run "mvn package" to generate the WAR file
2. Copy the generated "target/org.springframework.samples.jpetstore-1.0.0-SNAPSHOT.war" 
   to "<TOMCAT_HOME>/webapps/jpetstore.war"
3. Start HSQLDB via "db/hsqldb/server.bat" or "db/hsqldb/server.sh"
4. Start Tomcat (default port will be 8080)
5. Open "http://localhost:8080/jpetstore" in an Internet browser