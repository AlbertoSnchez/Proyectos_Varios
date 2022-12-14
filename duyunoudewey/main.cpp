#include <allegro.h>
#include<iostream>
#include<windows.h>
#include<MMSystem.h>

void init();
void deinit();

int main() {
	init();
	
	SAMPLE *sample = load_sample("C:\\Users\\alber\\Escritorio\\Proyectos\\Pruebas\\Enemy.wav");
	if (sample == NULL)
	  {
	    // You have a problem: file not found, format unknown to the library (.WAV),
	    // or the file was corrupted.
	    return (ERROR_BAD_SOUND); // <-- Make up your own error code here
	  }
  	play_sample(sample, 255, 0, 2000, 0);
	while (!key[KEY_ESC]) {
		/* put your code here */
		clear_to_color(screen,makecol(255,255,255));
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
	install_mouse();
	install_sound(DIGI_AUTODETECT , MIDI_AUTODETECT ,0);
	/* add other initializations here */
}

void deinit() {
	clear_keybuf();
	/* add other deinitializations here */
}

