public  class Planet{
    static final double G=6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV,double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
   public double calcDistance(Planet p){
        double offset_x=xxPos-p.xxPos;
        double offset_y=yyPos-p.yyPos;
        return Math.pow(offset_x*offset_x+offset_y*offset_y,0.5);
   } 

   public double calcForceExertedBy(Planet p){
    //F=G*m1*m2/r^2
    double r=calcDistance(p);
    double F=(G*mass*p.mass)/(r*r);
    return F;
   }
   public double calcNetForceExertedByX(Planet allp[]){
        double Fx=0;

        for(Planet p:allp){
            if(this.equals(p)){
                continue;
            }
            double r=calcDistance(p);
            double dx=p.xxPos-xxPos;
            double F=calcForceExertedBy(p);
            Fx+= F*dx/r;
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Planet allp[]){
        double Fy=0;
        for(Planet p:allp){
            
            if(this.equals(p)){
                continue;
            }
            double r=calcDistance(p);
            double dy=p.yyPos-yyPos;
            double F=calcForceExertedBy(p);
            Fy+= F*dy/r;
        }
        return Fy;
    }

    public void update(double dt, double Fx,double Fy){
        double ax=Fx/mass;
        double ay=Fy/mass;
        xxVel+=ax*dt;
        yyVel+=ay*dt;
        xxPos+=xxVel*dt;
        yyPos+=yyVel*dt;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
        //StdDraw.show();
    }
}