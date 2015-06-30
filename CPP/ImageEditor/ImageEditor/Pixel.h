//
//  Pixel.h
//  ImageEditor
//
//  Created by Andrei Rybin on 6/20/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

#ifndef ImageEditor_Pixel_h
#define ImageEditor_Pixel_h

#include <string>
#include <cmath>
#include <vector>
class Pixel
{
private:
    int MAX_VALUE = 255;
    int NUM_OF_COLORS = 3;
    int _red;
    int _green;
    int _blue;
    std::string _sRed;
    std::string _sBlue;
    std::string _sGreen;
    bool _isEdge;
    int _motionValue;
    void setStringValues();
    
public:
    Pixel(int red, int green, int blue);
    Pixel(std::string red,
          std::string green,
          std::string blue,
          bool isEdge
          );
    Pixel() {}
    void setMotionValue(int motionValue);
    int getRed();
    int getGreen();
    int getBlue();
    void invert(std::vector<Pixel> & modifiedImage, int x);
    void grayscale(std::vector<Pixel> & modifiedImage, int x);
    void emboss(std::vector<std::vector<Pixel>> & originalImage, std::vector<Pixel> & modifiedImage, int x, int y);
    void motionblur(std::vector<std::vector<Pixel>> & originalImage, std::vector<Pixel> & modifiedImage, int x, int y);
    std::string getSRed();
    std::string getSGreen();
    std::string getSBlue();
};

#endif
