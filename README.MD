# Hi-Framework with tomcat-7 embedded
This project shows how to run a Hi-Framework app with tomcat7 embedded.
Tomcat is a Java EE servlet container. Running you Hi-Framework application with tomcat embedded will allow you to test your application faster, you just got to open your terminal, navigate to your maven project directory and start
 your embedded tomcat server.


# Running this project

## Start tomcat
```bash
mvn clean compile tomcat7:run
```

## Open the browser and browse it
```xml
http://localhost:9093/app/pages/example
```

# Setting up your own project
This guide explains how to configure you Hi-Framework based application to run with tomcat7 embedded.

## web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">


    <servlet>
        <servlet-name>HiServlet</servlet-name>
        <servlet-class>mz.co.hi.web.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>HiServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>

    <listener>
        <listener-class>org.jboss.weld.environment.servlet.Listener</listener-class>
    </listener>
    
    <context-param>
        <param-name>DISABLE_SERVLET_CONTEXT_SCANNING</param-name>
        <param-value>True</param-value>
    </context-param>
    
</web-app>
```

## hi.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<app xmlns="http://hi.co.mz/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://hi.co.mz/XMLSchema
      https://talk-code.github.io/releases/ns/hi-1.0.xsd">

    <deployment-mode>DEVELOPMENT</deployment-mode>
    <default-lang>en</default-lang>

    <web>

        <welcome-url>pages/example</welcome-url>

        <templates>
            <template>index</template>
        </templates>

    </web>

</app>

```

## Add the tomcat maven plugin
```xml
 <build>
        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <!--This is the port tomcat will be running-->
                    <port>9093</port>
                    <!--This will be the context path of the app-->
                    <path>/app</path>
                    <!--Here we make sure that the compiled classes under target/classes will be added to classpath-->
                    <additionalClassesDirs>${project.build.directory}/${project.build.finalName}/classes</additionalClassesDirs>
                </configuration>

            </plugin>
        </plugins>
 </build>
```

## Add the maven dependencies
```xml

 <dependencies>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>javax.enterprise</groupId>
            <artifactId>cdi-api</artifactId>
            <version>1.2</version>
            <scope>compile</scope>
            <optional>false</optional>
        </dependency>
        
        <dependency>
            <groupId>org.jboss.weld.servlet</groupId>
            <artifactId>weld-servlet</artifactId>
            <version>2.4.2.Final</version>
        </dependency>


        <dependency>
            <groupId>org.emerjoin</groupId>
            <artifactId>Hi-Framework</artifactId>
            <version>2.3.0-beta-SNAPSHOT</version>
        </dependency>

    </dependencies>

```

## Add the Emerjoin's maven repository
Hi-Framework is currently living in a maven-repository hosted by github
```xml
<repositories>
        <repository>
            <id>Emerjoin</id>
            <name>maven-repo</name>
            <url>https://github.com/Emerjoin/maven-repo/raw/master</url>
        </repository>
</repositories>
```

