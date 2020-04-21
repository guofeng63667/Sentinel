package com.alibaba.csp.sentinel.dashboard.rule.consul;

/**
 * @Author guofeng@picooc.com
 * @Date 2020-04-21
 * @Version V1.0
 **/
public class ConsulConfigUtil {

    public static final String GROUP_ID = "SENTINEL_GROUP";

    public static final String FLOW_DATA_ID_POSTFIX = "flow_rule";
    public static final String DEGRADE_DATA_ID_POSTFIX = "degrade_rule";
    public static final String PARAM_FLOW_DATA_ID_POSTFIX = "param_rule";
    public static final String CLUSTER_MAP_DATA_ID_POSTFIX = "cluster_map";

    public static String getFlowKey(String app){
        return GROUP_ID+"/"+app+"/"+FLOW_DATA_ID_POSTFIX;
    }

    public static String getDegradeKey(String app){
        return GROUP_ID+"/"+app+"/"+DEGRADE_DATA_ID_POSTFIX;
    }
}
