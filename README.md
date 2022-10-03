# HTTP Server

A HTTP Server built with Java.

---

## Instructions

---

### Installation

Clone this repo from the terminal.

```
git clone https://github.com/klavenjones/http-server.git   
```

Navigate to the root directory of the project:

```
cd http-server
```

Build Project:

```
./gradlew build
```


### Linting

Run the linter for the source code

```
./gradlew checkStyleMain
```

Run the linter for the test suite

```
./gradlew checkStyleMain
```


### Testing

```
./gradlew test
```

### Deployment

Before running the script add environment variables

```
  export HTTP_SERVER_LIB_PATH=<full path to build package (build/libs/http-server.jar)>
  export WEB_ASSET_PATH=<full path to web assets (web folder with images)>
  export RUN_SERVER_SCRIPT_PATH=<full path to server script that runs the server remotely>
  export RUN_SERVER_SCRIPT_PATH=<full path to server script that runs the server remotely>
  export USER=<ssh user name>
  export HOSTNAME=<ssh hostname>
```

Once you added these values to your terminal please run the following command to deploy

```
bash scripts/acceptance_test.sh
```

***NOTE***

Please be advised your machine must be connected by to your remote server via SSH Key. Currently, the scripts does not support passphrase ssh connection, please modify the script file to your liking
to add this support.


### Stretch goals

Add automated scripting with ssh passphrase support.


[//]: # (## Usage)

[//]: # (___)

[//]: # ()
[//]: # ()
[//]: # (### Running the program)

[//]: # ()
[//]: # (Run the server)

[//]: # ()
[//]: # (```)

[//]: # (./gradle runServer)

[//]: # (```)

[//]: # ()
[//]: # (Run the server)

[//]: # ()
[//]: # (```)

[//]: # ( ./gradle runClient)

[//]: # (```)

[//]: # ()
[//]: # (The default port for the server and client is 8080, and the default hostname for the client is localhost. If you wish to override hostname and port, run the following commands:)

[//]: # ()
[//]: # (```)

[//]: # (./gradlew runServer --args <port-number>)

[//]: # (```)

[//]: # ()
[//]: # (```)

[//]: # (./gradlew runClient --args="<host-name> <port-number>")

[//]: # (```)