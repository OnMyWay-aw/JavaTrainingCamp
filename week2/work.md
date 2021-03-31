# 1.（选做）使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。

* 默认GC（默认并行）
```
java -XX:+PrintGCDetails  -Xloggc:garbage-collection-default.log GCLogAnalysis
```

*  串行GC
```
java -XX:+PrintGCDetails -Xloggc:garbage-collection-UseSerialGC.log -XX:+UseSerialGC GCLogAnalysis
```



*  并行（默认）

```
java -XX:+PrintGCDetails -Xloggc:garbage-collection-UseParallelGC.log -XX:+UseParallelGC GCLogAnalysis
```


*  CMS

```
java -XX:+PrintGCDetails -Xloggc:garbage-collection-UseConcMarkSweepGC.log -XX:+UseConcMarkSweepGC GCLogAnalysis
```


* G1
```
java -XX:+PrintGCDetails -Xloggc:garbage-collection-UseG1GC.log -XX:+UseG1GC GCLogAnalysis
```


2.（选做）使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

* 启动服务
```
java -Xloggc:garbage-collection-UseSerialGC.log -jar  gateway-server-0.0.1-SNAPSHOT.jar &
```

*  验证接口可以访问
```
curl http://localhost:8088/api/hello
hello world
```

*  压测一把
```
C02WK5WJG8WN:demo2 jiaruifeng$ wrk -t8 -c40 -d60s http://localhost:8088/api/hello > StressTest.log
Running 1m test @ http://localhost:8088/api/hello
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    16.26ms   56.96ms 647.00ms   93.73%
    Req/Sec     2.66k   793.11     5.18k    77.13%
  1198385 requests in 1.00m, 143.08MB read
Requests/sec:  19940.52
Transfer/sec:      2.38MB
```



3.（选做）如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。

4.（必做）根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

5.（选做）运行课上的例子，以及 Netty 的例子，分析相关现象。
根据多次自测的记过Socket01 Socket02 Socket03 压测的结果没有看出Socket02和Socket03一定比Socket01的性能好
个人猜测：
1.是否可能是因为本地单机，压测进程与服务进程本没有隔离导致 
2.Socket02中主线程创建并开启新的线程时，是否会影响要主线程的accept

* 压测Socket01
```
C02WK5WJG8WN:src jiaruifeng$ wrk -c40 -d30s http://localhost:8801/
Running 30s test @ http://localhost:8801/
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    22.57ms   15.03ms 235.68ms   80.31%
    Req/Sec   171.20    116.68   828.00     74.01%
  8924 requests in 30.69s, 1.73MB read
  Socket errors: connect 0, read 40529, write 226, timeout 6
Requests/sec:    290.82
Transfer/sec:     57.82KB

```

* 压测Socket02
```
C02WK5WJG8WN:src jiaruifeng$ wrk -c40 -d30s http://localhost:8802/
Running 30s test @ http://localhost:8802/
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.58ms   16.08ms 185.60ms   98.62%
    Req/Sec   120.03    138.72     1.02k    90.69%
  3627 requests in 30.01s, 729.02KB read
  Socket errors: connect 0, read 27926, write 64, timeout 0
Requests/sec:    120.87
Transfer/sec:     24.29KB

```

* 压测Socket03
```
C02WK5WJG8WN:src jiaruifeng$ wrk -c40 -d30s http://localhost:8803/
Running 30s test @ http://localhost:8803/
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    16.06ms   45.61ms 820.71ms   98.64%
    Req/Sec   144.74    104.06   737.00     80.27%
  4482 requests in 30.05s, 667.44KB read
  Socket errors: connect 0, read 21230, write 16, timeout 4
Requests/sec:    149.17
Transfer/sec:     22.21KB

```

6.（必做）写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到 GitHub