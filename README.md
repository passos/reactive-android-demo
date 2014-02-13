reactive-android-demo
=====================

A demo of RxJava for Android to show how to create a reactive Android app.


Currently this project has a ListView Adapter demo, which will

1. send HTTP request to google image search api
2. get returned JSON data asynchronously and convert JSON data to POJO
3. show search results in a ListView. Each result has title, content and image
4, download and show item image in asynchronous mode


No handler, no manual thread, no intent service, no event bus, no static class. 

Just reactive by RxJava
