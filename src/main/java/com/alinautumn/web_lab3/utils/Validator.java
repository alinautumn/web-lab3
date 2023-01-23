package com.alinautumn.web_lab3.utils;

import com.alinautumn.web_lab3.entity.Shot;

public class Validator {
    public static boolean isValid(Shot aShot){
        double x = aShot.getX();
        double y = aShot.getY();
        double r = aShot.getR();
        return (x >= -5 && x <= 3 && y >= -3 && y <= 3 && r >= 1 && r <= 3 && r % 0.5 == 0.);
    }
}
