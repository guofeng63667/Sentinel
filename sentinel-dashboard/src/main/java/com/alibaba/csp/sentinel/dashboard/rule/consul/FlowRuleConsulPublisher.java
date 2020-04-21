package com.alibaba.csp.sentinel.dashboard.rule.consul;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.ecwid.consul.v1.ConsulClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author guofeng@picooc.com
 * @Date 2020-04-21
 * @Version V1.0
 **/
@Component("flowRuleConsulPublisher")
public class FlowRuleConsulPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {

    @Autowired
    private ConsulClient consulClient;

    @Autowired
    private Converter<List<FlowRuleEntity>, String> converter;

    @Override
    public void publish(String app, List<FlowRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        consulClient.setKVValue(ConsulConfigUtil.getFlowKey(app),converter.convert(rules));
    }
}
