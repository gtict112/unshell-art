//
// Created by 袁东明 on 2017/7/1.
//

#include <jni.h>

#ifndef DEXDUMP_DUMP_H
#define DEXDUMP_DUMP_H

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_com_example_hrst_xposedtest_Dumpper_dump(JNIEnv *env, jclass clazz);
typedef uint8_t byte;
namespace art {

    class OatFile;

    class DexFile;

    class OatDexFile;

    class MemMap;
}
#ifdef __cplusplus
}
#endif

#endif //DEXDUMP_DUMP_H
