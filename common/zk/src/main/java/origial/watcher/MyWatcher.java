package origial.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by MrDing
 * Date: 2018/11/10.
 * Time:14:53
 */
public class MyWatcher implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.EventType type = watchedEvent.getType();
        System.out.println(("MyWatcher收到信息:"+type.name()));
    }

}
