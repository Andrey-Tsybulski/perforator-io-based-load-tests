mvn -f %~dp0\pom.xml ^
  clean test-compile ^
  perforator:testng ^
  -Dsuite.webDriverMode=local ^
  -Dsuite.concurrency=1 ^
  -Dsuite.duration=1m ^
  -Dsuite.rampUp=0m ^
  -Dsuite.rampDown=0m ^
  %*
