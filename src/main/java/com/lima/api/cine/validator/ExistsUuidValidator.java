package com.lima.api.cine.validator;

import com.lima.api.cine.exception.RecursoNaoEncontradoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ExistsUuidValidator implements ConstraintValidator<ExistsUuid, String> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsUuid param) {
        domainAttribute = param.fieldName();
        klass = param.domainClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value != null) {
            Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
            query.setParameter("value", value);
            List<?> list = query.getResultList();
            if(list.isEmpty()){
                throw new RecursoNaoEncontradoException(klass.getSimpleName() + " não existe!");
            }
            return true;
        }
        return true;
    }
}
