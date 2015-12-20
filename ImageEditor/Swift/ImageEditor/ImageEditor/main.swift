//
//  main.swift
//  ImageEditor
//
//  Created by Andrei Rybin on 6/29/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

import Foundation
let path = "/Users/andreirybin/Projects/CS240/ImageEditor/Java/ImageEditor/TestFiles/cs_logo.ppm"
var pixel = ImageProccesor(path: path)
pixel.writeResultsToFile("grayscale")



