package travel.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
@EnableWebMvc
@ComponentScan("travel.controller")
public class WebConfig implements WebMvcConfigurer{
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
        //1������FastJson��Ϣת������ 
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        //2������FastJsonConfig�����趨���л�����  ���л��������SerializerFeature���У�����ὲ
        FastJsonConfig fastJsonConfig= new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteNonStringKeyAsString);
        //���˾Ϳ���WriteNonStringKeyAsString ������String���͵�keyת����String���ͣ�����ǰ̨�޷���Json�ַ���ת����Json����
      
        //3����������������
        List<MediaType> fastMedisTypes = new ArrayList<MediaType>();
        fastMedisTypes.add(MediaType.APPLICATION_JSON_UTF8);//�趨Json��ʽ�ұ���Ϊutf-8
        fastConverter.setSupportedMediaTypes(fastMedisTypes);
        //4����ת������Ӧ����ת������ 
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		
		converters.add(fastJsonHttpMessageConverters());
		
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
