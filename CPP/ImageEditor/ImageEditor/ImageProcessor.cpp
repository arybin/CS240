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
    std::string delimiter = "/";
    int index = (int) _filePath.find(delimiter);
    _fileName = _filePath.substr(index+1, _filePath.length()-1);
    
}

void ImageProcessor::processContent()
{
    std::string line;
    std::ifstream file(_filePath);
    std::string width = "";
    std::string height = "";
    int w = 0;
    int h = 0;
    std::vector<std::string> * data = new std::vector<std::string>();
    if(file)//if the file exists
    {
        while (getline(file, line))
        {
            data->push_back(line);
        }
        
        std::string delimiter = " ";
        line = data->at(DIMENSIONS);
        int index = (int)line.find(delimiter);
        width.append(line.substr(0,index));
        height.append(line.substr(index+1,line.length()-1));
        w = std::stoi(width);
        _width = w;
        h = std::stoi(height);
        _height = h;
        for(int i = 0; i < h; i++)
        {
            std::vector<Pixel> row(w);
            originalImage.push_back(row);
        }
        
        int horPosition = 0;
        int vertPosition = 0;
        for (auto it = 4; it < data->size(); ++it)
        {
            std::string red = data->at(it);
            ++it;
            std::string green = data->at(it);
            ++it;
            std::string blue = data->at(it);
            bool isEdge = horPosition == 0 ||
            vertPosition == 0 ||
            horPosition + 1 == w ||
            vertPosition + 1 == h;
            
            Pixel p(red,green,blue,isEdge);
            originalImage[vertPosition][horPosition] = p;
            horPosition++;
            if (horPosition % w == 0) {
                vertPosition++;
                horPosition = 0;
            }
            
            
        }
        file.close();
        delete data;
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
    std::string outfile;
    outfile.append("TestFiles/");
    outfile.append("invert_");
    outfile.append(_fileName);
    std::ofstream out(outfile, std::ofstream::trunc);
    if(out)
    {
        out<<"P3\n";
        out<<_width<<" "<<_height<<"\n";
        out<<"255\n";
        for (int i = 0; i < _height; i++)
        {
            std::vector<Pixel> row(_width);
            for(int j = 0; j < _width; j++)
            {
                Pixel p = originalImage[i][j];
                p.invert(row, j);
                p = row[j];
                out<<p.getSRed()<<"\n";
                out<<p.getSGreen()<<"\n";
                if(i+1 != _height)
                {
                    out<<p.getSBlue()<<"\n";
                }
                else
                {
                    out<<p.getSBlue();
                }
            }
            modifiedImage.push_back(row);
        }
    }
    else
    {
        std::cout<<"unable to create the file\n";
    }
    out.close();
}

void ImageProcessor::grayscale()
{
    
}
void ImageProcessor::emboss()
{
    
}

