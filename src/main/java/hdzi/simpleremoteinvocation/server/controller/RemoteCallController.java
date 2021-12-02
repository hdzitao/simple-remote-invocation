package hdzi.simpleremoteinvocation.server.controller;

import com.alibaba.fastjson.JSON;
import com.yxz.qtcms.remotecall.bean.RemoteCallResult;
import com.yxz.qtcms.remotecall.bean.RemoteCallVO;
import com.yxz.qtcms.remotecall.service.RemoteCallService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("remote-call")
public class RemoteCallController {
    @Autowired
    private RemoteCallService service;

    @PostMapping("call")
    public RemoteCallResult call(@RequestBody RemoteCallVO vo) {
        log.info("接收远程调用 {}", JSON.toJSONString(vo));
        RemoteCallResult remoteCallResult;
        try {
            remoteCallResult = service.call(vo);
        } catch (Exception e) {
            log.info("远程调用异常", e);
            remoteCallResult = RemoteCallResult.FAIL;
        }
        log.info("接收远程调用返回 {} {}", JSON.toJSONString(vo), JSON.toJSONString(remoteCallResult));

        return remoteCallResult;
    }
}
