package com.liweifan.common.json.entity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

public class DateSerializer extends StdScalarSerializer<Date> {


	private static final long serialVersionUID = 1L;


	public DateSerializer() {
		super(Date.class);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void serialize(Date date,
                          JsonGenerator jsonGenerator,
                          SerializerProvider provider) throws IOException, JsonGenerationException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonGenerator.writeString(sdf.format(date));
    }
}
