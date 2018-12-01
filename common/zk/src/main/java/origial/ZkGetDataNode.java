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
import java.util.concurrent.CountDownLatch;

/**
 * Created by MrDing
 * Date: 2018/12/1.
 * Time:10:19
 */
@Slf4j
@Getter
@Setter
public class ZkGetDataNode implements Watcher {


    private ZooKeeper zooKeeper = null;


    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZkGetDataNode(String path, int timeOut) {
        try {
            zooKeeper = new ZooKeeper(path, timeOut, this);
        } catch (IOException e) {
            log.error("zookeeper连接失败...", e);
        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZkGetDataNode zkGetDataNode = new ZkGetDataNode(Constants.zkStr, Constants.timeout);
        Stat stat = new Stat();
        byte[] data = zkGetDataNode.getZooKeeper().getData("/dingyabin/test", true, stat);
        if (data != null) {
            String nodeData = new String(data);
            System.out.println("当前值:" + nodeData);
        }
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
                    Stat stat = new Stat();
                    byte[] data = zooKeeper.getData("/dingyabin/test", true, stat);
                    log.info("NodeDataChanged happened,当前path={},data={},version={}", event.getPath(),new String(data), stat.getVersion());
                    countDownLatch.countDown();
                    break;
                case NodeDeleted:
                    log.info("data delete!,path={}", event.getPath());
                    break;
                case NodeChildrenChanged:
                    break;
                case NodeCreated:
                    break;
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
