package com.kevingates.uploadimage.models;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * devices对象 devices
 *
 * @author kevingates
 * @date 2021-09-10
 */
public class Devices
{
    private static final long serialVersionUID = 1L;

    /** 点位序号 */
    private Long id;

    /** 设备识别码 */
    //@Excel(name = "设备识别码")
    private String deviceIdentificationCode;

    /** 安装地址 */
    //@Excel(name = "安装地址")
    private String address;

    /** 当前液位 */
    //@Excel(name = "当前液位")
    private BigDecimal liquidLevel;

    /** 小区名称 */
    //@Excel(name = "小区名称")
    private String communityName;

    /** 所属街道   */
    //@Excel(name = "所属街道  ")
    private String street;

    /** 排水系统 */
    //@Excel(name = "排水系统")
    private String drainageSystem;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 单位序号 */
    //@Excel(name = "单位序号")
    private Long organizationId;

    /** 录入单位名称 */
    //@Excel(name = "录入单位名称")
    private String organizationName;

    /** 地图定位地址（高德） */
    //@Excel(name = "地图定位地址", readConverterExp = "高=德")
    private String mapAddress;

    /** liquid alarm level */
    //@Excel(name = "liquid alarm level")
    private BigDecimal liquidAlarmLevel;

    /** liquid status */
    //@Excel(name = "liquid status")
    private String liquidStatus;

    /** ctwing device id */
    //@Excel(name = "ctwing device id")
    private String ctwingDeviceId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    private String orderBy;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setDeviceIdentificationCode(String deviceIdentificationCode)
    {
        this.deviceIdentificationCode = deviceIdentificationCode;
    }

    public String getDeviceIdentificationCode()
    {
        return deviceIdentificationCode;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setLiquidLevel(BigDecimal liquidLevel)
    {
        this.liquidLevel = liquidLevel;
    }

    public BigDecimal getLiquidLevel()
    {
        return liquidLevel;
    }
    public void setCommunityName(String communityName)
    {
        this.communityName = communityName;
    }

    public String getCommunityName()
    {
        return communityName;
    }
    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getStreet()
    {
        return street;
    }
    public void setDrainageSystem(String drainageSystem)
    {
        this.drainageSystem = drainageSystem;
    }

    public String getDrainageSystem()
    {
        return drainageSystem;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setOrganizationId(Long organizationId)
    {
        this.organizationId = organizationId;
    }

    public Long getOrganizationId()
    {
        return organizationId;
    }
    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }

    public String getOrganizationName()
    {
        return organizationName;
    }
    public void setMapAddress(String mapAddress)
    {
        this.mapAddress = mapAddress;
    }

    public String getMapAddress()
    {
        return mapAddress;
    }

    public void setLiquidAlarmLevel(BigDecimal liquidAlarmLevel)
    {
        this.liquidAlarmLevel = liquidAlarmLevel;
    }
    public BigDecimal getLiquidAlarmLevel()
    {
        return liquidAlarmLevel;
    }

    public void setLiquidStatus(String liquidStatus)
    {
        this.liquidStatus = liquidStatus;
    }

    public String getLiquidStatus()
    {
        return liquidStatus;
    }
    public void setCtwingDeviceId(String ctwingDeviceId)
    {
        this.ctwingDeviceId = ctwingDeviceId;
    }

    public String getCtwingDeviceId()
    {
        return ctwingDeviceId;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


}

