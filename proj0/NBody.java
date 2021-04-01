public class NBody{
	static double readRadius(String filename){
		double r;
		In in = new In(filename);
		int firstItemInFile = in.readInt();
		r=in.readDouble();
		return r;
	}
	static Planet[] readPlanets(String filename){
		double xxPos, yyPos,xxVel,yyVel,mass;
		String imgFilename;
		In in = new In(filename);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		Planet[] p=new Planet[5];
		for(int i=0;i<5;i++){
			xxPos=in.readDouble();
			yyPos=in.readDouble();
			xxVel=in.readDouble();
			yyVel=in.readDouble();
			mass=in.readDouble();
			imgFilename=in.readString();
			p[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFilename);


		}
		return p;
	}
	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];

		double now_t=0;

		double universe_radios;
		Planet[] p=new Planet[5];

		double[] xforces=new double[5];
		double[] yforces=new double[5];



		universe_radios=readRadius("./data/planets.txt");
		p=readPlanets("./data/planets.txt");

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-universe_radios,universe_radios);
		StdDraw.clear();
		StdDraw.picture(0,0,"./images/starfield.jpg");
		StdDraw.show();

		for(int i=0;i<5;i++){
			p[i].draw();

		}
		StdDraw.show();

		while(now_t<T){
			for(int i=0;i<5;i++){
				xforces[i]=p[i].calcNetForceExertedByX(p);
				yforces[i]=p[i].calcNetForceExertedByY(p);
				p[i].update(dt,xforces[i],yforces[i]);

			}
			StdDraw.setScale(-universe_radios,universe_radios);
			StdDraw.clear();
			StdDraw.picture(0,0,"./images/starfield.jpg");
			for(int i=0;i<5;i++){
			p[i].draw();

			}
			StdDraw.show();
			StdDraw.pause(10);
			now_t=now_t+dt;
		}

	}
}
