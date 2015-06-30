//
//  Pixel.cpp
//  ImageEditor
//
//  Created by Andrei Rybin on 6/20/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

#include "Pixel.h"
#include <stdlib.h>
#include <algorithm>

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

void Pixel::grayscale(std::vector<Pixel> & modifiedImage, int x)
{
    int average = (_red + _green + _blue) / NUM_OF_COLORS;
    Pixel p(average,average,average);
    modifiedImage[x] = p;

}

void Pixel::emboss(std::vector<std::vector<Pixel>> & originalImage, std::vector<Pixel> & modifiedImage, int x, int y)
{
    int embossValue = 0;
    if ((x - 1) < 0 || (y - 1) < 0)
    {
        embossValue = 128;
    }
    else
    {
        int redDiff = abs(_red - originalImage[y - 1][x - 1].getRed());
        int greenDiff = abs(_green - originalImage[y - 1][x - 1].getGreen());
        int blueDiff = abs(_blue - originalImage[y - 1][x - 1].getBlue());
        int maxDifference = std::max(redDiff, std::max(greenDiff, blueDiff));
        embossValue = maxDifference;
    }
    
    embossValue += 128;
    
    if (embossValue < 0) {
        embossValue = 0;
    } else if (embossValue > 255) {
        embossValue = 255;
    }
    Pixel p(embossValue, embossValue, embossValue);
    modifiedImage[x] = p;
}

void Pixel::motionblur(std::vector<std::vector<Pixel>> & originalImage, std::vector<Pixel> & modifiedImage, int x, int y)
{
    
}


