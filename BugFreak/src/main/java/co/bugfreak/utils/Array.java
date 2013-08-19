package co.bugfreak.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.bugfreak.framework.Result;

public class Array {
  public static <T extends V, V> Iterable<V> of(Class<V> clazz, Iterable<T> elements) {
    return new ArrayList<V>(toList(elements));
  }

  @SuppressWarnings("UnusedParameters")
  public static <T extends V, V> Iterable<V> of(Class<V> clazz, T ... elements) {
    ArrayList<V> list = new ArrayList<V>();

    for (T element : elements) {
      list.add(element);
    }

    return list;
  }

  public static <T> List<T> toList(Iterable<T> iterable) {
    List<T> list = new ArrayList<T>();
    for (T item : iterable) {
      list.add(item);
    }

    return list;
  }

  public static Iterable<Result> empty() {
    return Collections.emptyList();
  }
}
