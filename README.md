#基于Zookeeper的分布式锁

##说明

>在有些场景下面我们必须用到分布式锁的服务，比如在线医生，一个医生在某个时间段内只能给一个病人看病，但在我们的分布式以及集群上面，都是多进程的，这个时候需要对这个医生的资源进行加锁。


##程序说明

###Zookeeper安装

```
 从官方网站上面下载安装包：zookeeper-3.4.8.tar.gz
 解压的到zookeeper-3.4.8
 
 到$ZOOKEEPER_HOME/conf下面，备份原来的zoo_sample.cfg文件，把zoo_sample.cfg重命名为zoo.cfg
 
 Demo配置文件：
 
 tickTime=2000
 initLimit=10
 syncLimit=5
 dataDir=/Users/sunny/software/zookeeper-data
 dataLogDir=/Users/sunny/software/zookeeper-logs
 clientPort=2181
 server.1=zookeeper:2888:3888
 
 每个参数的说明就不在这里阐述了，可以Google或者百度一下。
 
```
###流程说明

1.创建DistributedLock lock对象

2.检查根目录和需要锁的目录是否存在，如果不存在则在zk中创建

3.因为Zookeeper可以实现对每个目录名称自增的特性，所以可以创建自己的节点名称，创建方式CreateMode.EPHEMERAL_SEQUENTIAL

4.然后获取该lockNode目录中的所有的字目录

5.对所有的字目录进行排序

6.然后获取比自己小的目录列表，选取最后一个进行watch注册，（当最后一个释放锁的时候会自动通知该节点的）

7.如果没有比自己小的节点，那么自己本身就可以获取该资源的锁

8.完成后，释放锁
