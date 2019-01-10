package spittr.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import spittr.filter.MyFilter;

import javax.servlet.*;
import java.util.HashMap;
import java.util.Map;

public class SpittrAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new MyFilter()};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setMultipartConfig(
                new MultipartConfigElement(
                        "/users/stringtek/tmp/spittr/uploads",
                        2097152,
                        4194304,
                        0));
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        Map<String,String> params=new HashMap<String,String>();
        params.put("resetEnable","true");//允许清空统计数据
        params.put("loginUsername","root");//访问druid可视化界面的用户名
        params.put("loginPassword","riven2832");//访问druid可视化界面的密码
        ServletRegistration.Dynamic druidServlet=servletContext.addServlet("druid", StatViewServlet.class);
        druidServlet.addMapping("/druid/*");
        druidServlet.setInitParameters(params);
    }
}
