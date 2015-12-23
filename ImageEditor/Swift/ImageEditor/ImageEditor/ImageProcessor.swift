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
    private var modifiedImage:PixelArray
    private var originalImage:PixelArray
    
    enum Action {
        case GRAYSCALE
        case EMBOSS
        case MOTIONBLUR
        case INVERT
    }
    
    init (path:String) {
        self.path = path
        self.lines = []
        self.motionValue = 0
        modifiedImage = PixelArray(width: 0, height: 0)
        originalImage = PixelArray(width: 0, height: 0)
        readStream()
    }
    
    //read from the file or stream
    private func readStream() {
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
    private func processStream() {
        //two-d array of pixels
        var position = 0
        let _ = self.lines[position++] //file type
        let _ = self.lines[position++] //comment
        let dimensions:[String] = self.lines[position++].componentsSeparatedByString(" ")
        let width = Int(dimensions[0])!
        let height = Int(dimensions[1])!
        originalImage = PixelArray(width: width, height: height)
        modifiedImage = PixelArray(width: width, height: height)
        let _ = self.lines[position++] //highest value
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
            let p = Pixel(red: red, green: green, blue: blue, x: horizontalPosition, y: verticalPosition, isEdge: isEdge)
            p.setMotionValue(self.motionValue)
            row.append(p)
            horizontalPosition++
            if horizontalPosition % width == 0 {
                verticalPosition++
                horizontalPosition = 0
                originalImage.appendRow(row)
                row = [Pixel]()
            }
            
        }
        print("done processing stream")
        
    }
    
    func performOperation(operation:Pixel -> Pixel) {
        
    }
    
    func applyAction(defaultAction: Action) {
        switch defaultAction{
            case .GRAYSCALE: grayScale()
            case .EMBOSS: emboss()
            case .INVERT: invert()
            case .MOTIONBLUR: motionBlur()
        }
        
        var output:String = ""

        output += "P3\n"
        output += "\(self.modifiedImage.getWidth()) \(self.modifiedImage.getHeight())\n"
        output += "255\n"
        for var y = 0; y < self.modifiedImage.getCount(); y++ {
            for var x = 0; x < self.modifiedImage.getRow(y).count; x++ {
                let p = self.modifiedImage.getValue(x, y: y)
                output += "\(p.getRed())\n"
                output += "\(p.getGreen())\n"
                if y + 1 != self.modifiedImage.getCount() {
                    output += "\(p.getBlue())\n"
                } else {
                    output += "\(p.getBlue())"
                }
            }
        }
        writeResultsToFile(String(defaultAction), output: output)
        
    }
    
    private func writeResultsToFile(actionName: String, output: String) {
        let parts = self.path.componentsSeparatedByString("/")
        let fileNameParts = parts[parts.count-1].componentsSeparatedByString(".")
        var finalPath = ""
        for i in 0...parts.count-2 { //forgot that this was inclusive
            finalPath += (parts[i] + "/")
        }
        finalPath += fileNameParts[0] //this is the name
        finalPath += "_" + actionName
        finalPath += "." + fileNameParts[1]
        do {
            try output.writeToFile(finalPath, atomically: true, encoding: NSUTF8StringEncoding)
        } catch {
            print("error thrown when trying to write to file")
        }
        print("done printing to file")
        
    }
    
    func emboss() {
        for var y = 0; y < originalImage.getCount(); y++ {
            var row:[Pixel] = []
            for var x = 0; x < originalImage.getRow(y).count; x++ {
                let p = originalImage.getValue(x, y: y).emboss(originalImage)
                row.append(p)
            }
            modifiedImage.appendRow(row)
        }

    }
    
    func motionBlur() {
        for var y = 0; y < originalImage.getCount(); y++ {
            var row:[Pixel] = []
            for var x = 0; x < originalImage.getRow(y).count; x++ {
                let p = originalImage.getValue(x, y: y).motionBlur(originalImage)
                row.append(p)
            }
            modifiedImage.appendRow(row)
        }

    }
    
    func invert() {
        for var y = 0; y < originalImage.getCount(); y++ {
            var row:[Pixel] = []
            for var x = 0; x < originalImage.getRow(y).count; x++ {
                let p = originalImage.getValue(x, y: y).invert()
                row.append(p)
            }
            modifiedImage.appendRow(row)
        }

    }
    
    func grayScale() {
        for var y = 0; y < originalImage.getCount(); y++ {
            var row:[Pixel] = []
            for var x = 0; x < originalImage.getRow(y).count; x++ {
                let p = originalImage.getValue(x, y: y).grayscale()
                row.append(p)
            }
            modifiedImage.appendRow(row)
        }
    }
}