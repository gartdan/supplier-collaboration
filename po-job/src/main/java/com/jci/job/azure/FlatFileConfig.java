package com.jci.job.azure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class FlatFileConfig {

	  @Value("${flat.file.destination.mapping.folder.url}")
	  private  String folderUrl;
	  
	  @Value("${flat.file.destination.supplier.url}")
	  private  String supplierUrl;
	  
	  @Value("${flat.file.destination.region.url}")
	  private  String regionUrl;
	 
	  @Value("${flat.file.name.sender.duns}")
	  private  String senderDuns;
	 
	  @Value("${flat.file.name.receiver.duns}")
	  private  String receiverDuns;
	  
	  @Value("${flat.file.name.message.type}")
	  private   String messageType;
	  
	  @Value("${flat.file.name.version}")
	  private   String version;
	  
	  @Value("${flat.file.name.site.id}")
	  private  String siteId;
	  
	  @Value("${flat.file.name.date.format}")
	  private  String dateFormat;
	  
	  @Value("${flat.file.name.date.time.zone}")
	  private  String timeZone;

	  @Bean
	  public FlatFile flatFile() {
		  FlatFile ff = new FlatFile(folderUrl, supplierUrl, senderDuns,receiverDuns,messageType,version,siteId,dateFormat,timeZone,regionUrl);
		  return ff;
	  }
	  
	  
	  
}
