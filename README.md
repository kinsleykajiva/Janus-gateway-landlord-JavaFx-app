# Janus-Gateway Landlord JavaFX Client Application.

<figure>
<img src="wall.png" alt="App Icon."/>

</figure>

This is a client application to [Janus WebRTC Server](https://github.com/meetecho/janus-gateway). 
This client app is also a client to  [Janus-gateway-landlord-Web-app](https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app). 


## Features include
- [Admin Monitor API requests](https://janus.conf.meetecho.com/docs/admin.html) can get Sessions.
- [LandLord-Web-App](https://github.com/kinsleykajiva/Janus-gateway-landlord-Web-app ) can access APIs [pending] 
- Access Config to Sever Dynamically [pending] 

### Please note that this application is still in development mode,hence the code is still abit messy .

## Build
Environment: JDK  17+ , yes you  can try to use JDK 10 or higher and openFX SDK.

I highly recommend you to use IntelliJ Idea  - https://www.jetbrains.com/idea/   to run this application and Scene Builder from https://gluonhq.com/products/scene-builder/


How to Build/Run the app :
- Clone the repository and resolve the project dependencies .
- run ``mvn clean install ``
- then run ``javafx:run -f pom.xml ``

<hr>
To package the application based on your system configuration please review https://github.com/wiverson/maven-jpackage-template#installation

Also do note for my reasons I have always opted to build a GraalVM Native Image build . Native images have their own advantages and limitations , 
for more please review Gluonhq GraalVM version ,sources: 
- https://gluonhq.com/create-native-javafx-applications-using-graalvm-22-builds-from-gluon/  
- https://github.com/gluonhq/graal

<hr>


<hr>
## Screenshots

<figure>
<img src="screenshots/sessions-screen.png" alt="Expanded Session Screen" />
<figure-caption>Expanded Session Screen</figure-caption>
</figure>


<figure>
<img src="screenshots/sessions-screenshot-2.png" alt="Collapsed Session Screen."/>
<figure-caption>Collapsed Session Screen.</figure-caption>
</figure>

<hr>

### Contributions are welcome , feel free to make a pull request.
<hr>
### Credit to <br>

 [Will Iverson](https://github.com/wiverson)  for creating a good project framework for javaFX projects.  Open source - https://github.com/wiverson/maven-jpackage-template  . Please 
review this documentation to also build  this project and resolve some issues if your facing any .

<hr>

### ToDo <br>



