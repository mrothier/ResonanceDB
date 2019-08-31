@echo off

echo API Query Tool Starting... Hosting HTTP Endpoint on Port 8081
echo If no errors are shown, web interface will now function fully.
echo Please leave this window open while using the web interface,
echo to allow the interface to make requests to the Last.FM API

java -jar javaBackend/Simple_API_Query_Endpoint.jar

PAUSE
