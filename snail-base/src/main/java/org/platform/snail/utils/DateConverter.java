package org.platform.snail.utils;

import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;

public class DateConverter implements Converter {
	private Logger log = Logger.getLogger(this.getClass());
	private Object defaultValue = null;
	private boolean useDefault = true;

	public DateConverter() {
		this.useDefault = true;

	}

	public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
		if (value == null || "".equals(value)) {
			if (useDefault) {
				return (defaultValue);
			} else {
				throw new ConversionException("No value specified");
			}
		}

		if (value instanceof Date) {
			return (value);
		}
		if (value instanceof String) {
			try {
				if(((String) value).length()==10){
					return SnailUtils.parseDate(value.toString(),
							"yyyy-MM-dd");
				}else if(((String) value).length()==19){
					return SnailUtils.parseDate(value.toString(),
							"yyyy-MM-dd HH:mm:ss");
				}else if(((String) value).length()==16){
					return SnailUtils.parseDate(value.toString(),"yyyy-MM-dd HH:mm");
				}else{
					return SnailUtils.parseDate(value.toString(),
							"yyyy-MM-dd HH:mm:ss zz");
				}
				
			} catch (Exception e) {
				log.error("convert error ocured.", e);
				if (useDefault) {
					return (defaultValue);
				} else {
					throw new ConversionException(e);
				}
			}

		}
		return defaultValue;
	}

}
