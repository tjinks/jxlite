package uk.co.cleopatra.jxlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

class NamespaceListParser {
	static NamespaceContext parse(String... namespaces) {
		Map<String, String> map = new HashMap<String, String>();
		for (String s : namespaces) {
			String[] prefixAndUri = s.split("=");
			if (prefixAndUri.length != 2) {
				throw new RuntimeException("Illegal value in namespace list");
			}
			map.put(prefixAndUri[0], prefixAndUri[1]);
		}
		return contextFromMap(map);
	}
	
	static private NamespaceContext contextFromMap(final Map<String, String> map) {
		return new NamespaceContext() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public Iterator getPrefixes(String namespaceURI) {
				List<String> prefixes = new ArrayList<String>();
				for (Map.Entry<String, String> e : map.entrySet()) {
					if (e.getValue().equals(namespaceURI)) {
						prefixes.add(e.getKey());
					}
				}
				return prefixes.iterator();
			}
			
			@Override
			public String getPrefix(String namespaceURI) {
				@SuppressWarnings("unchecked")
				Iterator<String> iter = getPrefixes(namespaceURI);
				if (iter.hasNext()) {
					return iter.next();
				}
				return null;
			}
			
			@Override
			public String getNamespaceURI(String prefix) {
				String result = map.get(prefix);
				if (result == null) {
					result = XMLConstants.NULL_NS_URI;
				}
				return result;
			}
		};
	}
}
