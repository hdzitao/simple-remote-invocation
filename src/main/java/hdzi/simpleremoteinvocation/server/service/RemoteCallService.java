package hdzi.simpleremoteinvocation.server.service;

import com.yxz.qtcms.remotecall.bean.RemoteCallResult;
import com.yxz.qtcms.remotecall.bean.RemoteCallVO;

public interface RemoteCallService {
    RemoteCallResult call(RemoteCallVO vo);
}
