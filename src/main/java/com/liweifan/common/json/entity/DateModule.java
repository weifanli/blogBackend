package com.liweifan.common.json.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class DateModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public DateModule() {
        super();
        addSerializer(Date.class, new DateSerializer());
        addDeserializer(Date.class, new DateDeserializer());
    }
}
