package co.bugfreak.framework;

public interface EventHandler<T> {
  void handle(Object sender, T eventArgs);
}
