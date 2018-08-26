package net.dxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描mybatis mapper 包路径
@MapperScan(basePackages = "net.dxs.mapper")
// 扫描 所有需要的包，包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages = { "net.dxs", "org.n3r.idworker" })
//开启定时任务
@EnableScheduling
public class DxsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DxsApplication.class, args);
	}
}
