from PIL import Image, ImageFilter
import os

BASE_DIR = os.path.dirname(__file__)
IMG_DIR = os.path.join(BASE_DIR, '../img')



# Creates a flipped, grayscale, and blury version of the image
def flipImg(img):
	im = Image.open(os.path.join(IMG_DIR, img))
	flip = im.rotate(180)
	flip.save(os.path.join(IMG_DIR, 'flip.png'))
	gray = im.convert('L')
	gray.save(os.path.join(IMG_DIR, 'gray.png'))
	blur = im.filter(ImageFilter.BLUR)
	blur.save(os.path.join(IMG_DIR, 'blur.png'))



#Image should be located in the img folder
flipImg('mapplop.png')
