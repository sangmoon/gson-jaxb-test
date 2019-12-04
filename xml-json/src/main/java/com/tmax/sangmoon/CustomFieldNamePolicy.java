package com.tmax.sangmoon;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.google.gson.FieldNamingStrategy;

public class CustomFieldNamePolicy implements FieldNamingStrategy {

	@Override
	public String translateName(Field f) {
		Annotation annotationName = null;
		
		if((annotationName = f.getAnnotation(XmlElement.class)) != null && !((XmlElement)annotationName).name().equals("##default")) {
			return ((XmlElement)annotationName).name();
		} else if((annotationName = f.getAnnotation(XmlAttribute.class)) !=null && !((XmlAttribute)annotationName).name().equals("##default")) {
			return ((XmlAttribute)annotationName).name();
		}
		return f.getName();
	}

}
