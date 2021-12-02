package hdzi.simpleremoteinvocation.server.service.impl;

import com.yxz.qtcms.elasticsearchcomponent.live.bean.LivePollingPO;
import com.yxz.qtcms.remotecall.service.RemoteCallTestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("test1")
public class RemoteCallTestServiceImpl1 implements RemoteCallTestService {
    @Override
    public Integer test(List<LivePollingPO> clazz) {
        return 1;
    }
}
