package com.kevingates.uploadimage;

import com.kevingates.uploadimage.mappers.DevicesMapper;
import com.kevingates.uploadimage.models.Devices;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan("com.kevingates.uploadimage.mappers") //扫描的mapper
@SpringBootApplication
public class UploadimageApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadimageApplication.class, args);
	}

	//private final DevicesMapper devicesMapper;

//	public UploadimageApplication(DevicesMapper devicesMapper) {
//		this.devicesMapper = devicesMapper;
//	}
//
//	@Bean
//	CommandLineRunner sampleCommandLineRunner() {
//		return args -> {
//			Devices device = new Devices();
//			device.setDeviceIdentificationCode("test");
//			devicesMapper.insertDevices(device);
//			System.out.println("good");
//		};
//	}
}
