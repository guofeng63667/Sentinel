package com.alibaba.csp.sentinel.dashboard.rule.consul;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author guofeng@picooc.com
 * @Date 2020-04-21
 * @Version V1.0
 **/
@Component("flowRuleConsulProvider")
public class FlowRuleConsulProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {


    @Autowired
    private ConsulClient consulClient;

    @Autowired
    private Converter<String, List<FlowRuleEntity>> converter;

    @Override
    public List<FlowRuleEntity> getRules(String appName) throws Exception {
        Response<GetValue> response = getValueImmediately(ConsulConfigUtil.getFlowKey(appName));
        if (response != null) {
            GetValue value = response.getValue();
            return value != null ? converter.convert(value.getDecodedValue()) : null;
        }
        return new ArrayList<>();
    }

    /**
     * Get data from Consul immediately.
     *
     * @param key data key in Consul
     * @return the value associated to the key, or null if error occurs
     */
    private Response<GetValue> getValueImmediately(String key) {
        return getValue(key, -1, -1);
    }

    /**
     * Get data from Consul (blocking).
     *
     * @param key      data key in Consul
     * @param index    the index of data in Consul.
     * @param waitTime time(second) for waiting get updated value.
     * @return the value associated to the key, or null if error occurs
     */
    private Response<GetValue> getValue(String key, long index, long waitTime) {
        try {
            return consulClient.getKVValue(key, new QueryParams(waitTime, index));
        } catch (Throwable t) {
            RecordLog.warn("[ConsulDataSource] Failed to get value for key: " + key, t);
        }
        return null;
    }
}
