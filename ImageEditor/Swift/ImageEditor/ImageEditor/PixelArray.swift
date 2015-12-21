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
    
    func getWidth() -> Int {
        return self.width
    }
    
    func getHeight() -> Int {
        return self.height
    }
    
    func appendRow(pixel: [Pixel]) {
        self.array.append(pixel)
    }
    
    func getValue(x: Int, y: Int) -> Pixel {
        return self.array[y][x]
    }
    
    func setValue(pixel: Pixel, x: Int, y: Int) {
        self.array[y].insert(pixel, atIndex: x)
    }
    
    func getRow(y:Int) ->[Pixel] {
        return self.array[y]
    }
    
    func getCount() -> Int {
        return self.array.count
    }
}
