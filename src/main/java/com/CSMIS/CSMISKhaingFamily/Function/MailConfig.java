package com.CSMIS.CSMISKhaingFamily.Function;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import com.CSMIS.CSMISKhaingFamily.DAO.EmailConfigRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.EmailConfig;




@Configuration
public class MailConfig {

   @Autowired
   private EmailConfigRepository emailRepo;
	
   private String host;
   private int port;
   private String username;
   private String password;

  
   @Bean
   public JavaMailSender javaMailSender() {
       JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
       
       List<EmailConfig> email = emailRepo.findAll();
	    for(int i = 0;i < email.size();i++) {
	    	 mailSender.setHost(email.get(i).getHost());
	         mailSender.setPort(email.get(i).getPort());
	         mailSender.setUsername(email.get(i).getUsername());
	         mailSender.setPassword(email.get(i).getPassword());
	         System.out.println(email.get(i).getHost());
	         System.out.println(email.get(i).getPort());
	         System.out.println(email.get(i).getUsername());
	         System.out.println(email.get(i).getPassword());
	    }
       
       
	    
	  	Properties properties = mailSender.getJavaMailProperties();
	  	properties.setProperty("mail.transport.protocol", "smtp");
	  	properties.setProperty("mail.smtp.auth", "true");
	  	properties.setProperty("mail.smtp.starttls.enable", "true"); 
	  	properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  	properties.setProperty("mail.debug", "true");
        return mailSender;
   }
   @Primary
   @Bean
   public FreeMarkerConfigurationFactoryBean factoryBean() {
     FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
     bean.setTemplateLoaderPath("classpath:/templates");
     
     return bean;
 }


public MailConfig() {
	
}
   
}