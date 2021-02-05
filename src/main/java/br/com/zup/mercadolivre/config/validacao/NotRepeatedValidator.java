package br.com.zup.mercadolivre.config.validacao;

import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicasRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class NotRepeatedValidator implements ConstraintValidator<NotRepeated, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null){
            return true;
        }

        List<CaracteristicasRequest> lista = (List<CaracteristicasRequest>) o;
        List<CaracteristicasRequest> listaSemRepeticao = lista.stream().distinct().collect(Collectors.toList());

        return lista.size() == listaSemRepeticao.size();
    }

    @Override
    public void initialize(NotRepeated constraintAnnotation) {

    }
}
