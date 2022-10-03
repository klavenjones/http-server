function runServerInBox {
  if lsof -t -i :5000
  then
    kill $(lsof -t -i :5000)
    echo "Stopping Processes...."
    sleep .5
  fi
  echo "Running server in the cloud, Press CTRL-C to exit. Server will continue to run in the background"
  nohup java -jar http-server-1.0-SNAPSHOT.jar 5000 &
}


runServerInBox

