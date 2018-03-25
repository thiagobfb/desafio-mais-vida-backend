package br.med.maisvida.desafio.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({ "br.med.maisvida.desafio.resources" })
public class ResourceWebConfig extends WebMvcConfigurerAdapter {

}
