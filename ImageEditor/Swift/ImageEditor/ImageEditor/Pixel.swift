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
    private var originalImage = Array<Array<Pixel>>()
    private var modifiedImage = Array<Array<Pixel>>()
    private var x:Int = 0
    private var y:Int = 0
    private var motionValue:Int = 0
    
    init(red:Int, green:Int, blue:Int){
        self.red = red
        self.green = green
        self.blue = blue
    }
    
    init(red:String,
        green:String,
        blue:String,
        inout originalImage:Array<Array<Pixel>>,
        inout modifiedImage:Array<Array<Pixel>>,
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
        self.red = self.sRed.toInt()!
        self.green = self.sGreen.toInt()!
        self.blue = self.sBlue.toInt()!
        
    }
    
    func setMotionValue(motionValue:Int) {
        self.motionValue = motionValue
    }
    
    func invert() {
        var red = abs(self.red - self.MAX_VALUE)
        var green = abs(self.green - self.MAX_VALUE)
        var blue = abs(self.blue - self.MAX_VALUE)
        let pixel = Pixel(red: red, green: green, blue: blue)
        modifiedImage[self.y][self.x] = pixel
        
    }
    
    func grayscale() {
        var average = (self.red + self.blue + self.green) / self.TOTAL_COLORS
        let pixel = Pixel(red: average, green: average, blue: average)
        modifiedImage[self.y][self.x] = pixel
    }
    
    
    
    
    
    
    
}