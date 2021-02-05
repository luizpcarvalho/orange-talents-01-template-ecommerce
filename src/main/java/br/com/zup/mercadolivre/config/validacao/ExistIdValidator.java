package br.com.zup.mercadolivre.config.validacao;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistIdValidator implements ConstraintValidator<ExistId, Object> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistId params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null){
            return true;
        }

        Query query = entityManager.createQuery("select 1 from " + klass.getName() + " where "
                + domainAttribute + "=:value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado(a) mais de um(a) " + klass
                + "com o atributo " + domainAttribute + " = " + o);

        return !list.isEmpty();
    }

}
