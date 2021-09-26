# 第二周JVM核心技术第4题

*  根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

#### 数据
 
###### SerialGC 
| GC| 堆大小| young gc时间（单位ms）  | full gc时间（单位ms） |
|---|---|---|---|---|
| UseSerialGC|-Xms128m -Xmx128m| 2~3  | 2~10 |
| UseSerialGC|-Xms256m -Xmx256m | 5~10| 4~20 |
| UseSerialGC|-Xms512m -Xmx512m | 8~16| 15~30 |
| UseSerialGC|-Xms1g -Xmx1g  | 5~30 | 20 |
| UseSerialGC|-Xms2g -Xmx2g   | 28~85 | 120 |
| UseSerialGC|-Xms4g -Xmx4g   | 30~120 |  |

###### ParallelGC 
| GC| 堆大小| young gc时间（单位ms）  | full gc时间（单位ms） |
|---|---|---|---|---|
| UseParallelGC|-Xms128m -Xmx128m| 1~3  | 2~8 |
| UseParallelGC|-Xms256m -Xmx256m | 2~6 | 3~15 |
| UseParallelGC|-Xms512m -Xmx512m | 3~10| 20~30 |
| UseParallelGC|-Xms1g -Xmx1g  | 5~10 | 20~30 |
| UseParallelGC|-Xms2g -Xmx2g   | 15~30|  50   |
| UseParallelGC|-Xms4g -Xmx4g   | 20~80|    |

###### CMS
| GC| 堆大小| young gc时间（单位ms）  | full gc时间（单位ms） |
|---|---|---|---|---|
| UseConcMarkSweepGC|-Xms128m -Xmx128m| 3~8  | 2~8 |
| UseConcMarkSweepGC|-Xms256m -Xmx256m | 5~15 | 15左右 |
| UseConcMarkSweepGC|-Xms512m -Xmx512m | 10~20|  |
| UseConcMarkSweepGC|-Xms1g -Xmx1g| 15~30 |  |
| UseConcMarkSweepGC|-Xms2g -Xmx2g|20~100 |    |
| UseConcMarkSweepGC|-Xms4g -Xmx4g|20~100 |    |

###### G1
| GC| 堆大小| young gc时间（单位ms）  | full gc时间（单位ms） |
|---|---|---|---|---|
| UseG1GC|-Xms128m -Xmx128m| 0.1~1  | 0.1~1  |
| UseG1GC|-Xms256m -Xmx256m | 0.1~1 | 0.1~1 |
| UseG1GC|-Xms512m -Xmx512m | 0.1~2 | 10~15 |
| UseG1GC|-Xms1g -Xmx1g| 1~10 |  |
| UseG1GC|-Xms2g -Xmx2g| 5~15 |    |
| UseG1GC|-Xms2g -Xmx2g| 5~50 |    |

**上述数据未做严格多次取平均的操作，只是大概看了下时间范围**

#### 总结   
* 串行GC：SerialGC
    * 现象：随着堆内存增加，gc 时间增加，增加较快；
* 并行GC：ParallelGC
    * 现象：随着堆内存增加，gc 时间增加，但是增长速度比串行GC慢；
* CMS GC
    * 现象：CMS gc时间对比并行gc，从数值上看无优势；
* G1 GC
    * 现象：G1 gc时间优于其他GC；大内存情况下，比其他GC表现好很多；


    

