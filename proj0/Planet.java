public class Planet {
	/** Creating a basic version of the Planet class with following 6 instance variables. */
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double G = 6.67 * 1e-11; // The gravitational constant G;


	/** The first constructor */
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** The second constructor */
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/** Calculates the distance between two Planets. */
	public double calcDistance(Planet p) {
		return Math.sqrt(Math.pow(p.xxPos - this.xxPos, 2) + Math.pow(p.yyPos - this.yyPos, 2));
	}

	/** Describing the force exerted on this Planet by the given Planet. */
	public double calcForceExertedBy(Planet p) {
		double r = this.calcDistance(p);
		double F = (G * this.mass * p.mass) / Math.pow(r, 2);  
		return F;
	}
	
	/** Describing the force exerted int the X and Y directions. */
	public double calcForceExertedByX(Planet p) {
		double F = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		double Fx = F * dx / r;
		return Fx;
	}

	public double calcForceExertedByY(Planet p) {
		double F = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		double Fy = F * dy / r;
		return Fy;
	}
	
	/** Calculating the net X and net Y force exerted by all Planets int that array upon the current Planets. */
	public double calcNetForceExertedByX(Planet[] p) {
		double netX = 0;
		for(int i = 0; i < p.length; i ++) {
			if(this.equals(p[i])) {
				continue;
			}else {
				netX += this.calcForceExertedByX(p[i]);
			}
		}
		return netX;
	}

	public double calcNetForceExertedByY(Planet[] p) {
		double netY = 0;
		for(int i = 0; i < p.length; i ++) {
			if(this.equals(p[i])) {
				continue;
			} else {
				netY += this.calcForceExertedByY(p[i]);
			}
		}
		return netY;
	}

	/** Update the planet's position and velocity instance variable. */
	public void update(double dt, double fx, double fy) {
		double anetX = fx / mass, anetY = fy / mass;
		xxVel = xxVel + dt * anetX;
		yyVel = yyVel + dt * anetY;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	/** Using the StdDraw API mentioned above to draw the Planet's image at the Planet's position. */
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}
