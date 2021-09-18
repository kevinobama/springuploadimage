Uploading Files
This guide walks you through the process of creating a server application that can receive HTTP multi-part file uploads.

What You Will Build
You will create a Spring Boot web application that accepts file uploads. You will also build a simple HTML interface to upload a test file.

What You Need
About 15 minutes

A favorite text editor or IDE

JDK 1.8 or later

Gradle 4+ or Maven 3.2+

You can also import the code straight into your IDE:

Spring Tool Suite (STS)

IntelliJ IDEA

How to complete this guide
Like most Spring Getting Started guides, you can start from scratch and complete each step or you can bypass basic setup steps that are already familiar to you. Either way, you end up with working code.

To start from scratch, move on to Starting with Spring Initializr.

To skip the basics, do the following:

Download and unzip the source repository for this guide, or clone it using Git: git clone https://github.com/spring-guides/gs-uploading-files.git

cd into gs-uploading-files/initial

Jump ahead to Create an Application Class.

When you finish, you can check your results against the code in gs-uploading-files/complete.

Starting with Spring Initializr
If you use Maven, visit the Spring Initializr to generate a new project with the required dependencies (Spring Web and Thymeleaf).

The following listing shows the pom.xml file that is created when you choose Maven:

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>
<parent>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>2.5.2</version>
<relativePath/> <!-- lookup parent from repository -->
</parent>
<groupId>com.example</groupId>
<artifactId>uploading-files-initial</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>uploading-files-initial</name>
<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>COPY
If you use Gradle, visit the Spring Initializr to generate a new project with the required dependencies (Spring Web and Thymeleaf).

The following listing shows the build.gradle file that is created when you choose Gradle:

plugins {
id 'org.springframework.boot' version '2.5.2'
id 'io.spring.dependency-management' version '1.0.11.RELEASE'
id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

repositories {
mavenCentral()
}

dependencies {
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
implementation 'org.springframework.boot:spring-boot-starter-web'
testImplementation('org.springframework.boot:spring-boot-starter-test')\
}

test {
useJUnitPlatform()
}COPY
Manual Initialization (optional)
If you want to initialize the project manually rather than use the links shown earlier, follow the steps given below:

Navigate to https://start.spring.io. This service pulls in all the dependencies you need for an application and does most of the setup for you.

Choose either Gradle or Maven and the language you want to use. This guide assumes that you chose Java.

Click Dependencies and select Spring Web and Thymeleaf.

Click Generate.

Download the resulting ZIP file, which is an archive of a web application that is configured with your choices.

If your IDE has the Spring Initializr integration, you can complete this process from your IDE.
Create an Application Class
To start a Spring Boot MVC application, you first need a starter. In this sample, spring-boot-starter-thymeleaf and spring-boot-starter-web are already added as dependencies. To upload files with Servlet containers, you need to register a MultipartConfigElement class (which would be <multipart-config> in web.xml). Thanks to Spring Boot, everything is auto-configured for you!

All you need to get started with this application is the following UploadingFilesApplication class (from src/main/java/com/example/uploadingfiles/UploadingFilesApplication.java):

package com.example.uploadingfiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UploadingFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadingFilesApplication.class, args);
	}

}COPY
As part of auto-configuring Spring MVC, Spring Boot will create a MultipartConfigElement bean and make itself ready for file uploads.

Create a File Upload Controller
The initial application already contains a few classes to deal with storing and loading the uploaded files on disk. They are all located in the com.example.uploadingfiles.storage package. You will use those in your new FileUploadController. The following listing (from src/main/java/com/example/uploadingfiles/FileUploadController.java) shows the file upload controller:

package com.example.uploadingfiles;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.uploadingfiles.storage.StorageFileNotFoundException;
import com.example.uploadingfiles.storage.StorageService;

@Controller
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}COPY
The FileUploadController class is annotated with @Controller so that Spring MVC can pick it up and look for routes. Each method is tagged with @GetMapping or @PostMapping to tie the path and the HTTP action to a particular controller action.

In this case:

GET /: Looks up the current list of uploaded files from the StorageService and loads it into a Thymeleaf template. It calculates a link to the actual resource by using MvcUriComponentsBuilder.

GET /files/{filename}: Loads the resource (if it exists) and sends it to the browser to download by using a Content-Disposition response header.

POST /: Handles a multi-part message file and gives it to the StorageService for saving.

In a production scenario, you more likely would store the files in a temporary location, a database, or perhaps a NoSQL store (such as Mongo’s GridFS). It is best to NOT load up the file system of your application with content.
You will need to provide a StorageService so that the controller can interact with a storage layer (such as a file system). The following listing (from src/main/java/com/example/uploadingfiles/storage/StorageService.java) shows that interface:

package com.example.uploadingfiles.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}