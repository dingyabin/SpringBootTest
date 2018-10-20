package origial;

import org.apache.zookeeper.ZooKeeper;
import origial.constant.Constants;
import origial.watcher.MyWatcher;

import java.io.IOException;

/**
 * Created by MrDing
 * Date: 2018/11/10.
 * Time:14:53
 */
public class ZKconnect {


    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(Constants.zkStr, Constants.timeout, new MyWatcher());
        System.out.println("zk开始连接,state:.........."+zooKeeper.getState().name());
        Thread.sleep(1000);
        System.out.println("经过1s，zk连接state:.........."+zooKeeper.getState().name());

        System.out.println("开始重连..............zk2");

        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();
        int sessionTimeout = zooKeeper.getSessionTimeout();

        ZooKeeper zooKeeper2 = new ZooKeeper(Constants.zkStr, sessionTimeout,new MyWatcher(),sessionId,sessionPasswd);
        System.out.println("zk2开始连接,state:.........."+zooKeeper2.getState().name());
        Thread.sleep(1000);
        System.out.println("经过1s，zk2连接state:.........."+zooKeeper2.getState().name());


    }




}
