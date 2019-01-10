package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.AbstractTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.io.IOException;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
@PropertySource("classpath:application.properties")
public class WebConfig  extends WebMvcConfigurerAdapter {

    private Environment env;
    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    //模版解析器
    @Bean
    public ITemplateResolver templateResolver(){
        ServletContextTemplateResolver templateResolver
                =new ServletContextTemplateResolver(ContextLoader.getCurrentWebApplicationContext().getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("html");
        templateResolver.setCharacterEncoding("utf-8");
        return templateResolver;
    }
    //模版引擎
    @Bean
    public TemplateEngine templateEngine(ITemplateResolver templateResolver){
        SpringTemplateEngine templateEngine
                =new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
    @Bean
    public ViewResolver viewResolver(TemplateEngine templateEngine){
        ThymeleafViewResolver viewResolver
                =new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("utf-8");
        return viewResolver;
    }
    //使用servlet3.0提供的方法来处理Multipart文件上传(配置完这个还要对DispatcherServlet进行配置主要是设置临时存储路径)
//    @Bean
//    public MultipartResolver multipartResolver(){
//        return new StandardServletMultipartResolver();
//    }

//    @Bean
//    public MultipartResolver multipartResolver() throws IOException{
//        CommonsMultipartResolver multipartResolver
//                = new CommonsMultipartResolver();
//        multipartResolver.setUploadTempDir(
//                new FileSystemResource("/tmp/spittr/uploads"));
//        multipartResolver.setMaxInMemorySize(0);
//        multipartResolver.setMaxUploadSize(2097152);
//        multipartResolver.setMaxUploadSizePerFile(1048576);
//        return multipartResolver;
//    }
    //配置静态文件的处理方法
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("WEB-INF/static/");
    }

    @Bean
    public String testBean(){
        System.out.println(env.getProperty("druid.password"));
        return "123";
    }
}
