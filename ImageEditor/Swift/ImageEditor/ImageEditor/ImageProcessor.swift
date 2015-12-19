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
    
    init (path:String) {
        self.path = path
        readStream()
    }
    
    //read from the file or stream
    func readStream() {
        if let data = NSData(contentsOfFile: path){
            //read into an NSString then downcast to a Swift String object
            let input = NSString(data: data, encoding: NSUTF8StringEncoding) as! String
            //new array for holding the individual lines
            var lines:[String] = []
            //splits the string into an array
            input.enumerateLines{(line,stop) -> () in lines.append(line)}
            print(input)
        }
    }
    
    //proccess into a data structure
    func processStream() {
        
    }
    
    private func applyAction() {
        
    }
}