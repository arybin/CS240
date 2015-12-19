//
//  ImageProcessor.swift
//  ImageEditor
//
//  Created by Andrei Rybin on 7/2/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

import Foundation

class ImageProccesor {
    
    private var path:String
    private var lines:[String]
    private var motionValue:Int
    
    init (path:String) {
        self.path = path
        self.lines = []
        self.motionValue = 0
        readStream()
    }
    
    //read from the file or stream
    func readStream() {
        if let data = NSData(contentsOfFile: path){
            //read into an NSString then downcast to a Swift String object
            let input = NSString(data: data, encoding: NSUTF8StringEncoding) as! String
            //new array for holding the individual lines
            //splits the string into an array
            input.enumerateLines{(line,stop) -> () in self.lines.append(line)}
        }
        processStream()
    }
    
    func setMotionValue(value : Int) {
        self.motionValue = value
    }
    
    //proccess into a data structure
    func processStream() {
        //two-d array of pixels
        var position = 0
        var originalImage = [[Pixel]]()
        var changedImage = [[Pixel]]()
        let fileType = self.lines[position++]
        let _ = self.lines[position++]
        let dimensions:[String] = self.lines[position++].componentsSeparatedByString(" ")
        let width = Int(dimensions[0])!
        let height = Int(dimensions[1])!
        let highestValue = self.lines[position++]
        print(fileType)
        print("\(dimensions) \(highestValue)")
        var horizontalPosition = 0
        var verticalPosition = 0
        var row = [Pixel]()
        while position < self.lines.count {
            let red = self.lines[position++]
            let green =  self.lines[position++]
            let blue = self.lines[position++]
            let isEdge = horizontalPosition == 0 ||
                verticalPosition == 0 ||
                horizontalPosition + 1 == width ||
                verticalPosition + 1 == height
            let p = Pixel(red: red, green: green, blue: blue, originalImage: &originalImage , modifiedImage: &changedImage,
                x: horizontalPosition, y: verticalPosition, isEdge: isEdge)
            p.setMotionValue(self.motionValue)
            row.append(p)
            horizontalPosition++
            if horizontalPosition % width == 0 {
                verticalPosition++
                horizontalPosition = 0
                originalImage.append(row)
                row = [Pixel]()
            }
            
        }
        print("done")
        
    }
    
    private func applyAction() {
        
    }
}