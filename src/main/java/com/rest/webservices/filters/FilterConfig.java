package com.rest.webservices.filters;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    public FilterProvider getFilterUser() {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("email", "name");
        return new SimpleFilterProvider().addFilter(FiltersName.UserFilter.name(), simpleBeanPropertyFilter);
    }
}