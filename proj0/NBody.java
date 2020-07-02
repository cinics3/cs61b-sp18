public class NBody {
	/** Get the radius of the universe in that file. */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		double radius = in.readDouble();
		
		return radius;
	}

	/** Get an array of Planets corresponding to the Planets in the file. */
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		double radius = in.readDouble();

		Planet[] p = new Planet[N];
		double xp, yp, xv, yv, ms;
		String img;

		for(int i = 0; i < N; i ++) {
			xp = in.readDouble();
			yp = in.readDouble();
			xv = in.readDouble();
			yv = in.readDouble();
			ms = in.readDouble();
			img = in.readString();
			p[i] = new Planet(xp, yp, xv, yv, ms, img);
		}

		return p;
	}

	public static void main(String[] args) {
		/** Get data of command line arguments*/
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		
		double uniRadius = NBody.readRadius(fileName);
		Planet[] Planets = NBody.readPlanets(fileName);
		
		/** Draw the background */
		StdDraw.setScale(-uniRadius, uniRadius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		/** Draw planets */
		for(int i = 0; i < Planets.length; i ++) {
			Planets[i].draw();
		}

		StdDraw.enableDoubleBuffering();

		for(double t = 0; t <= T; t += dt) {
			double[] xForce = new double[Planets.length];
			double[] yForce = new double[Planets.length];

			for(int i = 0; i < Planets.length; i ++) {
				xForce[i] = Planets[i].calcNetForceExertedByX(Planets);
				yForce[i] = Planets[i].calcNetForceExertedByY(Planets);
			}

			for(int i = 0; i < Planets.length; i ++) {
				Planets[i].update(dt, xForce[i], yForce[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");

			for(int i = 0; i < Planets.length; i ++) {
				Planets[i].draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", Planets.length);
		StdOut.printf("%.2e\n", uniRadius);
		for(int i = 0; i < Planets.length; i ++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
					Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
		}
	}
}
