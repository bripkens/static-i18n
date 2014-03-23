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
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class Messages {

  private static final Map<ProxyCacheKey, Object> PROXY_CACHE =
      new ConcurrentHashMap<ProxyCacheKey, Object>();

  @SuppressWarnings("unchecked")
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

  @SuppressWarnings("unchecked")
  private static <T> T createNewProxy(Class<T> proxyType, Locale locale) {
    String bundleName = proxyType.getAnnotation(MessagesSource.class).value();
    ResourceBundle bundle = getBundle(bundleName, locale);

    return (T) Proxy.newProxyInstance(Messages.class.getClassLoader(),
        new Class[]{proxyType},
        new MessageResolver(bundle));
  }

  private static ResourceBundle getBundle(String name, Locale locale) {
    try {
      return ResourceBundle.getBundle(name, locale);
    } catch (MissingResourceException ex) {
      throw new I18nException(ex);
    }
  }

  private static class MessageResolver implements InvocationHandler {

    private final ResourceBundle bundle;

    private MessageResolver(ResourceBundle bundle) {
      this.bundle = bundle;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      try {
        String msg = bundle.getString(method.getName());
        return format(msg, args);
      } catch (MissingResourceException ex) {
        throw new I18nException(ex);
      }
    }
  }

  private static String format(String format, Object[] params) {
    // we can skip the parsing step when there are no format parameters
    // (parsing is done by the MessageFormat class)
    if (params == null || params.length == 0) {
      return format;
    }

    return new MessageFormat(format).format(params);
  }
}
