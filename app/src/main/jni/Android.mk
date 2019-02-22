LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_LDLIBS :=-llog
LOCAL_MODULE:= process
LOCAL_SRC_FILES:= forktest.cpp
include $(BUILD_EXECUTABLE)