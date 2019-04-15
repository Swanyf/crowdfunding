package com.swan.crowdfunding.entity;

import java.util.ArrayList;
import java.util.List;

public class PermissionDO {
    private Integer id;

    private Integer pid;

    private String name;

    private String icon;

    private String url;

    // 将树形图的每条数据存放到children中
    private List<PermissionDO> children = new ArrayList<>();

    public List<PermissionDO> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDO> children) {
        this.children = children;
    }

    // 设置属性图节点默认是打开
    private Boolean open = true;

    // 设置选中的状态
    private Boolean checked;

   
    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public PermissionDO() {
    }

    public PermissionDO(Integer id, Integer pid, String name, String icon, String url) {
        super();
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.icon = icon;
        this.url = url;
    }

    @Override
    public String toString() {
        return "PermissionDO [id=" + id + ", pid=" + pid + ", name=" + name + ", icon=" + icon + ", url=" + url + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    
    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}