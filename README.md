# android-kotlin-app
An example Android Kotlin application which showcases various Rollbar configuration settings

# Steps to build and execute the application  

## Step 1:
Download the source code from the repository https://github.com/RollbarCustomerEng/android-kotlin-app

## Step 4:  
Open a terminal and cd to the download folder
Execute thhis command
gradle wrapper --gradle-version 7.4

## Step 3:  
Open the project folder with Android Studio

## Step 4:  
IMPORTANT Click the File/Sync Project with Gradle Files menu item 


## Step 5:
Verify that the Gradle JDK version is Version 11 minimum

File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK

## Step 6
Open the file 
android-kotlin-app/app/src/main/AndroidManifest.xml

For the meta-date setting com.rollbar.android.ACCESS_TOKEN, add a Rollbar Project post_client_item access token

## Step 7 
Build and run the application

# Troublshooting Tips

## Sync Project with Gradle Files
Click the File/Sync Project with Gradle Files menu item to resync the gradle file
