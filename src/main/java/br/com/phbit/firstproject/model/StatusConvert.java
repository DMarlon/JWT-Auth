package br.com.phbit.firstproject.model;

import javax.persistence.AttributeConverter;

import br.com.phbit.firstproject.enumerators.Status;

public class StatusConvert implements AttributeConverter<Status, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Status status) {
		return status.getCode();
	}

	@Override
	public Status convertToEntityAttribute(Integer statuscode) {
		return Status.getStatus(statuscode);
	}

}
