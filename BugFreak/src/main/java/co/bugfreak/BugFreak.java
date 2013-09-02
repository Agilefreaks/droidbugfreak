package co.bugfreak;

import android.app.Application;

import co.bugfreak.components.AndroidProvider;
import co.bugfreak.components.ErrorReportHandler;
import co.bugfreak.components.ErrorReportQueue;
import co.bugfreak.components.ErrorReportQueueListener;

public class BugFreak implements ReportingService {

  static ReportingService instance;

  BugFreak() {
  }

  public static void init() {
    Initializer.initialize();
  }

  public static ReportingService getInstance() {
    return instance;
  }

  public static void dispose() {
    GlobalConfig.getServiceLocator().getService(ErrorReportQueueListener.class).dispose();
    GlobalConfig.getServiceLocator().getService(ErrorReportHandler.class).dispose();

    instance = null;
  }

  public static void hook(String apiKey, String token, Application app) {
    GlobalConfig.setApiKey(apiKey);
    GlobalConfig.setToken(token);

    init();

    GlobalConfig.addDataProvider(new AndroidProvider(app));

    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread thread, final Throwable ex) {
        instance.beginReport(ex);
      }
    });
  }

  @Override
  public void beginReport(Throwable exc) {
    ErrorReport errorReport = createReport(exc);
    queue(errorReport);
  }

  private ErrorReport createReport(Throwable exc) {
    return ErrorReport.fromException(exc);
  }

  private void queue(ErrorReport errorReport) {
    ErrorReportQueue errorReportQueue = GlobalConfig.getServiceLocator().getService(ErrorReportQueue.class);

    errorReportQueue.enqueue(errorReport);
  }
}