package spittr.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.MessageSourceSupport;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import spittr.mapper.SpittleMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@ComponentScan(
        basePackages = "spittr",
        excludeFilters = {
                @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = EnableWebMvc.class)
        }
)
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class RootConfig{
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource
				=new ResourceBundleMessageSource();
		messageSource.setBasenames("message","ValidationMessages");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
	@Bean
	public DataSource dataSource(
			@Value("${druid.username}") String username,
			@Value("${druid.password}") String password,
			@Value("${druid.driver}") String driver,
			@Value("${druid.url}") String url
	){
		DruidDataSource dataSource
				=new DruidDataSource();
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		return dataSource;
	}
	@Bean
	public SqlSessionFactoryBean myBatisSessionFactory(DataSource dataSource){
		SqlSessionFactoryBean sqlSessionFactoryBean
				 =new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("spittr.mapper");
		sqlSessionFactoryBean.setMapperLocations(new Resource[]{
				new ClassPathResource("mapper/SpittleMapper.xml"),
				new ClassPathResource("mapper/SpitterMapper.xml")

		});
		return sqlSessionFactoryBean;
	}
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer
				=new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("spittr.mapper");
		return mapperScannerConfigurer;
	}


}
