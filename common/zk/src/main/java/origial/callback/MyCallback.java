package origial.callback;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;

/**
 * Created by MrDing
 * Date: 2018/11/10.
 * Time:15:52
 */

public class MyCallback implements AsyncCallback.StringCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        //成功
        if (rc == KeeperException.Code.OK.intValue()) {
            System.out.println("MyCallback-创建成功,path=" + path + " name=" + name + " ctx=" + ctx.toString());
        } else {
            //失败
            System.out.println("MyCallback-创建失败");
        }
    }


}
