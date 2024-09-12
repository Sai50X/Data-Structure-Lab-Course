import java.util.*;

public class GuitarHero { 

    public static void main(String[] args) {
         
         String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
         GuitarString[] gs = new GuitarString[keyboard.length()];
         for (int i = 0; i < gs.length; i ++) {
            gs[i] = new GuitarString(440 * Math.pow(1.05956, i-24));
         }



        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key)!=-1) {
                     gs[keyboard.indexOf(key)].pluck();
                }
            }
            double sample = 0.0;
            for (int i = 0; i < gs.length; i++) {
               sample += gs[i].sample();
            }
            StdAudio.play(sample);
            for (int i = 0; i < gs.length; i++) {
               gs[i].tic();
            }

        }
    }

}