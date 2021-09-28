package com.kevingates.uploadimage.controllers;

import com.kevingates.uploadimage.mappers.DevicesMapper;
import com.kevingates.uploadimage.models.Devices;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DevicesController {
    static String resource = "mybatis-config.xml";

    public static void main(String[] args) throws IOException {
        index();
        //updateDevices();
        //delete();
        //insertDevice();
    }

    public static void index() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            DevicesMapper mapper = session.getMapper(DevicesMapper.class);

            List<Devices> devices = mapper.selectDevicesList(new Devices());

            for(Devices device:devices) {
                System.out.println("data:"+device.getId()+","+device.getAddress());
            }

        } catch (Exception e) {
            System.out.println("error:"+e.getMessage());
        }
    }

    public static void updateDevice() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            DevicesMapper mapper = session.getMapper(DevicesMapper.class);

            Devices device = new Devices();
            device.setId(1L);
            device.setAddress("new york city");
            device.setCommunityName("mountaion view");

            int count = mapper.updateDevices(device);
            String msg = "nothing changed.";
            if(count>0) msg = "It's been updated count: "+ count;
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println("error:  "+e.getMessage());
        }
    }

    public static void insertDevice() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            DevicesMapper mapper = session.getMapper(DevicesMapper.class);

            Devices device = new Devices();

            device.setAddress("new york city");
            device.setCommunityName("mountaion view");

            int count = mapper.insertDevices(device);
            String msg = "nothing saved.";
            if(count>0) msg = "It's been saved count: "+ count;
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println("error:  "+e.getMessage());
        }
    }
    
    public static void delete() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            DevicesMapper mapper = session.getMapper(DevicesMapper.class);

            int count = mapper.deleteDevicesById(1L);
            String msg = "nothing deleted.";
            if(count>0) msg = "It's been deleted count: "+ count;
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println("error:  "+e.getMessage());
        }
    }
}