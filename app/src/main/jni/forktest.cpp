#include<stdio.h>
#include <unistd.h>
#include<stdlib.h>
#include <string>
#include<jni.h>
#include<android/log.h>


#define TAG    "TestJniLog" // 这个是自定义的LOG的标识
#define LOGD(...)  __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__) // 定义LOGD类型


/*int main()
{
    FILE *fp;
    char *str = "hello,this is called process";
    if ((fp=fopen("/storage/emulated/0/ARM/test111.txt","w"))==NULL)
    {
        printf("cannot open file!");
        exit(0);
    }
    fputs(str,fp);
    fclose(fp);


   LOGD("forktest程序pid=%d", getpid());
   LOGD("forktest程序ppid=%d", getppid());
   LOGD("forktest程序uid=%d", getuid());
   LOGD("forktest程序里面，打印执行完毕");
   LOGD("forktest程序里面，开始执行循环");
      int i = 1;
     for( ; ; )
        {
        LOGD("第%d次循环",i);
       sleep(10);
        i = i+1;

        }
	return 0;
}*/

int main(){

   LOGD("B程序pid=%d", getpid());
   LOGD("B程序ppid=%d", getppid());
   LOGD("B程序uid=%d", getuid());
    int pid = fork();//fork出一个字进程
    if(pid == 0){
        LOGD("开始调用C程序");
        execl("/system/bin/su", "su", "-c", "./data/jartest", (char *)0);
    }

}


