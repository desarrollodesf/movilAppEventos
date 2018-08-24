# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\NIROBE\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5

-dontusemixedcaseclassnames

-dontskipnonpubliclibraryclasses

-dontskipnonpubliclibraryclassmembers

-dontpreverify

-verbose

-dump class_files.txt

-printseeds seeds.txt

-printusage unused.txt

-printmapping mapping.txt

-renamesourcefileattribute SourceFile

-keepattributes SourceFile,LineNumberTable

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#restlet

-keep class org.restlet.** { *; }

-dontwarn org.restlet.**

-dontnote org.restlet.**


# rxjava

-keep class rx.schedulers.** { *; }

-keep class rx.internal.** { *; }

-dontwarn rx.**

-dontnote rx.**


#junit

-dontwarn android.test.**

-dontwarn org.junit.**


#greendao

-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {

   public static java.lang.String TABLENAME;

}

-keep class **$Properties
