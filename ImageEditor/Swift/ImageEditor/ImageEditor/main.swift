//
//  main.swift
//  ImageEditor
//
//  Created by Andrei Rybin on 6/29/15.
//  Copyright (c) 2015 Andrei Rybin. All rights reserved.
//

import Foundation
let path = "/Users/andreirybin/Projects/CS240/ImageEditor/TestFiles/cs_logo.ppm"
let action = ImageProccesor.Action.INVERT
let processor = ImageProccesor(path: path)
processor.applyAction(action)




