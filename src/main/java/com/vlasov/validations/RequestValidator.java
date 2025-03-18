package com.vlasov.validations;

import java.util.Optional;

public interface RequestValidator<T> {
    Optional<ValidationError> validate(T t);
}
