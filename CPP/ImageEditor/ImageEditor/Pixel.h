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
    int x;
    int y;
    Pixel[][] originalImage;
    Pixel[][] modifiedImage;
    int red;
    int green;
    int blue;
    string sRed;
    string sBlue;
    string sGreen;
    bool isEdge;
    int motionValue;
    
public:
    Pixel(int red, int green, int blue);
    
    
};

#endif
