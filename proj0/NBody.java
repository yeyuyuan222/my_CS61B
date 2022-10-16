public class NBody {

    public static double readRadius(String filename) {
        In input = new In(filename);
        input.readLine();
        return input.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In input = new In(filename);
        int N = input.readInt();// 1st line
        input.readDouble();// 2nd line
        Planet[] allp = new Planet[N];
        for (int i = 0; i < N; ++i) {
            allp[i] = new Planet(input.readDouble(), input.readDouble(), input.readDouble(), input.readDouble(),
                    input.readDouble(), input.readString());
        }
        return allp;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allp = readPlanets(filename);

        double time = 0;
        int N = allp.length;
        for (; time < T; time += dt) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];

            for (int i = 0; i < N; ++i) {
                xForces[i] = allp[i].calcNetForceExertedByX(allp);
                yForces[i] = allp[i].calcNetForceExertedByY(allp);
            }
           
            for(int i=0;i<N;++i){
                allp[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0,0 , "images/starfield.jpg");
            for (Planet p : allp) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
           StdDraw.enableDoubleBuffering();

        //StdAudio.play("audio/2001.mid");
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
         }
    }


}
