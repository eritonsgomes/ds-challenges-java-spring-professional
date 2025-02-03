package com.devsuperior.dsdesafios.dsdesafio1mod3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode =  EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class PageRenderConfig {
}
