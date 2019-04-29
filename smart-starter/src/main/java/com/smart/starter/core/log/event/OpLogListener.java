package com.smart.starter.core.log.event;
import com.smart.starter.core.log.OpLogParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 日志事件监听
 * @author guwenchang
 * @date 2019-04-29 14:12
 */
@Slf4j
@AllArgsConstructor
public class OpLogListener {

	@Async
	@Order
	@EventListener(OpLogEvent.class)
	public void saveSysLog(OpLogEvent event) {
		OpLogParam opLogParam = event.getOpLogParam();
		System.out.println(opLogParam);
	}
}
