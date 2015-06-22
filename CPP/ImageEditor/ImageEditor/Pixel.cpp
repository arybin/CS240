//
//  Pixel.cpp
//  ImageEditor
//
//  Created by Andrei Rybin on 6/20/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

#include "Pixel.h"

Pixel::Pixel(int red, int green, int blue)
{
    _red = red;
    _blue = blue;
    _green = green;
    setStringValues();
    
}

Pixel::Pixel(std::string red,
             std::string green,
             std::string blue,
             bool isEdge
             )
{
    _sRed = red;
    _sGreen = green;
    _sBlue = blue;
    _isEdge = isEdge;
    _red = std::stoi(_sRed);
    _green = std::stoi(_sGreen);
    _blue = std::stoi(_sBlue);
}

void Pixel::setMotionValue(int motionValue)
{
    _motionValue = motionValue;
}

void Pixel::setStringValues()
{
    _sRed = std::to_string(_red);
    _sGreen = std::to_string(_green);
    _sBlue = std::to_string(_blue);
}

int Pixel::getRed()
{
    return _red;
}

int Pixel::getGreen()
{
    return _green;
}

int Pixel::getBlue()
{
    return _blue;
}

std::string Pixel::getSRed()
{
    return _sRed;
}

std::string Pixel::getSGreen()
{
    return _sGreen;
}

std::string Pixel::getSBlue()
{
    return _sBlue;
}

void Pixel::invert(std::vector<Pixel> & modifiedImage, int x)
{
    int red = std::abs((double)_red - (double)MAX_VALUE);
    int green = std::abs((double)_green - (double)MAX_VALUE);
    int blue = std::abs((double)_blue - (double)MAX_VALUE);
    Pixel p(red,green,blue);
    modifiedImage[x] = p;
    
}

void Pixel::grayscale()
{
    int average = (_red + _green + _blue) / NUM_OF_COLORS;
    Pixel p(average,average,average);

}

void Pixel::emboss()
{
    
}

void Pixel::motionblur()
{
    
}


