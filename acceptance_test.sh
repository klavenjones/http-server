set -ex

gradle checkStyleMain
gradle checkStyleTest
gradle test

gradle build

java -jar build/libs/http-server-1.0-SNAPSHOT.jar 5000 &

cd http_server_spec

bundle install

rake test:implemented