/**
 * 
 */
package com.example.main;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.controlador.ControladorEmple;
import com.example.controlador.ControladorTareas;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * @author XY6585IP
 *
 */
@Component
public class JerseyConfig extends ResourceConfig {
	
	@Value("${spring.jersey.application-path:/}")
    private String apiPath ;

	public JerseyConfig(){
		this.registrarControladores();
	}

	private void registrarControladores() {
		this.register(ControladorEmple.class);
		this.register(ControladorTareas.class);
	}

	@PostConstruct
	public void init() {
		this.configurarSwagger();
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}

	private void configurarSwagger() {
		
		BeanConfig config = new BeanConfig();
        config.setConfigId("Gestión de Empleados implementación");
        config.setTitle("Spring Boot + Jersey + Swagger + Docker.");
        config.setVersion("v1");
        config.setContact("Alejandro L. and Gregorio ");
        config.setSchemes(new String[] { "http"});
        config.setBasePath("/");
        config.setResourcePackage("com.example.controlador");
        packages("com.example.controlador");
        config.setPrettyPrint(true);
        config.setScan(true);
        this.register(LoggingFeature.class);
        this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);
	}

}