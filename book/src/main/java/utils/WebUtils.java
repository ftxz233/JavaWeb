package utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author Rookie
 * @Date 2021/6/23
 */
public class WebUtils extends BeanUtils {
    public static <T> T copyParamToBean(Map value, T Bean) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.populate(Bean,value);
        return Bean;
    }
}
