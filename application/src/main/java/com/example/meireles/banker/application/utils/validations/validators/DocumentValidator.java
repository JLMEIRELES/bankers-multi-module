package com.example.meireles.banker.application.utils.validations.validators;


import com.example.meireles.banker.application.utils.validations.annotation.Document;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;


@RequiredArgsConstructor
public class DocumentValidator implements ConstraintValidator<Document, String> {

    private final CPFValidator cpfValidator;

    private final CNPJValidator cnpjValidator;

    @Override
    public boolean isValid(String document, ConstraintValidatorContext context) {
        if (document == null){
            return true;
        }

        document = document.replaceAll("\\D", "");
        return document.length() == 11 ? cpfValidator.isValid(document, context) : cnpjValidator.isValid(document, context);
    }
}
