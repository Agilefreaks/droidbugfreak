Droidbugfreak
===========

Bug tracking client for wpf/silverlight applications, see [server](https://github.com/agilefreaks/apibugfreak)

Overview
========

A bare bones bug tracking framework that you can use and deploy yourself, it's main aim is to integrate seemesly 
with your application and centralize issues in a main repository, we are working on having a free server deployed
please contact us if you can help

Installation
============

Add the dependency in `build.gradle`

```groovy
dependencies {
  ....
  compile 'co.bugfreak:BugFreak:1.1.+'
  ....
}
```

Configuration and Registration
==============================
Inside your `Application#onCreate` add the configuration

```java
public class CoolApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    // hook the reported
    BugFreak.hook("apiKey", "token", this);
  }
```

Inside AndroidManifest.xml request internet permission
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

Contributing
============

1. Fork it.
2. Create a branch (git checkout -b my_cool_feature)
3. Commit your changes (git commit -am "Added CoolFeature")
4. Push to the branch (git push origin my_cool_feature)
5. Open a Pull Request
