package de.bripkens.i18n;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * TODO Ben make formatter configurable
 * TODO Ben fallback bundle locale
 * TODO Ben configure whether MissingResourceException should be rethrown
 * TODO Ben ResourceBundle.getBundle can fail. Make it an i18n Exception
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class Messages {

  private static final Map<ProxyCacheKey, Object> PROXY_CACHE =
      new ConcurrentHashMap<ProxyCacheKey, Object>();

  public static <T> T get(Class<T> proxyType, Locale locale) {
    assert proxyType != null;
    assert locale != null;
    assert proxyType.isAnnotationPresent(MessagesSource.class);
    assert proxyType.isInterface();

    ProxyCacheKey cacheKey = new ProxyCacheKey(proxyType, locale);
    if (PROXY_CACHE.containsKey(cacheKey)) {
      return (T) PROXY_CACHE.get(cacheKey);
    }

    T proxy = createNewProxy(proxyType, locale);
    PROXY_CACHE.put(cacheKey, proxy);
    return proxy;
  }

  private static <T> T createNewProxy(Class<T> proxyType, Locale locale) {
    String bundleName = proxyType.getAnnotation(MessagesSource.class).value();
    ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);

    T proxy = (T) Proxy.newProxyInstance(Messages.class.getClassLoader(),
        new Class[]{proxyType},
        new MessageResolver(bundle, bundleName));
    return proxy;
  }

  private static class MessageResolver implements InvocationHandler {

    private final ResourceBundle bundle;
    private final String bundleName;

    private MessageResolver(ResourceBundle bundle, String bundleName) {
      this.bundle = bundle;
      this.bundleName = bundleName;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      try {
        String msg = bundle.getString(method.getName());
        return format(msg, args);
      } catch (MissingResourceException ex) {
        return "<<Missing resource bundle entry for '" + method.getName() +
            "' and locale " + bundle.getLocale().getDisplayName() +
            " in resource bundle '" + bundleName + "'>>";
      }
    }
  }

  private static String format(String format, Object[] params) {
    // when there are no format parameters, then we can skip the parsing step
    // that is done automatically by the MessageFormat class.
    if (params == null || params.length == 0) {
      return format;
    }

    return new MessageFormat(format).format(params);
  }
}
