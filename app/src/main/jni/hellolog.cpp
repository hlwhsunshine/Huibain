#include "hellolog.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char * hellolog();
char * secondLog();
int main()
{
    char * name2;
    name2 = hellolog();
    printf("%s\n",name2);
    return 0;
}

char * hellolog()
{
    static char name[10];
    strcpy(name,"chenlong");
    return name;
}

char * secondLog(){
     static char name[10];
     strcpy(name,"secondLog");
     return name;
}