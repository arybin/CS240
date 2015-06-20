//
//  Pixel.cpp
//  ImageEditor
//
//  Created by Andrei Rybin on 6/20/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

#include <stdio.h>
#include "Pixel.h"

Pixel::Pixel(int red, int green, int blue)
{
    _red = red;
    _blue = blue;
    _green = green;
}

Pixel::Pixel(std::string red,
             std::string green,
             std::string blue,
             bool isEdge,
             Pixel * originalImage,
             Pixel * modifiedImage,
             int x,
             int y
             )
{
    _sRed = red;
    _sGreen = green;
    _sBlue = blue;
    _isEdge = isEdge;
    _originalImage = originalImage;
    _modifiedImage = modifiedImage;
    _x = x;
    _y = y;
    _red = std::stoi(_sRed);
    _green = std::stoi(_sGreen);
    _blue = std::stoi(_sBlue);
}

void Pixel::setMotionValue(int motionValue)
{
    _motionValue = motionValue;
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

void Pixel::invert()
{
    
}

void Pixel::grayscale()
{
    
}

void Pixel::emboss()
{
    
}

void Pixel::motionblur()
{
    
}


