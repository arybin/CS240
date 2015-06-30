//
//  ImageProcessor.h
//  ImageEditor
//
//  Created by Andrei Rybin on 6/20/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

#ifndef __ImageEditor__ImageProcessor__
#define __ImageEditor__ImageProcessor__

#include <stdio.h>
#include <string>
#include <vector>
#include <functional>
#include "Pixel.h"
class ImageProcessor
{
private:
    int ROWS_TO_IGNORE = 4;
    int DIMENSIONS = 2;
    int TOTAL_COLORS = 3;
    int _height;
    int _width;
    int _motionValue;
    std::vector<std::vector<Pixel>> originalImage;
    std::vector<std::vector<Pixel>> modifiedImage;
    std::string _filePath;
    std::string _fileName;
    //function pointers, need to figure out how to use those

public:
    ImageProcessor(std::string filePath);
    void setMotionValue(int motionValue);
    void processContent();
    void populateImageMatrix(std::string imageData[]);
    void invert();
    void grayscale();
    void emboss();
    void motionblur();
    

    
    
    
};

#endif /* defined(__ImageEditor__ImageProcessor__) */
