package com.liweifan.common.json.entity;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.liweifan.common.utils.StringUtils;

public class DateDeserializer extends StdScalarDeserializer<Date> {
	

	private static final long serialVersionUID = 1L;
	protected final Pattern utsPattern=Pattern.compile(".*?T.*Z$");

	public DateDeserializer() {
		super(Date.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		
	            JsonToken currentToken = p.getCurrentToken();
	            if (currentToken == JsonToken.VALUE_STRING) {
	                String dateAsString = p.getText().trim();
	                if(StringUtils.isBlank(dateAsString)){
	                	return null;
	                }
	                
	                if(utsPattern.matcher(dateAsString).matches()){
	                	dateAsString=dateAsString.replace("T", " ");
	                	dateAsString=dateAsString.replace("Z", "");
	                }
	                StandardDateFormat sdf = new StandardDateFormat();
	                
	                try {
						return sdf.parse(dateAsString);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            return null;
	}

}
