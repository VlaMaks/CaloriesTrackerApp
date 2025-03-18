package com.vlasov.calories.validations;

import java.util.List;

public interface RequestValidator<T> {
    List<ValidationError> validate(T request);
}
