//
//  PixelArray.swift
//  ImageEditor
//
//  Created by Andrei Rybin on 12/19/15.
//  Copyright © 2015 Andrei Rybin. All rights reserved.
//

import Foundation

//this is a wrapper to be able to pass the array by reference
class PixelArray {
    private var width:Int
    private var height: Int
    private var array:[[Pixel]]
    
    init(width: Int, height: Int) {
        self.width = width
        self.height = height
        self.array = [[]]
        array.removeAll()
    }
    
    func appendRow(pixel: [Pixel]) {
        self.array.append(pixel)
    }
    
    func getValue(x: Int, y: Int) -> Pixel {
        return self.array[y][x]
    }
    
    func setValue(pixel: Pixel, x: Int, y: Int) {
        self.array[y][x] = pixel
    }
    
    func getRow(y:Int) ->[Pixel] {
        return self.array[y]
    }
    
    
}
