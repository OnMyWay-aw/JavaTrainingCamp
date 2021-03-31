#cd /Users/jiaruifeng/github/JavaTrainingCamp/week2/demo3/src
cd src
javac  pers/fffjia/socket/*.java

java pers/fffjia/socket/Socket01 &

java pers/fffjia/socket/Socket02 &

java pers/fffjia/socket/Socket03 &

sleep 5s
wrk -t8 -c40 -d30s http://localhost:8801/ > wrk.1.log &
wrk -t8 -c40 -d30s http://localhost:8802/ > wrk.2.log &
wrk -t8 -c40 -d30s http://localhost:8803/ > wrk.3.log &

wrk -t8 -c40 -d30s http://localhost:8801/
wrk -t8 -c40 -d30s http://localhost:8802/
wrk -t8 -c40 -d30s http://localhost:8803/


