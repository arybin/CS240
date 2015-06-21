//
//  main.cpp
//  ImageEditor
//
//  Created by Andrei Rybin on 6/20/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

#include <iostream>
#include "ImageProcessor.h"


int main(int argc, const char * argv[]) {
    
    std::string INVERT = "invert";
    std::string GRAYSCALE = "grayscale";
    std::string EMBOSS = "emboss";
    std::string MOTIONBLUR = "motionblur";
    int motionValue = 20;


    std::string fileName = "TestFiles/audio.ppm";
    std::string action = "invert";
    ImageProcessor iProcessor(fileName);
    iProcessor.processContent();
    if(action.compare(INVERT) == 0)
    {
        iProcessor.invert();
    }
    else if(action.compare(GRAYSCALE) == 0)
    {
        iProcessor.grayscale();
    }
    else if(action.compare(EMBOSS) == 0)
    {
        iProcessor.emboss();
    }
    else if(action.compare(MOTIONBLUR) == 0)
    {
        iProcessor.setMotionValue(motionValue);
        iProcessor.motionblur();
    }
    
    
    return 0;
}
