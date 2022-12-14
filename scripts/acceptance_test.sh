
echo "Kill All Proccesses First...."
sleep .5
if lsof -t -i :5000
  then
    kill $(lsof -t -i :5000)
    echo "If there server is running kill processes...."
    sleep .5
fi

echo "Run Linting On Main and Tests...."
sleep .5
gradle checkStyleMain
gradle checkStyleTest


echo "Run tests for projects...."
sleep .5
gradle test


echo "Building Project....."
sleep .5
gradle build


echo "Running Project Locally...."
java -jar $HTTP_SERVER_LIB_PATH 5000 &
sleep .5

echo "Running Acceptance Tests....."
cd http-server-spec
bundle install
rake test:implemented

echo "Removing Directory From Remote Server..."
sleep .5
ssh $USER@$HOSTNAME rm -rf  http-server.jar

echo "Deploying new build to Remote server...."

scp -r ~/.ssh/id_ed25519 $WEB_ASSET_PATH $USER@$HOSTNAME:~
scp -r ~/.ssh/id_ed25519 $HTTP_SERVER_LIB_PATH $USER@$HOSTNAME:~


echo "Starting server remotely.."
ssh $USER@$HOSTNAME "bash -s" -- <  $RUN_SERVER_SCRIPT_PATH &









