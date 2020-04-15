package com.hmq.framework.utis;

import java.lang.invoke.SerializedLambda;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.StringUtils;

import com.hmq.framework.utils.query.IGetter;
import com.hmq.framework.utils.query.ISetter;

public class BeanUtils {

	private static final Map<Class<?>, WeakReference<SerializedLambda>> FUNC_CACHE = new ConcurrentHashMap<>();

	public static <T> String convertToFieldName(IGetter<T> fn) {
		SerializedLambda lambda = getSerializedLambda(fn);
		String getMethodName = lambda.getImplMethodName();
		if (getMethodName.startsWith("get")) {
			getMethodName = getMethodName.substring(3);
		} else if (getMethodName.startsWith("is")) {
			getMethodName = getMethodName.substring(2);
		}
		return getMethodName.substring(0, 1).toLowerCase() + getMethodName.substring(1);
	}

	public static <T, V> String convertToFieldName(ISetter<T, V> fn) {
		SerializedLambda lambda = getSerializedLambda(fn);
		String getMethodName = lambda.getImplMethodName();
		if (getMethodName.startsWith("set")) {
			getMethodName = getMethodName.substring(3);
		}
		return getMethodName.substring(0, 1).toLowerCase() + getMethodName.substring(1);
	}

//	public static <T> Class<T> getTClass(IGetter<T> fn) {
//		SerializedLambda lambda =BeanUtils.getSerializedLambda(fn);
//		String instantiatedMethodType=lambda.getInstantiatedMethodType();
//		//(Lcom/hmq/universe/model/po/User;)Ljava/lang/Object;
//		String className=instantiatedMethodType.substring(2, instantiatedMethodType.indexOf(";"));
//		className= className.replace("/", ".");
//		
//		Class<T> tClass=null;
//		try {
//			tClass=(Class<T>) BeanUtils.class.getClassLoader().loadClass(className);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return tClass;
//	}

	public static SerializedLambda getSerializedLambda(Object fn) {
		Class<?> clazz = fn.getClass();
		return Optional.ofNullable(FUNC_CACHE.get(clazz)).map(WeakReference::get).orElseGet(() -> {
			SerializedLambda lambda = null;
			try {
				Method method = fn.getClass().getDeclaredMethod("writeReplace");
				method.setAccessible(Boolean.TRUE);
				lambda = (SerializedLambda) method.invoke(fn);
				FUNC_CACHE.put(fn.getClass(), new WeakReference<>(lambda));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return lambda;
		});
	}

	@SuppressWarnings("unchecked")
	public static <T> T convert(Object obj, Class<T> type) {
		if (obj != null && !StringUtils.isEmpty(obj.toString())) {
			if (type.equals(Integer.class) || type.equals(int.class)) {
				return (T) new Integer(obj.toString());
			} else if (type.equals(Long.class) || type.equals(long.class)) {
				return (T) new Long(obj.toString());
			} else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
				return (T) new Boolean(obj.toString());
			} else if (type.equals(Short.class) || type.equals(short.class)) {
				return (T) new Short(obj.toString());
			} else if (type.equals(Float.class) || type.equals(float.class)) {
				return (T) new Float(obj.toString());
			} else if (type.equals(Double.class) || type.equals(double.class)) {
				return (T) new Double(obj.toString());
			} else if (type.equals(Byte.class) || type.equals(byte.class)) {
				return (T) new Byte(obj.toString());
			} else if (type.equals(Character.class) || type.equals(char.class)) {
				return (T) new Character(obj.toString().charAt(0));
			} else if (type.equals(String.class)) {
				return (T) obj;
			} else if (type.equals(BigDecimal.class)) {
				return (T) new BigDecimal(obj.toString());
			} else if (type.equals(LocalDateTime.class)) {
				// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
				// HH:mm:ss");
				return (T) LocalDateTime.parse(obj.toString());
			} else if (type.equals(Date.class)) {
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
					return (T) formatter.parse(obj.toString());
				} catch (ParseException e) {
					throw new RuntimeException(e.getMessage());
				}

			} else {
				return (T) obj;
			}
		} else {
			if (type.equals(int.class)) {
				return (T) new Integer(0);
			} else if (type.equals(long.class)) {
				return (T) new Long(0L);
			} else if (type.equals(boolean.class)) {
				return (T) new Boolean(false);
			} else if (type.equals(short.class)) {
				return (T) new Short("0");
			} else if (type.equals(float.class)) {
				return (T) new Float(0.0);
			} else if (type.equals(double.class)) {
				return (T) new Double(0.0);
			} else if (type.equals(byte.class)) {
				return (T) new Byte("0");
			} else if (type.equals(char.class)) {
				return (T) new Character('\u0000');
			} else {
				return null;
			}
		}
	}
}
