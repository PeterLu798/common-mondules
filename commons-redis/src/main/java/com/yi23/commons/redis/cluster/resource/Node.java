package com.yi23.commons.redis.cluster.resource;

import java.util.List;

public class Node {

    /**
     * 节点id
     */
    private String id;
    /**
     * 节点host和端口
     */
    private List<String> hostsAndPorts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getHostsAndPorts() {
        return hostsAndPorts;
    }

    public void setHostsAndPorts(List<String> hostsAndPorts) {
        this.hostsAndPorts = hostsAndPorts;
    }
}
