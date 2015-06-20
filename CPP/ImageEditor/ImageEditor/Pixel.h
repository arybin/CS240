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
class Pixel
{
private:
    int _x;
    int _y;
    Pixel * _originalImage;
    Pixel * _modifiedImage;
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
          bool isEdge,
          Pixel * originalImage,
          Pixel * modifiedImage,
          int x,
          int y
          );
    void setMotionValue(int motionValue);
    int getRed();
    int getGreen();
    int getBlue();
    void invert();
    void grayscale();
    void emboss();
    void motionblur();
    std::string getSRed();
    std::string getSGreen();
    std::string getSBlue();
    
};

#endif
