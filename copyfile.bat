copy ..\..\proj.android\mxdrawlibrary\build\outputs\aar\mxdrawlibrary-release.aar .\app\lib\mxdrawlibrary.aar /y
copy ..\..\proj.android\mxdrawlibrary\build\intermediates\intermediate-jars\release\jni\armeabi-v7a\libmxdrawobj.so  .\app\libs\armeabi-v7a\libmxdrawobj.so /y
copy ..\..\proj.android\mxdrawlibrary\build\intermediates\intermediate-jars\release\jni\arm64-v8a\libmxdrawobj.so  .\app\libs\arm64-v8a\libmxdrawobj.so /y
copy ..\..\proj.android\mxdrawlibrary\build\intermediates\intermediate-jars\release\jni\x86\libmxdrawobj.so  .\app\libs\x86\libmxdrawobj.so /y
xcopy  ..\..\proj.android\app\src .\app\src /s/y

xcopy  ..\..\Resources .\app\assets /s/y



