#include <allegro.h>

void init();
void deinit();

int main() {
	init();

	while (!key[KEY_ESC]) {
		/* put your code here */
			int x = 100, y = 100, tempx = 100, tempy = 100,dir = 1;
	while (!key[KEY_ESC]) {
		/* put your code here */
		tempx = x;
		tempy = y;
		if(dir == 1 && x != 20 && y !=20){
			x--;
			y--;
		} else if(dir == 2 && x != 20 && y !=460){
			x--;
			y++;
		} else if(dir == 3 && x != 620 && y !=20){
			x++;
			y--;
		}else if(dir == 4 && x != 620 && y !=460){
			x++;
			y++;
		} else {
			dir = rand() % 4 + 1;
		}
		acquire_screen();
		circlefill(screen,tempx,tempy,20,makecol(0,0,0));
		circlefill(screen,x,y,20,makecol(128,225,0));
		release_screen();
		rest(10);
	}

	deinit();
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
	install_mouse();
	/* add other initializations here */
}

void deinit() {
	clear_keybuf();
	/* add other deinitializations here */
}
