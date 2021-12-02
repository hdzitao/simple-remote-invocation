package hdzi.simpleremoteinvocation.server.controller;

import com.alibaba.fastjson.JSON;
import hdzi.simpleremoteinvocation.server.bean.RIServerResult;
import hdzi.simpleremoteinvocation.server.bean.RIServerVO;
import hdzi.simpleremoteinvocation.server.service.RemoteInvocationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("remote-invocation")
public class RemoteInvocationController {
    @Autowired
    private RemoteInvocationService service;

    @PostMapping("invoke")
    public RIServerResult invoke(@RequestBody String json) { // 不用默认的jackson解析,直接接收string交fastjson解析
        log.info("接收远程调用 {}", json);
        RIServerVO vo = JSON.parseObject(json, RIServerVO.class);
        RIServerResult remoteCallResult;
        try {
            remoteCallResult = service.invoke(vo);
        } catch (Exception e) {
            log.info("远程调用异常", e);
            remoteCallResult = RIServerResult.FAIL;
        }
        log.info("接收远程调用返回 {} {}", json, JSON.toJSONString(remoteCallResult));

        return remoteCallResult;
    }
}
