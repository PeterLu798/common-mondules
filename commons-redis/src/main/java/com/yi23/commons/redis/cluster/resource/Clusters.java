package com.yi23.commons.redis.cluster.resource;

import java.util.List;

public class Clusters {

    /**
     * 应用id
     */
    private String id;
    /**
     * 最大的活动连接
     */
    private Integer maxtotal;
    /**
     * 最大的空闲连接
     */
    private Integer maxidle;
    /**
     * 最大的等待时间
     */
    private Long maxwait;
    /**
     * 过期时间
     */
    private int expiretime;
    /**
     * 默认超时时间
     */
    private Long defaulttimeout;
    /**
     * brrow时提前进行alidate操作
     */
    private boolean testonborrow;
    /**
     * return时提前进行alidate操作
     */
    private boolean testonreturn;
    /**
     * 集群节点
     */
    private List<Node> nodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMaxtotal() {
        return maxtotal;
    }

    public void setMaxtotal(Integer maxtotal) {
        this.maxtotal = maxtotal;
    }

    public Integer getMaxidle() {
        return maxidle;
    }

    public void setMaxidle(Integer maxidle) {
        this.maxidle = maxidle;
    }

    public Long getMaxwait() {
        return maxwait;
    }

    public void setMaxwait(Long maxwait) {
        this.maxwait = maxwait;
    }

    public Long getDefaulttimeout() {
        return defaulttimeout;
    }

    public void setDefaulttimeout(Long defaulttimeout) {
        this.defaulttimeout = defaulttimeout;
    }

    public boolean isTestonborrow() {
        return testonborrow;
    }

    public void setTestonborrow(boolean testonborrow) {
        this.testonborrow = testonborrow;
    }

    public boolean isTestonreturn() {
        return testonreturn;
    }

    public void setTestonreturn(boolean testonreturn) {
        this.testonreturn = testonreturn;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public int getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(int expiretime) {
        this.expiretime = expiretime;
    }
}
