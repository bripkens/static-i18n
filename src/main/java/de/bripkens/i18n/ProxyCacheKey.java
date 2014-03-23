package de.bripkens.i18n;

import java.util.Locale;

/**
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
class ProxyCacheKey {

  private final Class<?> proxyType;
  private final Locale locale;

  ProxyCacheKey(Class<?> proxyType, Locale locale) {
    this.proxyType = proxyType;
    this.locale = locale;
  }

  Class<?> getProxyType() {
    return proxyType;
  }

  Locale getLocale() {
    return locale;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProxyCacheKey)) return false;

    ProxyCacheKey that = (ProxyCacheKey) o;

    if (locale != null ? !locale.equals(that.locale) : that.locale != null)
      return false;
    if (proxyType != null ? !proxyType.equals(that.proxyType) : that.proxyType != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = proxyType != null ? proxyType.hashCode() : 0;
    result = 31 * result + (locale != null ? locale.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return proxyType.getSimpleName() + "#" + locale.getDisplayName();
  }
}
