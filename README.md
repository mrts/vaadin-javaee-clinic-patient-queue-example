# vaadin-javaee-clinic-patient-queue-example

An example clinic patient queue application using Vaadin 8, Java EE 7 and
Vaadin CDI add-on.

Demonstrates how to use Vaadin's server push and Java EE messaging APIs to send
messages to client browsers in real time. Uses JMS 2.0 API for sending
messages, message-driven beans for receiving them and a separate singleton
broadcaster to broadcast messages to Vaadin UIs (the broadcaster is recommended
by Vaadin as a bridge for connecting message-driven beans and Vaadin UIs that
have different lifetimes).

Also demonstrates how to authenticate and authorize users using JAAS and Vaadin
login form and view navigator.

Vaadin's server push uses a WebSocket connection by default which unfortunately
[is not compatible](https://github.com/vaadin/cdi/issues/88)
[with CDI](https://github.com/javaee/websocket-spec/issues/196), so for the time
being a mixed communication mode with WebSocket for server to client and
XMLHttpRequest for client to server must be used with
`@Push(transport = Transport.WEBSOCKET_XHR)`.

Tested with WildFly 10.1, but should work equally well with other Java EE 7
application servers.

The project consists of the following modules:

- parent project: common metadata and configuration
- `vaadin-javaee-clinic-patient-queue-example-ui`: main Vaadin application module
- `vaadin-javaee-clinic-patient-queue-example-backend`: backend module, contains server side code and dependencies.

## Building and running

You need Maven and Java 8 JDK to build and run the application.

Build the application WAR with `mvn package`, deploy it from
`target/vaadin-javaee-clinic-patient-queue-example-1.0-SNAPSHOT.war` to the application
server.

**Note that you need to run WildFly with full profile to enable JMS:**

    bin/standalone.{bat,sh} -c standalone-full.xml

## Overview

The project consists of three apps:

- Registration kiosk app where patients can register to doctor's appointments
- Doctor's office app for doctors to see when patients arrive and call them in
- Infoscreen where patients are notified when doctor calls them in.

The following screencast shows the whole workflow in action:

![screencast](doc/vaadin-javaee-clinic-patient-queue-screencast.gif)

## Testing

1. Open [doctor's office app](http://localhost:8080/vaadin-javaee-clinic-patient-queue-example-1.0-SNAPSHOT/doctors-office) in first browser window
2. Login as *user* with password *user* (see *Adding doctors* below), dr. Anu Võsu's office view opens
3. Open [registration kiosk](http://localhost:8080/vaadin-javaee-clinic-patient-queue-example-1.0-SNAPSHOT/registration-kiosk) in second browser window
4. Enter your name and press *Register* under dr. Anu Võsu's name to register to her appointment
5. The kiosk will output your call-in number
6. Open [infoscreen](http://localhost:8080/vaadin-javaee-clinic-patient-queue-example-1.0-SNAPSHOT/infoscreen) in third browser window
7. Switch to doctor's office app and observe that your registration is visible on the page
8. Select your registration and press *Call in*
9. Switch to infoscreen and observe that your call in number is visible in the list

A Vaadin TestBench test is also included, if you have a TestBench licence and
[Chrome WebDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads)
installed, you can run it with

    mvn test

## Adding doctors

WildFly has a security realm called *ApplicationRealm* configured by default.

To add a new user to *ApplicationRealm* execute the `add-user.{bat,sh}` script
within the `bin` folder of your WildFly installation and enter the requested
information. Choose *b) Application user* and add the user to the group
*users*.

Add user *user* with password *user* for the test to work.

## References

- [Server Push chapter in official Vaadin documentation](https://vaadin.com/docs/-/part/framework/advanced/advanced-push.html)

