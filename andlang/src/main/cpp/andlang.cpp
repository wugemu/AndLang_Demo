#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getDESKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string desKey = "dfhg(*UHO^5jqsce45sxdq3wJGTF(2q34zsddfasz";
    return env->NewStringUTF(desKey.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getQQAPPID(
        JNIEnv *env,
jobject /* this */) {
std::string QQ_APP_ID = "1106681108";
return env->NewStringUTF(QQ_APP_ID.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getQQAPPKEY(
        JNIEnv *env,
        jobject /* this */) {
    std::string QQ_APP_KEY = "lfyCgjnuWsIP0saH";
    return env->NewStringUTF(QQ_APP_KEY.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getWXAPPID(
        JNIEnv *env,
        jobject /* this */) {
    std::string WX_APP_ID = "wx5b9372fe6b0a3703";
    return env->NewStringUTF(WX_APP_ID.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getWXAPPKEY(
        JNIEnv *env,
        jobject /* this */) {
    std::string WX_APP_KEY = "9a6b4baf078c89f30e65b93fa2c6da90";
    return env->NewStringUTF(WX_APP_KEY.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getWBAPPID(
        JNIEnv *env,
        jobject /* this */) {
    std::string WB_KEY = "";
    return env->NewStringUTF(WB_KEY.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getWBSecrt(
        JNIEnv *env,
        jobject /* this */) {
    std::string WB_SECRET = "";
    return env->NewStringUTF(WB_SECRET.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getAlipayID(
        JNIEnv *env,
        jobject /* this */) {
    std::string HOTFIX_AESKEY = "2018042802603603";
    return env->NewStringUTF(HOTFIX_AESKEY.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getHOTFIXAPPID(
        JNIEnv *env,
        jobject /* this */) {
    std::string HOTFIX_APP_ID = "";
    return env->NewStringUTF(HOTFIX_APP_ID.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_andlang_util_NativeHelper_getHOTFIXAESKEY(
        JNIEnv *env,
        jobject /* this */) {
    std::string HOTFIX_AESKEY = "";
    return env->NewStringUTF(HOTFIX_AESKEY.c_str());
}



