//
//  ImageProcessor.cpp
//  ImageEditor
//
//  Created by Andrei Rybin on 6/20/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

#include "ImageProcessor.h"
#include <iostream>
#include <fstream>
#include <regex>
#include <sstream>
#include <vector>


ImageProcessor::ImageProcessor(std::string filePath)
{
    _filePath = filePath;
    _motionValue = 0;
    
}

void ImageProcessor::processContent()
{
    std::string line;
    std::ifstream file(_filePath);
    std::string width = "";
    std::string height = "";
    int w;
    int h;
    int lineCount(0);
    std::vector<std::string> imageData;
    if(file)//if the file exists
    {
        while (getline(file, line))
        {
            if(lineCount == DIMENSIONS)
            {
                std::string delimiter = " ";
                int index = (int)line.find(delimiter);
                width.append(line.substr(0,index));
                height.append(line.substr(index+1,line.length()-1));
                w = std::stoi(width);
                h = std::stoi(height);
                imageData.resize(h*w);
                _originalImage = new Pixel*[h];
                _modifiedImage = new Pixel*[h];

            } else if(lineCount > ROWS_TO_IGNORE) {
                //add everything to the vector
                imageData.push_back(line);
            }
            ++lineCount;
        }
        int horPosition = 0;
        int vertPosition = 0;
        for (auto it = 0; it < imageData.size(); ++it) {
            std::string red = imageData[it];
            ++it;
            std::string green = imageData[it];
            ++it;
            std::string blue = imageData[it];
            ++it;
            bool isEdge = horPosition == 0 ||
            vertPosition == 0 ||
            horPosition + 1 == w ||
            vertPosition + 1 == h;
            auto value = _originalImage[vertPosition];
            if(!value) {
                _originalImage[vertPosition] = new Pixel[w];
            }
            //Pixel p(red,green,blue,isEdge);
            
            
        }
    }
    else
    {
        std::cout<<"file doesn't exist\n";
    }
}

void ImageProcessor::setMotionValue(int motionValue)
{
    _motionValue = motionValue;
}

void ImageProcessor::motionblur()
{
    
}

void ImageProcessor::invert()
{
    
}

void ImageProcessor::grayscale()
{
    
}
void ImageProcessor::emboss()
{
    
}

