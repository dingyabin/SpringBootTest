package origial;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import origial.callback.MyCallback;
import origial.constant.Constants;
import origial.watcher.MyWatcher;

import java.io.IOException;

/**
 * Created by MrDing
 * Date: 2018/11/10.
 * Time:15:51
 */
public class ZKnodeOperation {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ZooKeeper zooKeeper = new ZooKeeper(Constants.zkStr, Constants.timeout, new MyWatcher());

        //同步创建
        //zooKeeper.create("/dingyabin/java-syn", "Java".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        //异步创建
        //zooKeeper.create("/dingyabin/java-asyn", "Java".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new MyCallback(), "success");

        //同步修改
        Stat stat = zooKeeper.setData("/dingyabin/test", "set".getBytes(), 1);
        System.out.println("修改后的version:"+stat.getVersion());

       //删除节点
        zooKeeper.delete("/dingyabin/test", 0, (rc, path, ctx) -> System.out.println("path=" + path + "cx=" + ctx),"success");

        Thread.sleep(900000);


    }


}
