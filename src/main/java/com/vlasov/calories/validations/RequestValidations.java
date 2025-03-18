package com.vlasov.calories.validations;

import java.util.Optional;

public interface RequestValidations<T> {
    Optional<ValidationError> validate(T t);
}
