package hdzi.simpleremoteinvocation.server.service;

import com.yxz.qtcms.elasticsearchcomponent.live.bean.LivePollingPO;

import java.util.List;

public interface RemoteCallTestService {
    Integer test(List<LivePollingPO> clazz);
}
