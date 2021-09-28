package com.kevingates.uploadimage.mappers;

import com.kevingates.uploadimage.models.Devices;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
/**
 * devicesMapper接口
 * 
 * @author kevingates
 * @date 2021-09-01
 */
public interface DevicesMapper 
{
    /**
     * 查询devices
     * 
     * @param id devices主键
     * @return devices
     */
    public Devices selectDevicesById(Long id);

    public Devices selectDevicesByDeviceIdentificationCode(String deviceIdentificationCode);

    /**
     * 查询devices列表
     * 
     * @param devices devices
     * @return devices集合
     */
    public List<Devices> selectDevicesList(Devices devices);

    public List<String> selectDeviceStreets();
    /**
     * 新增devices
     * 
     * @param devices devices
     * @return 结果
     */
    public int insertDevices(Devices devices);

    /**
     * 修改devices
     * 
     * @param devices devices
     * @return 结果
     */
    public int updateDevices(Devices devices);

    public int updateDevice();

    /**
     * 删除devices
     * 
     * @param id devices主键
     * @return 结果
     */
    public int deleteDevicesById(Long id);

    /**
     * 批量删除devices
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevicesByIds(String[] ids);

    @Select("SELECT * FROM devices WHERE id = 1")
    public Devices selectDeviceTest();

    @Select("SELECT * FROM devices limit 2")
    public List<Devices> selectDeviceTestAll();
}
