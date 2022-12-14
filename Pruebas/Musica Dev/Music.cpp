#include<iostream>
#include<windows.h>
#include<MMSystem.h>

using namespace std;


int main(){
	cout<<"Playing music..."<<endl;
	PlaySound(TEXT("Enemy.wav"), NULL, SND_FILENAME | SND_LOOP | SND_ASYNC);

	while (!GetAsyncKeyState(27)){
		//Dale esc para que pare
	}
	PlaySound(NULL,0,0);//Con esto se para
	while (!GetAsyncKeyState(38)){
		//Dale flechita arriba pa que se empiece
	}
	PlaySound(TEXT("Enemy.wav"), 0,0);
	system("pause");
	return 0;
}

