mvn -f %~dp0\pom.xml ^
  clean test-compile ^
  perforator:testng ^
  -Dsuite.webDriverMode=cloud ^
  -Dsuite.concurrency=250 ^
  -Dsuite.duration=12m ^
  -Dsuite.rampUp=10m ^
  -Dsuite.rampDown=1m ^
  %*