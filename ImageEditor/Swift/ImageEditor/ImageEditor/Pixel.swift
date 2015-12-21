//
//  Pixel.swift
//  ImageEditor
//
//  Created by Andrei Rybin on 7/1/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

import Foundation

class Pixel {
    private let MAX_VALUE:Int = 255
    private let TOTAL_COLORS: Int = 3
    
    private var red: Int = 0
    private var green: Int = 0
    private var blue: Int = 0
    private var isEdge: Bool = false
    private var sRed: String = ""
    private var sGreen: String = ""
    private var sBlue: String = ""
    private var originalImage:PixelArray
    private var modifiedImage:PixelArray
    private var x:Int = 0
    private var y:Int = 0
    private var motionValue:Int = 0
    
    
    
    init(red:String,
        green:String,
        blue:String,
        originalImage:PixelArray,
        modifiedImage:PixelArray,
        x:Int,
        y:Int,
        isEdge:Bool
        ) {
            self.sRed = red
            self.sGreen = green
            self.sBlue = blue
            self.originalImage = originalImage
            self.modifiedImage = modifiedImage
            self.x = x
            self.y = y
            self.isEdge = isEdge
            self.setIntValues()
    }
    
    private func setIntValues() {
        self.red = Int(self.sRed)!
        self.green = Int(self.sGreen)!
        self.blue = Int(self.sBlue)!
        
    }
    
    func setMotionValue(motionValue:Int) {
        self.motionValue = motionValue
    }
    
    internal func getRed() -> String {
        return self.sRed
    }
    
    func getGreen() -> String {
        return self.sGreen
    }
    
    func getBlue() -> String {
        return self.sBlue
    }
    
    
    func invert() {
        let red = abs(self.red - self.MAX_VALUE)
        let green = abs(self.green - self.MAX_VALUE)
        let blue = abs(self.blue - self.MAX_VALUE)
        let pixel = Pixel(red: String(red), green: String(green), blue: String(blue), originalImage: self.originalImage, modifiedImage: self.modifiedImage, x: self.x, y: self.y, isEdge: self.isEdge)
        modifiedImage.setValue(pixel, x: self.x, y: self.y)// [self.y][self.x] = pixel
        
    }
    
    func grayscale() {
        let average = (self.red + self.blue + self.green) / self.TOTAL_COLORS
        let pixel = Pixel(red: String(average), green: String(average), blue: String(average), originalImage: self.originalImage, modifiedImage: self.modifiedImage, x: self.x, y: self.y, isEdge: self.isEdge)
            modifiedImage.setValue(pixel, x: self.x, y: self.y)
    }
    
    func emboss() {
        var embossValue = 0
        if (self.x - 1) < 0 && (self.y - 1) < 0 {
            embossValue = 128
        } else {
            let pixel = self.originalImage.getValue(self.x-1, y: self.y-1)
            let redDiff = abs(self.red - pixel.red)
            let greenDiff = abs(self.green - pixel.green)
            let blueDiff = abs(self.blue - pixel.blue)
            let maxDifference = max(redDiff,max(greenDiff, blueDiff))
            embossValue = maxDifference
        }
        embossValue += 128
        if embossValue < 0 {
            embossValue = 0
        } else if embossValue > 255 {
            embossValue = 255
        }
        let pixel = Pixel(red: String(embossValue), green: String(embossValue), blue: String(embossValue), originalImage: self.originalImage, modifiedImage: self.modifiedImage, x: self.x, y: self.y, isEdge: self.isEdge)
        modifiedImage.setValue(pixel, x: self.x, y: self.y)
    }
    
    func motionBlur() {
        var row = self.originalImage.getRow(self.y)
        var blurValue = self.motionValue
        var red = 0
        var green = 0
        var blue = 0
        
        if(blurValue + self.x) >= row.count {
            blurValue = row.count - self.x
        }
        for var i = self.x; i < (blurValue + self.x - 1); i++ {
            red += row[i].red
            green += row[i].green
            blue += row[i].blue
        }
        red /= blurValue
        green /= blurValue
        blue /= blurValue
        let pixel = Pixel(red: String(red), green: String(green), blue: String(blue), originalImage: self.originalImage, modifiedImage: self.modifiedImage, x: self.x, y: self.y, isEdge: self.isEdge)
        self.modifiedImage.setValue(pixel, x: self.x, y: self.y)
    }
}