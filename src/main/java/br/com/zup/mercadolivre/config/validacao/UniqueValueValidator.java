package br.com.zup.mercadolivre.config.validacao;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klass;
    private String message;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.domainAttribute = constraintAnnotation.fieldName();
        this.klass = constraintAnnotation.domainClass();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null){
            return true;
        }

        Query query = entityManager.createQuery("select 1 from " + klass.getName() + " where "
                + domainAttribute + " = :value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um " + klass.getName()
                + " com o atributo " + domainAttribute + " = " + o);

        return list.isEmpty();
    }

}
