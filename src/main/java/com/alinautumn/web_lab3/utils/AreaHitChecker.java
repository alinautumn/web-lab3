package com.alinautumn.web_lab3;

public class AreaHitChecker {
    public static boolean isHit(Shot aShot){
        return isCircleZone(aShot) || isTriangleZone(aShot) || isRectangleZone(aShot);
    }
    private static boolean isRectangleZone(Shot aShot){
        double x = aShot.getX();
        double y = aShot.getY();
        double r = aShot.getR();
        return (x<=0) && (y<=0) && (x>=(-1)*r/2) && (y>=(-1)*r);
    }
    private static boolean isCircleZone(Shot aShot){
        double x = aShot.getX();
        double y = aShot.getY();
        double r = aShot.getR();
        return (x*x + y*y <= r/2*(-1)*r/2) && (x>=0) && (y<=0);
    }
    private static boolean isTriangleZone(Shot aShot){
        double x = aShot.getX();
        double y = aShot.getY();
        double r = aShot.getR();
        return (x<=0) && (y>=0) && (y>=2*x-r);
    }
}
