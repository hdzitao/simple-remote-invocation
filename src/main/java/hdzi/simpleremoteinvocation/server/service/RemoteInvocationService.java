package hdzi.simpleremoteinvocation.server.service;


import hdzi.simpleremoteinvocation.server.bean.RIServerResult;
import hdzi.simpleremoteinvocation.server.bean.RIServerVO;

public interface RemoteInvocationService {
    RIServerResult call(RIServerVO vo);
}
