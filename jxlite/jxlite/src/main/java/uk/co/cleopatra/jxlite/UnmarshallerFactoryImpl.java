package uk.co.cleopatra.jxlite;

import java.lang.reflect.Method;
import java.util.HashMap;

import javax.xml.namespace.NamespaceContext;

import uk.co.cleopatra.jxlite.annotations.NamespaceList;
import uk.co.cleopatra.jxlite.converters.NodeConverterRegistry;

class UnmarshallerFactoryImpl implements UnmarshallerFactory {
	private final HashMap<Class<?>, Unmarshaller<?>> unmarshallers = new HashMap<Class<?>, Unmarshaller<?>>();

	UnmarshallerFactoryImpl() {
		super();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> Unmarshaller<T> build(Class<T> intf,
			NodeConverterRegistry nodeConverterRegistry) {
		Unmarshaller<T> result;
		if (!intf.isInterface()) {
			throw new IllegalArgumentException("Interface type expected");
		}
		if (unmarshallers.containsKey(intf)) {
			result = (Unmarshaller<T>) unmarshallers.get(intf);
		} else {
			UnmarshallerImpl<T> impl = new UnmarshallerImpl(intf);
			unmarshallers.put(intf, impl);
			NamespaceContext namespaceContext = getNamespaceContext(intf);
			ProxyableGetMethodFactory proxyableGetMethodFactory = new ProxyableGetMethodFactory(
					this, namespaceContext, nodeConverterRegistry);
			HashMap<Method, ProxyableGetMethod> methodMap = new HashMap<Method, ProxyableGetMethod>();
			for (Method method : intf.getMethods()) {
				ProxyableGetMethod proxyMethod = proxyableGetMethodFactory
						.methodFor(method);
				if (proxyMethod != null) {
					methodMap.put(method, proxyMethod);
				}
			}
			impl.setMethodMap(methodMap);
			result = impl;
		}
		return result;
	}

	private static NamespaceContext getNamespaceContext(Class<?> intf) {
		NamespaceList namespaceList = intf.getAnnotation(NamespaceList.class);
		if (namespaceList == null) {
			return NamespaceListParser.parse(new String[0]);
		} else {
			return NamespaceListParser.parse(namespaceList.value());
		}
	}

}
