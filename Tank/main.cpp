#include <allegro.h>
#include <winalleg.h>
using namespace std;

void init();
void deinit();

int main() {
	init();
	int x = 0, y = 0,dir = 1;
	BITMAP *tank;
	while(!key[KEY_ESC]){
		if(dir == 1 && x != 560 && y == 0)
			x++;
	 	else if(dir == 2 && x == 560 && y !=400)
			y++;
		else if(dir == 3 && x != 0 && y == 400)
			x--;
		else if(dir == 4 && x == 0 && y != 0)
			y--;
		else if (dir == 5)
			dir = 1;
		else 
			dir++;
		acquire_screen();
		tank = load_bitmap("tank.bmp",NULL);
		clear_bitmap(screen);
		draw_sprite(screen,ball,x,y);
		release_screen();
		//Sleep(1);
	}
	deinit();
	allegro_exit();
	return 0;
}
END_OF_MAIN()

void init() {
	int depth, res;
	allegro_init();
	depth = desktop_color_depth();
	if (depth == 0) depth = 32;
	set_color_depth(depth);
	res = set_gfx_mode(GFX_AUTODETECT_WINDOWED, 640, 480, 0, 0);
	if (res != 0) {
		allegro_message(allegro_error);
		exit(-1);
	}
	install_timer();
	install_keyboard();
	install_sound(DIGI_AUTODETECT,MIDI_AUTODETECT,NULL);
	install_mouse();
	/* add other initializations here */
	show_mouse(screen);
	install_sound(DIGI_AUTODETECT,MIDI_AUTODETECT,NULL);
}

void deinit() {
	clear_keybuf();
	/* add other deinitializations here */
}
