package com.alibaba.csp.sentinel.dashboard.datacenter;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author guofeng@picooc.com
 * @Date 2020-04-21
 * @Version V1.0
 **/
@Configuration
public class ConsulConfiguration {

    @Bean
    public ConsulProperties consulProperties() {
        return new ConsulProperties();
    }

    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }
    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    @Bean
    public ConsulClient consulClient(ConsulProperties consulProperties) {
        final int agentPort = consulProperties.getPort();
        final String agentHost = !StringUtils.isEmpty(consulProperties.getScheme())
                ? consulProperties.getScheme() + "://" + consulProperties.getHost()
                : consulProperties.getHost();

        if (consulProperties.getTls() != null) {
            ConsulProperties.TLSConfig tls = consulProperties.getTls();
            TLSConfig tlsConfig = new TLSConfig(tls.getKeyStoreInstanceType(),
                    tls.getCertificatePath(), tls.getCertificatePassword(),
                    tls.getKeyStorePath(), tls.getKeyStorePassword());
            return new ConsulClient(agentHost, agentPort, tlsConfig);
        }
        return new ConsulClient(agentHost, agentPort);
    }

}
