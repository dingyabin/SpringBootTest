package origial;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import origial.constant.Constants;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by MrDing
 * Date: 2018/12/1.
 * Time:10:19
 */
@Slf4j
@Getter
@Setter
public class ZkGetChildrenDataNode implements Watcher {


    private ZooKeeper zooKeeper = null;


    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZkGetChildrenDataNode(String path, int timeOut) {
        try {
            zooKeeper = new ZooKeeper(path, timeOut, this);
        } catch (IOException e) {
            log.error("zookeeper连接失败...", e);
        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZkGetChildrenDataNode zkGetDataNode = new ZkGetChildrenDataNode(Constants.zkStr, Constants.timeout);
        Stat stat = new Stat();
        List<String> children = zkGetDataNode.getZooKeeper().getChildren("/dingyabin", true, stat);
        System.out.println("子节点:" + String.join(",", children));
        countDownLatch.await();
    }


    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();
        try {
            switch (type) {
                case None:
                    log.info("nothing happened!");
                    break;
                case NodeDataChanged:
                    break;
                case NodeDeleted:
                    log.info("data delete!,path={}", event.getPath());
                    break;
                case NodeChildrenChanged:
                    Stat stat = new Stat();
                    List<String> children = zooKeeper.getChildren("/dingyabin", true, stat);
                    System.out.println("父节点:" + event.getPath());
                    System.out.println("子节点:" + String.join(",",children));
                    countDownLatch.countDown();
                    break;
                case NodeCreated:
                    break;
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
