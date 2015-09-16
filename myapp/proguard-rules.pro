# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# keep volley
-keep class com.android.volley.** {*;}
-keep interface com.android.volley.** {*;}
-dontwarn com.android.volley.**

# keep apache
-keep class org.apache.http.** {*;}
-keep interface org.apache.http.** {*;}
-dontwarn org.apache.http.**

# remove log
-assumenosideeffects class android.util.Log{
    public static int v(...);
    public static int i(...);
    public static int d(...);
    public static int w(...);
    public static int e(...);
}

# remove println
-assumenosideeffects class java.io.PrintStream{
    public void println(%);
    public void println(**);
}